package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.SerializedName;

public class FavouritesDB {
    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }


}