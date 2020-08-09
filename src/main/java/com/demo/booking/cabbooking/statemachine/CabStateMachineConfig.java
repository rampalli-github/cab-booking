package com.demo.booking.cabbooking.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

@Configuration
public class CabStateMachineConfig {
    private static final Logger logger = LoggerFactory.getLogger(CabStateMachineConfig.class);
    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ProxyFactoryBean stateMachine() {
        ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.setTargetSource(poolTargetSource());
        return pfb;
    }

    @Bean
    public CommonsPool2TargetSource poolTargetSource() {
        CommonsPool2TargetSource pool = new CommonsPool2TargetSource();
        pool.setMaxSize(3);
        pool.setTargetBeanName("stateMachineTarget");
        return pool;
    }

    @Bean
    public StateMachinePersister<CabStates, RideEvents, String> stateMachinePersist() {
        // use stateMachinePersist without redis to ease testing
        return new InMemoryStateMachinePersist();
    }

    @Bean
    public CabStateMachineListener stateMachineEventListener() {
        CabStateMachineListener listener = new CabStateMachineListener();
        return listener;
    }

    @Bean(name = "stateMachineTarget")
    @Scope(scopeName="prototype")
    public StateMachine<CabStates, RideEvents> stateMachineTarget() throws Exception {
        StateMachineBuilder.Builder<CabStates, RideEvents> builder = StateMachineBuilder.<CabStates, RideEvents>builder();

        builder.configureConfiguration()
                .withConfiguration()
                .autoStartup(true);

        builder.configureStates()
                .withStates()
                .junction(CabStates.WAITINGFORDRIVERCONFIRMATION)
                .junction(CabStates.WAITINGFORUSERCONFIRMATION)
                .junction(CabStates.WAITINGFORPASSENGER)
                .initial(CabStates.OFFLINE)
                .states(EnumSet.allOf(CabStates.class));

        builder.configureTransitions()
                .withExternal()
                .source(CabStates.OFFLINE).target(CabStates.READYTORIDE).event(RideEvents.ONLINEREQUESTED)
                .action(makeCabOnline(), errorAction())
                .and()
                .withExternal()
                .source(CabStates.READYTORIDE).target(CabStates.WAITINGFORDRIVERCONFIRMATION).event(RideEvents.RIDEREQUESTED)
                //.action(delAction())
                .and()
                .withJunction()
                .source(CabStates.WAITINGFORDRIVERCONFIRMATION)
                .first(CabStates.WAITINGFORUSERCONFIRMATION, checkDriverConfirmation())
                .then(CabStates.READYTORIDE, ctx -> false)
                //.action(payAction())
                .and()
                .withJunction()
                .source(CabStates.WAITINGFORUSERCONFIRMATION)
                .first(CabStates.RIDECONFIRMED, checkUserConfirmation())
                .then(CabStates.RIDECONFIRMED, ctx -> false)
                .and()
                .withExternal()
                .source(CabStates.RIDECONFIRMED)
                .target(CabStates.REACHINGPICKUPLOCATION)
                .event(RideEvents.RIDEBOOKED)
                .and()
                .withExternal()
                .source(CabStates.REACHINGPICKUPLOCATION)
                .target(CabStates.WAITINGFORPASSENGER)
                .event(RideEvents.ARRIVEDATPICKUPLOCATION)
                .and()
                .withJunction()
                .source(CabStates.WAITINGFORPASSENGER)
                .first(CabStates.RIDEINPROGRESS, checkPassengerArrived())
                .then(CabStates.READYTORIDE, ctx -> false)
                .and()
                .withExternal()
                .source(CabStates.RIDEINPROGRESS).target(CabStates.READYTORIDE)
                //.action(pageviewAction())
                .event(RideEvents.RIDECOMPLETED);
        return builder.build();
    }

    private Action<CabStates, RideEvents> errorAction() {
        return new Action<CabStates, RideEvents>() {

            @Override
            public void execute(StateContext<CabStates, RideEvents> context) {
                logger.error("Exception occurred ",context.getException());
            }
        };
    }

    private Action<CabStates, RideEvents> makeCabOnline() {
        return new Action<CabStates, RideEvents>() {

            @Override
            public void execute(StateContext<CabStates, RideEvents> context) {
                logger.info("Cab made online");
            }
        };
    }

    private Guard<CabStates, RideEvents> checkPassengerArrived() {
        return ctx -> true;
    }

    private Guard<CabStates, RideEvents> checkUserConfirmation() {
        return ctx -> true;
    }

    private Guard<CabStates, RideEvents> checkDriverConfirmation() {
        return ctx -> true;
    }
}
