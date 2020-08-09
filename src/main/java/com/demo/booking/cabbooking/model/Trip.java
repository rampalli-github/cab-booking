package com.demo.booking.cabbooking.model;

import java.time.LocalDateTime;

public class Trip {
    private String tripId;
    private User user;
    private Vehicle vehicle;
    private Driver driver;
    private Location plannedStartLocation;
    private Location plannedDestinationLocation;
    private Location actualStartLocation;
    private Location actualDestinationLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float distance;
    private float fare;
}
