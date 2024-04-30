package com.example.httpurlconnectionexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewContactActivity extends AppCompatActivity {

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

    private String urlInsertContact = "http://your-api-endpoint.com/insert-contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        initViews();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLConnectionPostHandler urlConnectionPostHandler = new URLConnectionPostHandler();
                urlConnectionPostHandler.setDataDownloadListener(new URLConnectionPostHandler.DataDownloadListener() {
                    @Override
                    public void dataDownloadedSuccessfully(Object data) {
                        Toast.makeText(NewContactActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void dataDownloadFailed() {
                        Toast.makeText(NewContactActivity.this, "Record not added.", Toast.LENGTH_SHORT).show();
                    }
                });
                urlConnectionPostHandler.execute(urlInsertContact, generateParameters());
            }
        });
    }

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
        buttonSubmit = findViewById(R.id.button_submit);
    }

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