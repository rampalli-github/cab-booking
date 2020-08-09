package com.demo.booking.cabbooking.service.operational;

import com.demo.booking.cabbooking.model.Route;

public interface FareCalculationService {

    public float calculateFareForRoute(Route route);
}
