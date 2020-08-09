package com.demo.booking.cabbooking;

import com.demo.booking.cabbooking.model.Vehicle;
import com.demo.booking.cabbooking.service.ridemanagement.CabStateMachineService;
import com.demo.booking.cabbooking.statemachine.CabStates;
import com.demo.booking.cabbooking.statemachine.RideEvents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CabBookingEventServiceTests {

    public static final String SAMPLE_ENGINE_ID = "12345";
    @Autowired
    private CabStateMachineService cabStateMachineService;

    @Test
    public void testCabComingOnline() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setEngineID(SAMPLE_ENGINE_ID);
        cabStateMachineService.feedMachine(vehicle, RideEvents.ONLINEREQUESTED);
        Assertions.assertEquals(CabStates.READYTORIDE, cabStateMachineService.getCurrentState(vehicle));
    }
}
