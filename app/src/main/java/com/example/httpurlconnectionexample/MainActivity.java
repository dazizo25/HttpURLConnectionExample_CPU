package com.example.httpurlconnectionexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
                jsonDecoder(data.toString());
            }

            @Override
            public void dataDownloadFailed() {
                // handler failure (e.g network not available etc.)
            }
        });
        uRLConnectionGetHandler.execute(url_api_view);
    }

    public void jsonDecoder(String json_string) {

        try {
            json_string = json_string.substring(json_string.indexOf("{"));
            Toast.makeText(this, json_string, Toast.LENGTH_LONG).show();
            ListView listView = (ListView) findViewById(R.id.listView);

            List<String> items = new ArrayList<>();
            JSONObject root = new JSONObject(json_string);

            JSONArray array = root.getJSONArray("leads");

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Lead newLead = new Lead(
                        object.getString("id"),
                        object.getString("source"),
                        object.getString("status"));
                items.add(newLead.toString());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, items);

            if (listView != null) {
                listView.setAdapter(adapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
