package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<UpgradelistItem> upgrades = new ArrayList<>(Arrays.asList(
                new UpgradelistItem("Cursor"),
                new UpgradelistItem("Grandma"),
                new UpgradelistItem("Farm")
        ));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, upgrades, new RecyclerViewAdapter.OnClickListener(){
            @Override
            public void onClick(UpgradelistItem upgrade) {
                //TODO open detail activity
            }
        });
        
        RecyclerView view = findViewById(R.id.upgrades_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
    }

}
