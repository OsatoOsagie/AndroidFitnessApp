package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Exercise_historyResponse {

    @SerializedName("workout_one")
    @Expose
    private List<Workout_DB> workoutOne_array = null;


    @SerializedName("workout_two")
    @Expose
    private List<Workout_DB> workoutTwo_array = null;


    @SerializedName("workout_three")
    @Expose
    private List<Workout_DB> workoutThree_array = null;


    @SerializedName("workout_four")
    @Expose
    private List<Workout_DB> workoutFour_array = null;


    @SerializedName("workout_five")
    @Expose
    private List<Workout_DB> workoutFive_array = null;


    @SerializedName("workout_num")
    private String response_workout_num;

    @SerializedName("response")
    private String response;

    public String getResponse_workout_num() {
        return response_workout_num;
    }

    public List<Workout_DB> getWorkoutOne_array() {
        return workoutOne_array;
    }

    public List<Workout_DB> getWorkoutTwo_array() {
        return workoutTwo_array;
    }

    public List<Workout_DB> getWorkoutThree_array() {
        return workoutThree_array;
    }

    public List<Workout_DB> getWorkoutFour_array() {
        return workoutFour_array;
    }

    public List<Workout_DB> getWorkoutFive_array() {
        return workoutFive_array;
    }

    public String getResponse() {
        return response;
    }
}
