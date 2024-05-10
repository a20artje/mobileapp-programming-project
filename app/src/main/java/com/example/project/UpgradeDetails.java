package com.example.project;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class UpgradeDetails extends AppCompatActivity {

    private String name;
    private float cookiesPerSecond;
    private int cost;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_details);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            name = extras.getString("name");
            cookiesPerSecond = extras.getFloat("cookiesPerSecond");
            cost = extras.getInt("cost");
            description = extras.getString("description");


        }

    }

    public void closeActivity(View view) {
        finish();
    }
}