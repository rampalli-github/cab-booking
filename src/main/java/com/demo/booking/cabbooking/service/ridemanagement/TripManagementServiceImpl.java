package com.demo.booking.cabbooking.service.ridemanagement;

import com.demo.booking.cabbooking.model.Location;
import com.demo.booking.cabbooking.service.geoservices.GeoFencingService;
import com.demo.booking.cabbooking.service.geoservices.RoutingService;
import com.demo.booking.cabbooking.service.geoservices.TimingService;
import com.demo.booking.cabbooking.service.geoservices.VicinityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripManagementServiceImpl implements TripManagementService{
    @Autowired
    private GeoFencingService geoFencingService;

    @Autowired
    private RoutingService routingService;

    @Autowired
    private TimingService timingService;

    @Autowired
    private VicinityService vicinityService;

    @Override
    public void startGeoFenceForUserLocation(Location location) {

    }

    @Override
    public void startGeoFenceForDriverLocation(Location location) {

    }
}
