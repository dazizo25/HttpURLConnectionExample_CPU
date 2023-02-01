package com.example.httpurlconnectionexample;

public class Lead {
    String id;
    String source;
    String status;
    String reason_disqualified;
    String type;
    String vendor_id;
    String linkedin;
    String role;
    String rating;
    String company_id;


    public Lead(String source, String status) {
        this.source=source;
        this.status=status;
    }
}
