package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

    private ArrayList<UpgradelistItem> upgrades;
    private RecyclerViewAdapter adapter;
    private DrawerLayout drawerLayout;
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a20artje";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);

        setUpRecyclerView();



    }

    private void setUpRecyclerView(){
        upgrades = new ArrayList<>();

        adapter = new RecyclerViewAdapter(this, upgrades, new RecyclerViewAdapter.OnClickListener(){
            @Override
            public void onClick(UpgradelistItem upgrade) {
                //TODO open detail activity
            }
        });

        RecyclerView view = findViewById(R.id.upgrades_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        new JsonTask(this).execute(JSON_URL);
    }
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Gson gson = new Gson();
        Type type = new TypeToken<List<UpgradeItem>>() {}.getType();
        List<UpgradeItem> listOfUpgrades = gson.fromJson(json, type);

        for(UpgradeItem upgradeItem : listOfUpgrades){
            upgrades.add(new UpgradelistItem(upgradeItem.getName()));
        }
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
}
