package com.demo.booking.cabbooking;

import com.demo.booking.cabbooking.model.Location;
import com.demo.booking.cabbooking.model.Route;
import com.demo.booking.cabbooking.model.Vehicle;
import com.demo.booking.cabbooking.service.geoservices.RoutingService;
import com.demo.booking.cabbooking.service.geoservices.TimingService;
import com.demo.booking.cabbooking.service.geoservices.VicinityService;
import com.demo.booking.cabbooking.service.operational.FareCalculationService;
import com.demo.booking.cabbooking.service.ridemanagement.CabStateMachineService;
import com.demo.booking.cabbooking.service.ridemanagement.TripManagementService;
import com.demo.booking.cabbooking.statemachine.CabStates;
import com.demo.booking.cabbooking.statemachine.RideEvents;
import org.concordion.integration.junit4.ConcordionRunner;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(ConcordionRunner.class)
public class BookCabFixture {

    private Vehicle vehicle;
    private Location startLocation;
    private Route route;

    @Before
    public void setup(){
        applicationContext = SpringApplication.run(CabBookingApplication.class, new String[0]);
    }

    private ApplicationContext applicationContext;

    public boolean createGeoFence(String latitude, String longitude){
        startLocation = getLocation(latitude, longitude);
        TripManagementService tripManagementService = applicationContext.getBean(TripManagementService.class);
        tripManagementService.startGeoFenceForUserLocation(startLocation);
        return true;
    }

    @NotNull
    private Location getLocation(String latitude, String longitude) {
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }

    public String getVehiclesInVicnity(String latitude, String longitude){
        Location location = getLocation(latitude, longitude);
        VicinityService vicinityService = Mockito.mock(VicinityService.class);
        List<Vehicle> list = new ArrayList<>();
        list.add(vehicle);
        Mockito.when(vicinityService.getNearbyVehicles(location)).thenReturn(list);
        List<Vehicle> nearbyVehicles = vicinityService.getNearbyVehicles(location);
        if(nearbyVehicles.isEmpty()) return "zero";
        else return "non-zero";
    }

    public String bringCabOnline(String vehicleNumber) throws Exception {
        CabStateMachineService cabStateMachineService = applicationContext.getBean(CabStateMachineService.class);
        vehicle = new Vehicle();
        vehicle.setRegistrationNumber(vehicleNumber);
        cabStateMachineService.feedMachine(vehicle, RideEvents.ONLINEREQUESTED);
        if(CabStates.READYTORIDE == cabStateMachineService.getCurrentState(vehicle)) return "online";
        else return "offline";
    }

    public String calculateRoute(String destinationLatitude, String destinationLongitude){
        RoutingService routingService = applicationContext.getBean(RoutingService.class);
        route = routingService.findRoute(startLocation,getLocation(destinationLatitude,destinationLongitude));
        if(route != null) return "route";
        else return "no-route";
    }

    public long calculateTime(){
        TimingService timingService = applicationContext.getBean(TimingService.class);
        return timingService.calculateTimeForRoute(route);
    }

    public float calculateFare(){
        FareCalculationService fareCalculationService = applicationContext.getBean(FareCalculationService.class);
        return fareCalculationService.calculateFareForRoute(route);
    }

    public String bookRide() throws Exception {
        CabStateMachineService cabStateMachineService = applicationContext.getBean(CabStateMachineService.class);
        cabStateMachineService.feedMachine(vehicle, RideEvents.RIDEREQUESTED);
        return cabStateMachineService.getCurrentState(vehicle).toString();
    }
}
