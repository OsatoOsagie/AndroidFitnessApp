package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Exercises_response {


    @SerializedName("exercises_array")
    @Expose
    private List<Exercise_Array> exercisesArray = null;


    @SerializedName("response")
    @Expose
    private String response;


    public List<Exercise_Array> getExercisesArray() {
        return exercisesArray;
    }

    public String getResponse() {
        return response;
    }
}
