package com.oo115.myapplication.Workout_feature;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oo115.myapplication.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseDetailsFragment extends Fragment {


    public ExerciseDetailsFragment() {
        // Required empty public constructor
    }


    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
    public static ExerciseDetailsFragment newInstance(int bodyPartID, boolean showInput) {
        ExerciseDetailsFragment f = new ExerciseDetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("bodyPartID", bodyPartID);
        args.putBoolean("showInput", showInput);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.exercise_details_fragment, container, false);

        return view;
    }

}
