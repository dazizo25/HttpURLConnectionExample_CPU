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


public class ViewContactActivity extends AppCompatActivity {

    String url_api_view = "http://student01.csucleeds.com/student01/cpu/api.php?apicall=view_contact";
    FloatingActionButton buttonAddContact;
    FloatingActionButton  buttonViewLeads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);


        buttonViewLeads = findViewById(R.id.button_lead_view);
        buttonAddContact = findViewById(R.id.button_add_contact);
        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewContactActivity.this, NewContactActivity.class));
            }
        });
        buttonViewLeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewContactActivity.this, MainActivity.class));
                //startActivity(new Intent(ViewContactActivity.this, ViewLeadActivity.class));
            }

        });

    }

    public void onResume() {
        super.onResume();

        URLConnectionGetHandler uRLConnectionGetHandler = new URLConnectionGetHandler();
        uRLConnectionGetHandler.setDataDownloadListener(new URLConnectionGetHandler.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(Object data) {
                ListView listView = (ListView) findViewById(R.id.listView);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewContactActivity.this,
                        android.R.layout.simple_list_item_1, jsonDecoder((String) data));

                if (listView != null) {
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void dataDownloadFailed() {
                Toast.makeText(ViewContactActivity.this, "No records found.", Toast.LENGTH_SHORT).show();

            }
        });
        uRLConnectionGetHandler.execute(url_api_view);
    }
    private List<String> jsonDecoder(String json_string) {
        try {
            json_string = json_string.substring(json_string.indexOf("{"));

            List<String> items = new ArrayList<>();
            JSONObject root = new JSONObject(json_string);

            JSONArray array = root.getJSONArray("contact");

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
                items.add(newContact.toString());
            }
            return items;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
