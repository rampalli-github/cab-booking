package com.demo.booking.cabbooking.statemachine;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStateMachinePersist implements StateMachinePersister<CabStates, RideEvents, String> {
    private final Map<String, StateMachine<CabStates, RideEvents>> contexts = new HashMap<>();

    @Override
    public void persist(StateMachine<CabStates, RideEvents> stateMachine, String s) throws Exception {
        contexts.put(s, stateMachine);
    }

    @Override
    public StateMachine<CabStates, RideEvents> restore(StateMachine<CabStates, RideEvents> stateMachine, String s) throws Exception {
        return contexts.get(s);
    }
}
