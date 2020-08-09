# Booking a cab

This is a demo of cab booking service, trying to bring together the different aspects of booking a cab into a structure that can be the first iteration of the system architecture. 

## Business Rules or Acceptance Criteria

The cab booking process will have the following pre-requisites

- A vehicle has been registered with the system
- A driver with valid credentials has been associated with the vehicle
- Some kind of device/app has been provided to the driver to allow him to share the vehicle status such as OFFLINE, READYTORIDE etc.
- The user who wants to book the cab has the app installed 
- A geo services component similar to https://github.com/tidwall/tile38 is installed 

### Booking Scenario

- Driver opens the app
    - brings his vehicle FOR HIRE [TS 10 TU 1234](- "#vehicleRegnNo") [online](- "?=bringCabOnline(#vehicleRegnNo)")
    - the app sends the current location [10](- "#latitude") and Longitude [10](- "#longitude")
    - the location of the vehicle is updated in the geo server (similar to tile38) so it is available for search. 
- User opens the app
    - at this point, the Latitude 10 and longitude 10 reaches the TripManagementService
    - TripManagementService initiates a request for creating a [geoFence](- "#result = createGeoFence(#latitude,#longitude)")
    - The creation of geoFence is expected to be [successful](- "c:assert-true=#result")
        - GeoFence allows for getting notified of objects entering or exiting the boundary of the zone so finding the vehicles that are nearby becomes easy.
    - the user selects the Latitude [20](- "#destinationLatitude") and Longitude [20](- "#destinationLongitude") for the destination the user wants to go
    - at this point, the request reaches TripManagementService for requesting a ride.
        - The process of accepting of rejecting the trip will happen in the following way.
        - First, check with VicinityService the list of cabs available. It should be [non-zero](- "?=getVehiclesInVicnity(#latitude,#longitude)")
            - This one would be ideally coming from the geo server search. Here it is mocked.
        - Now that a vehicle is available, the rest of the steps need to be done.
            - send the destination details to the RoutingService to calculate the [route](- "?=calculateRoute(#destinationLatitude, #destinationLongitude)")
            - send the route details to the TimingService to calculate the time as [100](- "?=calculateTime()")
            - send the route details to the FareCalculationService to calculate the fare as [10.0](- "?=calculateFare()")
            - send the event to route the vehicle to move it to [RIDECONFIRMED](- "?=bookRide()") state