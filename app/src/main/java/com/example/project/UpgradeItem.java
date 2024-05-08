package com.example.project;

import com.google.gson.annotations.SerializedName;

public class UpgradeItem {

    @SerializedName("ID")
    private String Id;
    private String name;
    @SerializedName("Category")
    private float cookiesPerSecond;
    @SerializedName("Cost")
    private int cost;
    @SerializedName("Size")
    private int costIncrease;
    @SerializedName("Auxdata")
    private String description;

    public String getName(){
        return name;
    }
}
