package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weight_Array {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("measurement")
    @Expose
    private String measurement;

    @SerializedName("user_id")
    @Expose
    private String user_id;


    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getUser_id() {
        return user_id;
    }
}
