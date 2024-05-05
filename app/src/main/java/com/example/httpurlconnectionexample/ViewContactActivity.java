package com.example.httpurlconnectionexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * This activity displays a list of contacts retrieved from a remote API.
 * It also provides floating action buttons to add new contacts and view leads.
 * When the activity is resumed, it sends a GET request to the API to retrieve the contact list,
 * and then decodes the JSON response to display the contacts in a list view.
 */
/**
 * Activity to view contacts
 */
public class ViewContactActivity extends AppCompatActivity {

    // API URL to view contacts
    String url_api_view = "http://student01.csucleeds.com/student01/cpu/api.php?apicall=view_contact";

    // Floating action buttons for adding new contacts and viewing leads
    FloatingActionButton buttonAddContact;
    FloatingActionButton buttonViewLeads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        // Initialize floating action buttons
        buttonViewLeads = findViewById(R.id.button_lead_view);
        buttonAddContact = findViewById(R.id.button_add_contact);

        // Set click listeners for floating action buttons
        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start new contact activity when add contact button is clicked
                startActivity(new Intent(ViewContactActivity.this, NewContactActivity.class));
            }
        });
        buttonViewLeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start main activity when view leads button is clicked
                startActivity(new Intent(ViewContactActivity.this, MainActivity.class));
                //startActivity(new Intent(ViewContactActivity.this, ViewLeadActivity.class));
            }
        });
    }

    /**
     * Called when the activity is resumed
     */
    public void onResume() {
        super.onResume();

        // Create a new instance of URLConnectionGetHandler to handle API requests
        URLConnectionGetHandler uRLConnectionGetHandler = new URLConnectionGetHandler();

        // Set data download listener to handle API response
        uRLConnectionGetHandler.setDataDownloadListener(new URLConnectionGetHandler.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(Object data) {
                // Get the list view to display contacts
                ListView listView = (ListView) findViewById(R.id.listView);

                // Create an array adapter to display contacts in the list view
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewContactActivity.this,
                        android.R.layout.simple_list_item_1, jsonDecoder((String) data));

                // Set the adapter to the list view
                if (listView!= null) {
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void dataDownloadFailed() {
                // Show a toast message if no records are found
                Toast.makeText(ViewContactActivity.this, "No records found.", Toast.LENGTH_SHORT).show();
            }
        });
        // Execute the API request to view contacts
        uRLConnectionGetHandler.execute(url_api_view);
    }

    /**
     * Decodes JSON response from API and returns a list of contact strings
     *
     * @param json_string JSON response from API
     * @return List of contact strings
     */
    private List<String> jsonDecoder(String json_string) {
        try {
            // Remove any unnecessary characters from the JSON string
            json_string = json_string.substring(json_string.indexOf("{"));

            // Create a list to store contact strings
            List<String> items = new ArrayList<>();

            // Parse the JSON response
            JSONObject root = new JSONObject(json_string);

            // Get the contact array from the JSON response
            JSONArray array = root.getJSONArray("contact");

            // Iterate through the contact array and create Contact objects
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Contact newContact = new Contact(
                        object.getString("id"),
                        object.getString("company_name"),
                        object.getString("contact_name"),
                        object.getString("title"),
                        object.getString("role"),
                        object.getString("phone_number"),
                        object.getString("extension_number"),
                        object.getString("mobile_number"),
                        object.getString("email_address"),
                        object.getString("street"),
                        object.getString("city"),
                        object.getString("county"),
                        object.getString("postcode"),
                        object.getString("status"),
                        object.getString("annual_revenue"));
                // Add the contact string to the list
                items.add(newContact.toString());
            }
            return items;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}