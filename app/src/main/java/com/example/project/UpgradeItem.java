package com.example.project;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("WeakerAccess")
public class UpgradeItem {

    @SerializedName("ID")
    private String Id;
    private String name;

    @SerializedName("category")
    private float cookiesPerSecond;
    @SerializedName("cost")
    private int cost;
    @SerializedName("size")
    private int costIncrease;
    @SerializedName("auxdata")
    private String description;

    public String getName(){
        return name;
    }

    public float getCookiesPerSecond() {
        return cookiesPerSecond;
    }

    public int getCost() {
        return cost;
    }

    public int getCostIncrease() {
        return costIncrease;
    }

    public String getDescription() {
        return description;
    }
}
