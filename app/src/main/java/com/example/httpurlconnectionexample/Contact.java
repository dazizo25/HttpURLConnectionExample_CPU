package com.example.httpurlconnectionexample;

import androidx.annotation.NonNull;

public class Contact {
    @NonNull
    String id;
    String companyName;
    String contactName;
    String title;
    String role;
    String phoneNumber;
    String extensionNumber;
    String mobileNumber;
    String emailAddress;
    String  street;
    String city;
    String county;
    String postCode;
    String status;
    String annualRevenue;


    public Contact(@NonNull String id, String companyName, String contactName, String title,
                   String role, String phoneNumber, String extensionNumber, String mobileNumber,
                   String emailAddress, String street, String city, String county, String postCode,
                   String status, String annualRevenue) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", title='" + title + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", extensionNumber='" + extensionNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", postCode='" + postCode + '\'' +
                ", status='" + status + '\'' +
                ", annualRevenue='" + annualRevenue + '\'' +
                '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getContactName() {
        return contactName;
    }


    public void setContactName(String contactName) {
        this.contactName = contactName;
    }}



















