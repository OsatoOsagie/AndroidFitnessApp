package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Custom_plan_searchResponse {

    @SerializedName("custom_plan_array")
    @Expose
    private List<Custom_planArray> custom_planArraysArray = null;


    @SerializedName("response")
    @Expose
    private String response;

    public List<Custom_planArray> getCustom_planArraysArray() {
        return custom_planArraysArray;
    }

    public String getResponse() {
        return response;
    }
}
