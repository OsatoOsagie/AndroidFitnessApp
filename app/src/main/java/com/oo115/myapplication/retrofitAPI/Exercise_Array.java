package com.oo115.myapplication.retrofitAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exercise_Array {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("Muscle_group")
    @Expose
    private String muscle_group;

    @SerializedName("images")
    @Expose
    private String image;


    @SerializedName("gif")
    @Expose
    private String gif;

    public String getGif() {
        return gif;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMuscle_group() {
        return muscle_group;
    }

    public String getImage() {
        return image;
    }


}
