package com.demo.booking.cabbooking.service.geoservices;

import com.demo.booking.cabbooking.model.Location;
import com.demo.booking.cabbooking.model.Vehicle;

import java.util.List;

/**
 * Service that provides GeoFencing apis.
 * It is proposed to use an opensource server such as tile38 https://github.com/tidwall/tile38
 */
public interface GeoFencingService {

    public List<Vehicle> searchForVehicles(Location location);
}
