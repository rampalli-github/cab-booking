package com.demo.booking.cabbooking.service.geoservices;

import com.demo.booking.cabbooking.model.Route;
import org.springframework.stereotype.Service;

@Service
public class TimingServiceImpl implements TimingService{
    @Override
    public long calculateTimeForRoute(Route route) {
        return 100;
    }
}
