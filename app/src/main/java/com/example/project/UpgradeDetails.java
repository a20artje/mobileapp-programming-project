package com.example.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class UpgradeDetails extends AppCompatActivity {

    private String name;
    private float cookiesPerSecond;
    private int cost;
    private String description;

    SharedPreferences cookiesPreferenceRef;
    SharedPreferences.Editor cookiesPreferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_details);

        cookiesPreferenceRef = getSharedPreferences("cookieStats", MODE_PRIVATE);
        cookiesPreferenceEditor = cookiesPreferenceRef.edit();

        Bundle extras = getIntent().getExtras();

       updateBalanceUI();

        if(extras != null) {
            name = extras.getString("name");
            cookiesPerSecond = extras.getFloat("cookiesPerSecond");
            cost = extras.getInt("cost");
            description = extras.getString("description");
            int amountOfTimesUpgraded = cookiesPreferenceRef.getInt(name, 0);
            String amountString = "You own: " + String.valueOf(amountOfTimesUpgraded) + " " + name;

            TextView nameText = findViewById(R.id.upgrade_name);
            TextView cpsText = findViewById(R.id.upgrade_cps);
            TextView costText = findViewById(R.id.upgrade_cost);
            TextView descriptionText = findViewById(R.id.upgrade_description);
            TextView amountText = findViewById(R.id.upgrade_amount);

            nameText.setText(name);
            String cpsString = String.valueOf(cookiesPerSecond) + " cookies/sec";
            cpsText.setText(cpsString);
            costText.setText(String.valueOf(cost));
            descriptionText.setText(description);
            amountText.setText(amountString);
        }


    }

    private void updateBalanceUI (){

        int cookieBalance = cookiesPreferenceRef.getInt("amountOfCookies", 0);
        TextView cookieBalanceText = findViewById(R.id.cookie_balance);
        String balance = "balance: " + String.valueOf(cookieBalance);
        cookieBalanceText.setText(balance);

    }

    public void closeActivity(View view) {
        finish();
    }

    public void purchase(View view) {

        View purchaseButton = findViewById(R.id.purchase_button);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_down);
        purchaseButton.startAnimation(animation);

        int cookieBalance = cookiesPreferenceRef.getInt("amountOfCookies", 0);;

        if(cookieBalance >= cost){

            int amountOfTimesUpgraded = cookiesPreferenceRef.getInt(name, 0);
            amountOfTimesUpgraded++;
            cookiesPreferenceEditor.putInt(name, amountOfTimesUpgraded);
            cookiesPreferenceEditor.apply();
            String amountString = "You own: " + String.valueOf(amountOfTimesUpgraded) + " " + name;
            TextView amountText = findViewById(R.id.upgrade_amount);
            amountText.setText(amountString);

            cookieBalance -= cost;
            cookiesPreferenceEditor.putInt("amountOfCookies", cookieBalance);
            cookiesPreferenceEditor.apply();
            updateBalanceUI();

        }
    }
}