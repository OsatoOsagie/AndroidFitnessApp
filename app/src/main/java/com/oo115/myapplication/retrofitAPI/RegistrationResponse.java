package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegistrationResponse {

    @SerializedName("response")
    @Expose
    private String response;


    @SerializedName("user_details")
    @Expose
    private List<UserDB> user_details = null;

    public String getResponse() {
        return response;
    }

    public List<UserDB> getUser_details() {
        return user_details;
    }
}
