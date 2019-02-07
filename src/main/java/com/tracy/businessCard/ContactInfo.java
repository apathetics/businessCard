package com.tracy.businessCard;

// We can use Lombok annotations to streamline boilerplate code like getters/setters here for neater code.
// I will go ahead and leave this in for readability since there are only three fields.

// Since this is a simple model with a constructor/getter, there is no need to unit test this class.
// Validation is dealt with by error code.
public class ContactInfo {

    private String name;
    private String phoneNumber;
    private String emailAddress;

    // validation for case where we don't have name || phone || email
    public ContactInfo(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
