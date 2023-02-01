package com.example.httpurlconnectionexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url_api_view = "http://csucl.com/csucl.com/glynn/cpu/api.php?apicall=view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URLConnectionGetHandler uRLConnectionGetHandler = new URLConnectionGetHandler();
        uRLConnectionGetHandler.setDataDownloadListener(new URLConnectionGetHandler.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(Object data) {
                // handler result
                Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_SHORT).show();

                List<Lead> leads = jsonDecoder(data);
                String s = null;
                for (Lead l: leads) {
                    s = s + l.source + ", ";
                }
                Toast.makeText(MainActivity.this, "Leads: "+ s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void dataDownloadFailed() {
                // handler failure (e.g network not available etc.)
            }
        });
        uRLConnectionGetHandler.execute(url_api_view);
    }

    public List<Lead> jsonDecoder(Object data) {
        String jsonString = data.toString();
        jsonString = jsonString.substring(jsonString.indexOf("{"));
        List<Lead> leads = new ArrayList<>();
        JSONObject jsonObj = null;

        try {
            jsonObj = new JSONObject(jsonString);

            // A JSON array with the the names of all the cities
            JSONArray source_list = jsonObj.names();
            Toast.makeText(this, source_list.toString(), Toast.LENGTH_SHORT).show();
            for (int i = 0; i < jsonObj.length(); i++) {
                String lead = null;
                try {
                    lead = source_list.getString(i);
                    Toast.makeText(this, lead.toString(), Toast.LENGTH_SHORT).show();
                    JSONObject jsonSource = jsonObj.getJSONObject(lead);
                    JSONObject jsonPosition = jsonSource.getJSONObject("leads");

                    try {
                        leads.add(new Lead(
                                jsonPosition.getString("source"),
                                jsonPosition.getString("status")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return leads;
    }

}
