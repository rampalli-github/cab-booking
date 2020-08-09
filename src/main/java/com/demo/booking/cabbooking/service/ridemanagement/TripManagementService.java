package com.demo.booking.cabbooking.service.ridemanagement;

import com.demo.booking.cabbooking.model.Location;

public interface TripManagementService {
    public void startGeoFenceForUserLocation(Location location);
    public void startGeoFenceForDriverLocation(Location location);
}
