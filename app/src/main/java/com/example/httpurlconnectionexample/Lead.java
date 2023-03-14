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


    public Lead(String id, String source, String status, String type) {
        this.id = id;
        this.source = source;
        this.status = status;
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Lead{");
        sb.append("id='").append(id).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
