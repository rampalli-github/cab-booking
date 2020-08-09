package com.demo.booking.cabbooking.model;

/**
 * Contains the details of a user who wants to book a cab
 */
public class User {
    private String name;
    private ContactDetails contactDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
