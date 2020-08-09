package com.demo.booking.cabbooking.service.geoservices;

import com.demo.booking.cabbooking.model.Location;
import com.demo.booking.cabbooking.model.Vehicle;

import java.util.List;

public interface VicinityService {
    public List<Vehicle> getNearbyVehicles(Location location);
}
