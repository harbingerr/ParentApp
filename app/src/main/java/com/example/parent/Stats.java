package com.example.parent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        TextView facebookTV = findViewById(R.id.facebookTV);
        TextView messengerTV = findViewById(R.id.messengerTV);
        TextView twitchTV = findViewById(R.id.twitchTV);
        TextView twitterTV = findViewById(R.id.twitterTV);
        TextView ytTV = findViewById(R.id.ytTV);
        TextView tiktokTV = findViewById(R.id.tiktokTV);
        TextView snapchatTV = findViewById(R.id.snapchatTV);
        TextView igTV = findViewById(R.id.igTV);


        Bundle extras = getIntent().getExtras();
        String parentId = extras.getString("parentId");
        String childName1 = extras.getString("childName");

        Response.Listener<String> response = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("tagconvertstr", "["+response+"]");
                    JSONObject jsonResponse = new JSONObject(response);
                    ytTV.setText(jsonResponse.getString("youtube"));
                    igTV.setText(jsonResponse.getString("instagram"));
                    snapchatTV.setText(jsonResponse.getString("snapchat"));
                    tiktokTV.setText(jsonResponse.getString("tiktok"));
                    facebookTV.setText(jsonResponse.getString("facebook"));
                    twitterTV.setText(jsonResponse.getString("twitter"));
                    twitchTV.setText(jsonResponse.getString("twitch"));
                    messengerTV.setText(jsonResponse.getString("messenger"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        GetStats setParent = new GetStats( getDateTime(), childName1 ,response);
        RequestQueue queue = Volley.newRequestQueue(Stats.this);
        queue.add(setParent);


    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Log.i("[*** date ***]", dateFormat.format(date));
        return dateFormat.format(date);
    }
}