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

    // The rating assigned to the lead.
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
     * Constructs a Lead object with the specified attributes.
     *
     * @param id                The unique identifier for the lead.
     * @param source            The source of the lead.
     * @param status            The current status of the lead.
     * @param reason_disqualified The reason for disqualification (if any).
     * @param type              The type of lead.
     * @param vendor_id         The vendor identifier associated with the lead.
     * @param linkedin          The LinkedIn profile URL of the lead.
     * @param role              The role of the lead.
     * @param rating            The rating of the lead.
     * @param company_id        The identifier of the company associated with the lead.
     */
    public Lead(String id, String source, String status, String reason_disqualified, String type,
                String vendor_id, String linkedin, String role, String rating, String company_id) {
        this.id = id;
        this.source = source;
        this.status = status;
        this.reason_disqualified = reason_disqualified;
        this.type = type;
        this.vendor_id = vendor_id;
        this.linkedin = linkedin;
        this.role = role;
        this.rating = rating;
        this.company_id = company_id;
    }
// testing gethup with that line
    /**
     * Returns a string representation of the Lead object.
     * This method is meant to provide a string representation of the Lead object for easy display or usage in other string-based functions.
     *
     * @return A string representation of the Lead object.
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Lead Information: ").append('\n') ;
        sb.append("Lead ID : ").append(id).append('\n') ;
        sb.append("Lead source: ").append(source).append('\n');
        sb.append("Lead status: ").append(status).append('\n');
        sb.append("Lead type: ").append(type).append('\n');
        return sb.toString();
    }
}
