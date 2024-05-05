package com.example.httpurlconnectionexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/*
 * This is an Android application that demonstrates how to use HttpURLConnection
 * to make an HTTP GET request to a RESTful API and parse the JSON response.
 * The application retrieves a list of leads from a remote server and displays
 * them in a ListView. The user can also add a new lead by clicking on the
 * floating action button.
 */
/**
 * Main activity of the application.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * URL of the API endpoint to retrieve data from.
     */
    String url_api_view = "http://student01.csucleeds.com/student01/cpu/api.php?apicall=view";

    /**
     * Floating action button to add a new lead.
     */
    FloatingActionButton fab;

    /**
     * Button to view contacts.
     */
    FloatingActionButton buttonAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize the floating action button and set its click listener.
         */
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Start the NewLeadActivity when the floating action button is clicked.
                 */
                Intent intent = new Intent(getBaseContext(), NewLeadActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Initialize the button to view contacts and set its click listener.
         */
        buttonAddContact = findViewById(R.id.button_add_contact);
        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Start the ViewContactActivity when the button is clicked.
                 */
                Intent intent = new Intent(getBaseContext(), ViewContactActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Called when the activity is resumed.
     */
    public void onResume() {
        super.onResume();

        /**
         * Create an instance of URLConnectionGetHandler to handle the API request.
         */
        URLConnectionGetHandler uRLConnectionGetHandler = new URLConnectionGetHandler();
        uRLConnectionGetHandler.setDataDownloadListener(new URLConnectionGetHandler.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(Object data) {
                /**
                 * Get the ListView and set its adapter with the decoded JSON data.
                 */
                ListView listView = (ListView) findViewById(R.id.listView);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, jsonDecoder((String) data));

                if (listView!= null) {
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void dataDownloadFailed() {
                /**
                 * Show a toast message if the data download fails.
                 */
                Toast.makeText(MainActivity.this, "No records found.", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * Execute the URLConnectionGetHandler with the API URL.
         */
        uRLConnectionGetHandler.execute(url_api_view);
    }

    /**
     * Decodes the JSON response from the API and returns a list of strings.
     *
     * @param json_string JSON response from the API
     * @return List of strings representing the decoded JSON data
     */
    private List<String> jsonDecoder(String json_string) {
        try {
            /**
             * Remove any unnecessary characters from the JSON string.
             */
            json_string = json_string.substring(json_string.indexOf("{"));

            List<String> items = new ArrayList<>();
            JSONObject root = new JSONObject(json_string);

            JSONArray array = root.getJSONArray("leads");

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Lead newLead = new Lead(
                        object.getString("id"),
                        object.getString("source"),
                        object.getString("status"),
                        object.getString("type"));
                items.add(newLead.toString());
            }
            return items;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}