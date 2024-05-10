package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private ArrayList<UpgradeItem> upgrades;
    private RecyclerViewAdapter adapter;
    private DrawerLayout drawerLayout;
    private ImageButton cookieButton;

    private TextView amountOfCookiesText;
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a20artje";

    SharedPreferences cookiesPreferenceRef;
    SharedPreferences.Editor cookiesPreferenceEditor;
    private int amountOfCookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        cookieButton = findViewById(R.id.cookie_button);

        amountOfCookiesText = findViewById(R.id.number_of_cookies);

        setUpRecyclerView();
        setUpPreferences();
    }

    private void setUpPreferences(){
        cookiesPreferenceRef = getSharedPreferences("cookieStats", MODE_PRIVATE);

        cookiesPreferenceEditor = cookiesPreferenceRef.edit();

        int amountOfCookies = cookiesPreferenceRef.getInt("amountOfCookies", 0);
        amountOfCookiesText.setText(String.valueOf(amountOfCookies));
    }

    private void setUpRecyclerView(){
        upgrades = new ArrayList<>();

        adapter = new RecyclerViewAdapter(this, upgrades, new RecyclerViewAdapter.OnClickListener(){
            @Override
            public void onClick(UpgradeItem upgrade) {
                Intent intent = new Intent(MainActivity.this, UpgradeDetails.class);
                intent.putExtra("name", upgrade.getName());
                intent.putExtra("cost", upgrade.getCost());
                intent.putExtra("cookiesPerSecond", upgrade.getCookiesPerSecond());
                intent.putExtra("description", upgrade.getDescription());
                startActivity(intent);
            }
        });

        RecyclerView view = findViewById(R.id.upgrades_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        new JsonTask(this).execute(JSON_URL);
    }
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<UpgradeItem>>() {}.getType();
        List<UpgradeItem> listOfUpgrades = gson.fromJson(json, type);

        upgrades.addAll(listOfUpgrades);

        adapter.notifyDataSetChanged();
    }

    public void openAbout(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);

    }

    public void exitNavMenu(View view) {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void openUpgradeMenu(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void cookieClicked(View view) {

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_down);
        cookieButton.startAnimation(animation);

        int amountOfCookies = cookiesPreferenceRef.getInt("amountOfCookies", 0);
        amountOfCookies++;
        cookiesPreferenceEditor.putInt("amountOfCookies", amountOfCookies);
        cookiesPreferenceEditor.apply();
        amountOfCookiesText.setText(String.valueOf(amountOfCookies));
    }
}
