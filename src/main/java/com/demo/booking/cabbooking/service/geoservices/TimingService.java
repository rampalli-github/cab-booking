package com.demo.booking.cabbooking.service.geoservices;

import com.demo.booking.cabbooking.model.Route;

public interface TimingService {

    public long calculateTimeForRoute(Route route);
}
