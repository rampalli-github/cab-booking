package com.demo.booking.cabbooking.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;

public class CabStateMachineListener extends StateMachineListenerAdapter<CabStates, RideEvents> {
    private static final Logger logger = LoggerFactory.getLogger(CabStateMachineListener.class);
    @Override
    public void eventNotAccepted(Message<RideEvents> event) {
        logger.info("Event {} not accepted", event.toString());
    }
}
