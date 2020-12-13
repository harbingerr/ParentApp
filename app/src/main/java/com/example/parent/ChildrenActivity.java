package com.example.parent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChildrenActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children);
        Button child1 = findViewById(R.id.child1);
        Button child2 = findViewById(R.id.child2);
        Button child3 = findViewById(R.id.child3);

        Bundle extras = getIntent().getExtras();
        String parentId = extras.getString("parentId");
        String childName1 = extras.getString("childName");
        Parent user = new Parent(parentId,childName1);


        child1.setText(user.childName1);

        child1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!child1.getText().equals("Add child..")){
                    Intent mainActivityIntent = new Intent(ChildrenActivity.this, Stats.class);
                    mainActivityIntent.putExtra("parentId",  user.parentId);
                    mainActivityIntent.putExtra("childName",  user.childName1);
                    ChildrenActivity.this.startActivity(mainActivityIntent);
                }
            }
        });

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        Log.i("[*** date ***]", dateFormat.format(date));
        return dateFormat.format(date);
    }
}