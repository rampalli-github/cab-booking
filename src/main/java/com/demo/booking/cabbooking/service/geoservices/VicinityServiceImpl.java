package com.demo.booking.cabbooking.service.geoservices;

import com.demo.booking.cabbooking.model.Location;
import com.demo.booking.cabbooking.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VicinityServiceImpl implements VicinityService{
    @Override
    public List<Vehicle> getNearbyVehicles(Location location) {
        return new ArrayList<>();
    }
}
