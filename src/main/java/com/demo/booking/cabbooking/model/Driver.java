package com.demo.booking.cabbooking.model;

import java.util.List;

/**
 * Contains the details of a driver
 */
public class Driver {
    private String name;
    private String uniqueID;
    private String govtApprovedID;
    private String drivingLicenseID;
    private List<String> vehicles;
    private ContactDetails contactDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getGovtApprovedID() {
        return govtApprovedID;
    }

    public void setGovtApprovedID(String govtApprovedID) {
        this.govtApprovedID = govtApprovedID;
    }

    public String getDrivingLicenseID() {
        return drivingLicenseID;
    }

    public void setDrivingLicenseID(String drivingLicenseID) {
        this.drivingLicenseID = drivingLicenseID;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
