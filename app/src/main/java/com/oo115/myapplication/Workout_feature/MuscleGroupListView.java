package com.oo115.myapplication.Workout_feature;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oo115.myapplication.BodyPart;
import com.oo115.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MuscleGroupListView extends ArrayAdapter<String> implements View.OnClickListener {
    String[] exerciseGroups;
    Integer[] groupImage;
    private Activity context;


    public MuscleGroupListView(Activity context, String[] exerciseGroups, Integer[] groupImage) {
        super(context, R.layout.muscle_group_layout, exerciseGroups);

        this.context = context;
        this.exerciseGroups = exerciseGroups;
        this.groupImage = groupImage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            //build in class to convert xml file to corressponding java object
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.muscle_group_layout, null, true);
            viewHolder = new ViewHolder(view);
            //view class method which stores view
            view.setTag(viewHolder);
        } else {
            //this gets the stored method in set tag
            viewHolder = (ViewHolder) view.getTag();
        }
        //setting the reference for the image and text view
        viewHolder.group_image.setImageResource(groupImage[position]);
        viewHolder.musclegroup_name.setText(exerciseGroups[position]);
        return view;
    }


    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        BodyPart dataModel = (BodyPart) object;
    }


    //to optimise list viw performance
    //holds and finds view by id of image and name

    class ViewHolder {
        TextView musclegroup_name;
        ImageView group_image;

        ViewHolder(View view) {
            musclegroup_name = view.findViewById(R.id.musclGroup_name);
            group_image = view.findViewById(R.id.muscleGroup_IMV);
        }
    }
}