package com.demo.booking.cabbooking.service.ridemanagement;

import com.demo.booking.cabbooking.model.Vehicle;
import com.demo.booking.cabbooking.statemachine.CabStateMachineListener;
import com.demo.booking.cabbooking.statemachine.CabStates;
import com.demo.booking.cabbooking.statemachine.RideEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

@Service
public class CabStateMachineServiceImpl implements CabStateMachineService{
    public static final String TESTPREFIX = "testprefix:";
    @Autowired
    private StateMachine<CabStates, RideEvents> stateMachine;

    @Autowired
    private CabStateMachineListener stateMachineListener;

    @Autowired
    private StateMachinePersister<CabStates, RideEvents, String> stateMachinePersister;

    public void feedMachine(Vehicle vehicle, RideEvents id) throws Exception {
        stateMachine.addStateListener(stateMachineListener);
        stateMachine
                .sendEvent(MessageBuilder
                        .withPayload(id).build());
        stateMachinePersister.persist(stateMachine, TESTPREFIX + vehicle.getEngineID());
    }

    public StateMachine<CabStates, RideEvents> resetStateMachineFromStore(Vehicle vehicle) throws Exception {
        return stateMachinePersister.restore(stateMachine, TESTPREFIX + vehicle.getEngineID());
    }

    @Override
    public CabStates getCurrentState(Vehicle vehicle) throws Exception {
        resetStateMachineFromStore(vehicle);
        return stateMachine.getState().getId();
    }
}
