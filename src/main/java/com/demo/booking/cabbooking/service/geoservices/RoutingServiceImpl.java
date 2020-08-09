package com.demo.booking.cabbooking.service.geoservices;

import com.demo.booking.cabbooking.model.Location;
import com.demo.booking.cabbooking.model.Route;
import org.springframework.stereotype.Service;

@Service
public class RoutingServiceImpl implements RoutingService{
    @Override
    public Route findRoute(Location startLocation, Location destinationLocation) {
        Route route = new Route();
        route.setStartLocation(startLocation);
        route.setDestinationLocation(destinationLocation);
        return route;
    }
}
