package com.oo115.myapplication.Workout_feature;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oo115.myapplication.R;

import java.util.Objects;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Exercise_description_tab extends Fragment {


    public Exercise_description_tab() {
        // Required empty public constructor
    }

    public static Exercise_description_tab newInstance(String exerciseName, String desc, boolean showInput) {
        Exercise_description_tab f = new Exercise_description_tab();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("exerciseName", exerciseName);
        args.putString("desc", desc);
        args.putBoolean("showInput", showInput);
        f.setArguments(args);

        return f;

    }

    private TextView title, description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_exercise_description, container, false);

        title = view.findViewById(R.id.ex_title);
        description = view.findViewById(R.id.ex_desc);

        String extras = Objects.requireNonNull(getArguments()).getString("exerciseName");
        String exerciseDesc = Objects.requireNonNull(getArguments()).getString("desc");

        title.setText(extras);
        description.setText(exerciseDesc);


        return view;
    }

}
