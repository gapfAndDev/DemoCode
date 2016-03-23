package com.developer.codingchallenge.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {
    public void setAll(String all) {
        this.all = all;
    }

    @SerializedName("all")
    @Expose
    private String all;

    public String getAll() {
        return all;
    }
}
