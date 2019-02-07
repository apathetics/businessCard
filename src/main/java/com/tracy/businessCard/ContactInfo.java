package com.tracy.businessCard;

// We can use Lombok annotations to streamline boilerplate code like getters/setters here for neater code.
// I will go ahead and leave this in for readability since there are only three fields.

// Since this is a simple model with a constructor/getter, there is no need to unit test this class.
// Unless there is an empty field? Keep in mind for later consideration.
public class ContactInfo {

    private String name;
    private String phoneNumber;
    private String emailAddress;

    public ContactInfo() {
    }

    // Test for case where we don't have name || phone || email
    public ContactInfo(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
