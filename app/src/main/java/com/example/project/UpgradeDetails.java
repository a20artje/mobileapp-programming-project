package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

            TextView nameText = findViewById(R.id.upgrade_name);
            TextView cpsText = findViewById(R.id.upgrade_cps);
            TextView costText = findViewById(R.id.upgrade_cost);
            TextView descriptionText = findViewById(R.id.upgrade_description);

            nameText.setText(name);
            cpsText.setText(String.valueOf(cookiesPerSecond));
            costText.setText(String.valueOf(cost));
            descriptionText.setText(description);
        }

    }

    public void closeActivity(View view) {
        finish();
    }
}