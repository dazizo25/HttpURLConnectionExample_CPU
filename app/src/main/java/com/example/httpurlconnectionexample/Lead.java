package com.example.httpurlconnectionexample;

/**
 * Represents a lead entity with various attributes.
 * This class includes fields such as id, source, status, etc.
 */
public class Lead {

    /** The unique identifier of the lead. */
    String id;

    /** The source from which the lead was acquired. */
    String source;

    /** The current status of the lead. */
    String status;

    /** The reason for disqualification, if applicable. */
    String reason_disqualified; // This field seems unused in the current code.

    /** The type of the lead. */
    String type;

    /** The vendor ID associated with the lead. */
    String vendor_id; // There's no usage of vendor_id in the provided code.

    /** The LinkedIn profile of the lead. */
    String linkedin;

    /** The role/title of the lead. */
    String role;

    /** The rating assigned to the lead. */
    String rating;

    /** The identifier of the company associated with the lead. */
    String company_id; // There's no usage of company_id in the provided code.

    /**
     * Constructs a Lead object with the specified attributes.
     *
     * @param id The unique identifier of the lead.
     * @param source The source from which the lead was acquired.
     * @param status The current status of the lead.
     * @param type The type of the lead.
     */
    public Lead(String id, String source, String status, String type) {
        this.id = id;
        this.source = source;
        this.status = status;
        this.type = type;
    }

    /**
     * Returns a string representation of the Lead object.
     * This method is meant to provide a string representation of the Lead object for easy display or usage in other string-based functions.
     *
     * @return A string representation of the Lead object.
     */
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
