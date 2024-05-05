package com.example.httpurlconnectionexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
// This code defines a class named NewContactActivity that extends the AppCompatActivity class.
// The NewContactActivity class is responsible for creating a new contact in an Android application.
// It initializes various EditText fields and a Button for user input, and sets up an onClickListener
// for the Button to submit the contact information to a server using the URLConnectionPostHandler class.
// The URLConnectionPostHandler class is an asynchronous task that sends an HTTP POST request to a server
// with the contact information and receives a response. The response is then displayed to the user in a Toast message.

/**
 * This activity is responsible for creating a new contact.
 */
public class NewContactActivity extends AppCompatActivity {

    // Declare variables for all the EditText fields
    private EditText editTextCompanyName;
    private EditText editTextContactName;
    private EditText editTextTitle;
    private EditText editTextRole;
    private EditText editTextPhoneNumber;
    private EditText editTextExtensionNumber;
    private EditText editTextMobileNumber;
    private EditText editTextEmailAddress;
    private EditText editTextStreet;
    private EditText editTextCity;
    private EditText editTextCounty;
    private EditText editTextPostCode;
    private EditText editTextStatus;
    private EditText editTextAnnualRevenue;
    private Button buttonSubmit;

    // URL for inserting a new contact
    private String urlInsertContact = "http://student01.csucleeds.com/student01/cpu/api.php?apicall=insert_contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        // Initialize all the views
        initViews();

        // Set an onClick listener for the submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new instance of URLConnectionPostHandler
                URLConnectionPostHandler urlConnectionPostHandler = new URLConnectionPostHandler();
                // Set a data download listener to handle the response
                urlConnectionPostHandler.setDataDownloadListener(new URLConnectionPostHandler.DataDownloadListener() {
                    @Override
                    public void dataDownloadedSuccessfully(Object data) {
                        // Show a toast message with the response data and finish the activity
                        Toast.makeText(NewContactActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void dataDownloadFailed() {
                        // Show a toast message if the data download fails
                        Toast.makeText(NewContactActivity.this, "Record not added.", Toast.LENGTH_SHORT).show();
                    }
                });
                // Execute the URLConnectionPostHandler with the insert contact URL and generated parameters
                urlConnectionPostHandler.execute(urlInsertContact, generateParameters());
            }
        });
    }

    /**
     * Initialize all the views in the activity.
     */
    private void initViews() {
        editTextCompanyName = findViewById(R.id.editText_companyName);
        editTextContactName = findViewById(R.id.editText_contactName);
        editTextTitle = findViewById(R.id.editText_title);
        editTextRole = findViewById(R.id.editText_role);
        editTextPhoneNumber = findViewById(R.id.editText_phoneNumber);
        editTextExtensionNumber = findViewById(R.id.editText_extensionNumber);
        editTextMobileNumber = findViewById(R.id.editText_mobileNumber);
        editTextEmailAddress = findViewById(R.id.editText_emailAddress);
        editTextStreet = findViewById(R.id.editText_street);
        editTextCity = findViewById(R.id.editText_city);
        editTextCounty = findViewById(R.id.editText_county);
        editTextPostCode = findViewById(R.id.editText_postCode);
        editTextStatus = findViewById(R.id.editText_status);
        editTextAnnualRevenue = findViewById(R.id.editText_annualRevenue);
        buttonSubmit = findViewById(R.id.contact_button_submit);
    }

    /**
     * Generate the parameters to be sent with the HTTP request.
     *
     * @return A string containing all the parameters.
     */
    private String generateParameters() {
        StringBuilder paramBuilder = new StringBuilder();
        paramBuilder.append("companyName=").append(editTextCompanyName.getText().toString());
        paramBuilder.append("&contactName=").append(editTextContactName.getText().toString());
        paramBuilder.append("&title=").append(editTextTitle.getText().toString());
        paramBuilder.append("&role=").append(editTextRole.getText().toString());
        paramBuilder.append("&phoneNumber=").append(editTextPhoneNumber.getText().toString());
        paramBuilder.append("&extensionNumber=").append(editTextExtensionNumber.getText().toString());
        paramBuilder.append("&mobileNumber=").append(editTextMobileNumber.getText().toString());
        paramBuilder.append("&emailAddress=").append(editTextEmailAddress.getText().toString());
        paramBuilder.append("&street=").append(editTextStreet.getText().toString());
        paramBuilder.append("&city=").append(editTextCity.getText().toString());
        paramBuilder.append("&county=").append(editTextCounty.getText().toString());
        paramBuilder.append("&postCode=").append(editTextPostCode.getText().toString());
        paramBuilder.append("&status=").append(editTextStatus.getText().toString());
        paramBuilder.append("&annualRevenue=").append(editTextAnnualRevenue.getText().toString());
        return paramBuilder.toString();
    }
}