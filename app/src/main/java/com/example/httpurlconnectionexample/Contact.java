package com.example.httpurlconnectionexample;

import androidx.annotation.NonNull;
/*
 * Contact.java - A Java class representing a contact with various details.
 *
 * This class is used to store and manage information about a contact, such as
 * the contact's name, company name, title, role, phone numbers, email address,
 * and physical address. The class provides constructors, getter and setter
 * methods, and a toString() method for easy manipulation and display of the
 * contact information.
 *
 * The Contact class is part of the com.example.httpurlconnectionexample package,
 * which demonstrates how to use HttpURLConnection in Android to make HTTP requests
 * and handle responses.
 *
 * This class is  a simple and flexible way to manage contact information.
 */
public class Contact {
    // A unique identifier for the contact
    @NonNull
    String id;

    // The name of the company the contact is associated with
    String companyName;

    // The name of the contact
    String contactName;

    // The title of the contact (e.g. "Manager", "Director")
    String title;

    // The role of the contact within the company
    String role;

    // The phone number of the contact
    String phoneNumber;

    // The extension number for the contact's phone number
    String extensionNumber;

    // The mobile number of the contact
    String mobileNumber;

    // The email address of the contact
    String emailAddress;

    // The street address of the company
    String street;

    // The city where the company is located
    String city;

    // The county where the company is located
    String county;

    // The postal code for the company's address
    String postCode;

    // The status of the contact (e.g. "Active", "Inactive")
    String status;

    // The annual revenue of the company
    String annualRevenue;

    // Constructor for the Contact class
    public Contact(@NonNull String id, String companyName, String contactName, String title,
                   String role, String phoneNumber, String extensionNumber, String mobileNumber,
                   String emailAddress, String street, String city, String county, String postCode,
                   String status, String annualRevenue) {
        // Initialize the id field with the provided id
        this.id = id;

        // Initialize the other fields with the provided values
        this.companyName = companyName;
        this.contactName = contactName;
        this.title = title;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.extensionNumber = extensionNumber;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.street = street;
        this.city = city;
        this.county = county;
        this.postCode = postCode;
        this.status = status;
        this.annualRevenue = annualRevenue;
    }

    // Override the toString() method to provide a string representation of the Contact object
    @Override
    public String toString() {
        return "The Contact Information: " +'\n' +
                "Contact id: " + id + '\n' +
                "Company Name: " + companyName + '\n' +
                "Contact Name: " + contactName + '\n' +
                "Title: " + title + '\n' +
                "Role: " + role + '\n' +
                "Phone Number: " + phoneNumber + '\n' +
                "Extension Number: " + extensionNumber + '\n' +
                "Mobile Number: " + mobileNumber + '\n' +
                "Email Address: " + emailAddress + '\n' +
                "Street: " + street + '\n' +
                "City: " + city + '\n' +
                "County: " + county + '\n' +
                "Post Code: " + postCode + '\n' +
                "Status: " + status + '\n' +
                "Annual Revenue: " + annualRevenue + '\n';
    }

    // Getter method for the id field
    public String getId() {
        return id;
    }

    // Setter method for the id field
    public void setId(String id) {
        this.id = id;
    }

    // Getter method for the companyName field
    public String getCompanyName() {
        return companyName;
    }

    // Setter method for the companyName field
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // Getter method for the contactName field
    public String getContactName() {
        return contactName;
    }

    // Setter method for the contactName field
    public void setContactName(String contactName) {
        this.contactName = contactName;}}