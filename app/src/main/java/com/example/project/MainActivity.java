package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a20artje";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        upgrades = new ArrayList<>(Arrays.asList(
                new UpgradelistItem("Cursor"),
                new UpgradelistItem("Grandma"),
                new UpgradelistItem("Farm")
        ));

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

}
