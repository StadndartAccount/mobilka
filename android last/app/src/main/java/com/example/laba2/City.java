package com.example.laba2;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("Population")
    private int population;
    @SerializedName("Country")
    private String country;
    @SerializedName("Name")
    private String name;
    private String language;

    public int getPopulation() {
        return population;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }
}