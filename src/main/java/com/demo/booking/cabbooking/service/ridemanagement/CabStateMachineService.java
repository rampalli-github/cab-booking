package com.demo.booking.cabbooking.service.ridemanagement;

import com.demo.booking.cabbooking.model.Vehicle;
import com.demo.booking.cabbooking.statemachine.CabStates;
import com.demo.booking.cabbooking.statemachine.RideEvents;
import org.springframework.statemachine.StateMachine;

public interface CabStateMachineService {
    public void feedMachine(Vehicle vehicle, RideEvents id) throws Exception ;
    public StateMachine<CabStates, RideEvents> resetStateMachineFromStore(Vehicle vehicle) throws Exception;
    public CabStates getCurrentState(Vehicle vehicle) throws Exception;
}
