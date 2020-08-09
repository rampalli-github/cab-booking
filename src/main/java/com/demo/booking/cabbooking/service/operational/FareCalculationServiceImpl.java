package com.demo.booking.cabbooking.service.operational;

import com.demo.booking.cabbooking.model.Route;
import org.springframework.stereotype.Service;

@Service
public class FareCalculationServiceImpl implements FareCalculationService{
    @Override
    public float calculateFareForRoute(Route route) {
        return 10.0f;
    }
}
