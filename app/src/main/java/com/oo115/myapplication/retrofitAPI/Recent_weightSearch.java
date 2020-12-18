package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.SerializedName;

public class Recent_weightSearch {

    @SerializedName("user_weight")
    private String user_weight;

    @SerializedName("user_fat")
    private String user_fat;

    @SerializedName("user_muscle")
    private String user_muscle;

    @SerializedName("user_water")
    private String user_water;

    @SerializedName("response")
    private String response;

    public String getUser_weight() {
        return user_weight;
    }

    public String getUser_fat() {
        return user_fat;
    }

    public String getUser_muscle() {
        return user_muscle;
    }

    public String getUser_water() {
        return user_water;
    }

    public String getResponse() {
        return response;
    }
}
