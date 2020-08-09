package com.demo.booking.cabbooking.service.geoservices;

import com.demo.booking.cabbooking.model.Location;
import com.demo.booking.cabbooking.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoFencingServiceImpl implements GeoFencingService{
    @Override
    public List<Vehicle> searchForVehicles(Location location) {
        return new ArrayList<>();
    }
}
