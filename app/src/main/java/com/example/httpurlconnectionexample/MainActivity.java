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

    String json_string = "{\n" +
            "  \"title\":\"JSONParserTutorial\",\n" +
            "  \"array\":[\n" +
            "    {\n" +
            "    \"company\":\"Google\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"company\":\"Facebook\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"company\":\"LinkedIn\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"company\" : \"Microsoft\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"company\": \"Apple\"\n" +
            "    }\n" +
            "    ],\n" +
            "    \"nested\":{\n" +
            "    \"flag\": true,\n" +
            "    \"random_number\":1\n" +
            "    }\n" +
            "}";

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

                jsonDecoder(json_string);
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

            ListView listView = (ListView) findViewById(R.id.listView);

            List<String> items = new ArrayList<>();
            JSONObject root = new JSONObject(json_string);

            JSONArray array= root.getJSONArray("array");

            this.setTitle(root.getString("title"));

            for(int i=0;i<array.length();i++)
            {
                JSONObject object= array.getJSONObject(i);
                items.add(object.getString("company"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, items);

            if (listView != null) {
                listView.setAdapter(adapter);
            }

            JSONObject nested= root.getJSONObject("nested");
            Log.d("TAG","flag value "+nested.getBoolean("flag"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
