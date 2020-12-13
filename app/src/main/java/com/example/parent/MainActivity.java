package com.example.parent;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_next = findViewById(R.id.button);
        EditText et_id = findViewById(R.id.childId);

        /*NotificationCompat.Builder builder = new NotificationCompat.Builder(
                MainActivity.this
        ).setSmallIcon(R.drawable.facebook)
                .setContentTitle("FB VELAA")
                .setContentInfo("Vela si bol dnes")
                .setAutoCancel(true);
        Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message","hmhm");

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
        */
        Gson gson = new Gson();
        SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
        String json = mPrefs.getString("UserData", "");

        if(!json.isEmpty()){
            Parent user = gson.fromJson(json, Parent.class);
            Intent mainActivityIntent = new Intent(MainActivity.this, ChildrenActivity.class);
            mainActivityIntent.putExtra("parentId",  user.parentId);
            mainActivityIntent.putExtra("childName",  user.childName1);
            MainActivity.this.startActivity(mainActivityIntent);
        }

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<String> response = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("tagconvertstr", "["+response+"]");
                            JSONObject jsonResponse = new JSONObject(response);
                            String userId = jsonResponse.getString("id");
                            String childName = jsonResponse.getString("name");

                            if(childName != "nothing") {
                                Parent user = new Parent(userId, childName);
                                SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
                                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                                Gson gson = new Gson();
                                String json = gson.toJson(user);
                                prefsEditor.putString("UserData", json);
                                prefsEditor.commit();

                                Intent mainActivityIntent = new Intent(MainActivity.this, ChildrenActivity.class);
                                mainActivityIntent.putExtra("parentId",  userId);
                                mainActivityIntent.putExtra("childName",  childName);
                                MainActivity.this.startActivity(mainActivityIntent);
                            } else {
                                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                                alert.setMessage("Dieta sa nenašlo").setNegativeButton("Retry", null).create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                SetParent setParent = new SetParent( et_id.getText().toString() ,response);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(setParent);

            }
        });
    }
}