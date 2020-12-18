package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Favourites_search_response {

    @SerializedName("favourites_array")
    @Expose
    private List<Favourites_Array> favourites = null;


    @SerializedName("response")
    @Expose
    private String response = "test";


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Favourites_Array> getFavourites() {
        return favourites;
    }

}
