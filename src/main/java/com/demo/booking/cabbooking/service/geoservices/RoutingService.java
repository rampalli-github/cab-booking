package com.demo.booking.cabbooking.service.geoservices;

import com.demo.booking.cabbooking.model.Location;
import com.demo.booking.cabbooking.model.Route;

public interface RoutingService {

    public Route findRoute(Location startLocation, Location destinationLocation);
}
