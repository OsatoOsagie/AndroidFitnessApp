package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.SerializedName;

public class Workout_DB {
    @SerializedName("Id")
    private String Id;

    @SerializedName("exercise_name")
    private String ex_name;

    @SerializedName("date")
    private String workout_date;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("sets")
    private String set;

    @SerializedName("reps")
    private String rep;

    @SerializedName("weight")
    private String weight;

    public String getId() {
        return Id;
    }

    public String getEx_name() {
        return ex_name;
    }

    public String getWorkout_date() {
        return workout_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getSet() {
        return set;
    }

    public String getRep() {
        return rep;
    }

    public String getWeight() {
        return weight;
    }
}
