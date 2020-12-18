package com.oo115.myapplication.Workout_feature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.oo115.myapplication.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class WorkoutExercisesFragment extends Fragment {
    ListView exercises_lv;
    //data source for the individual muscle groups
    String[] exerciseGroups = {"Triceps", "Chest", "Shoulders", "Biceps", "Core", "Back", "Legs", "Show All"};
    //data source for the images relating to each muscle group
    Integer[] groupImage = {R.drawable.triceps, R.drawable.chest, R.drawable.shoulders, R.drawable.bicep, R.drawable.core,
            R.drawable.back, R.drawable.quads, R.drawable.cardio, R.drawable.showall};


    /**
     * this method creates an instance of the exercise_for_muscle_groups fragment for the muscle group that is clicked
     */
    private AdapterView.OnItemClickListener onClickListItem = (parent, view, position, id) -> {

        int muscle_group = position;

        Exercises_for_muscle_groupsFragment fragment = Exercises_for_muscle_groupsFragment.newInstance(muscle_group, true);
        String backStateName = fragment.getClass().getName();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_containerHome, fragment);
        transaction.addToBackStack(backStateName);

        // Commit the transaction
        transaction.commit();

    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        //setting the title of the page
        Objects.requireNonNull(getActivity()).setTitle("Workout");

        exercises_lv = view.findViewById(R.id.exercisesListView);
        //declaring adaptor for listView
        MuscleGroupListView muscleGroupListView = new MuscleGroupListView(this.getActivity(), exerciseGroups, groupImage);
        exercises_lv.setAdapter(muscleGroupListView);
        exercises_lv.setOnItemClickListener(onClickListItem);

        return view;
    }


//    @Override
//    public void onDestroyView() {
//        //mContainer.removeAllViews();
//        ViewGroup mContainer = (ViewGroup) getActivity().findViewById(R.id.container);
//        mContainer.removeAllViews();
//        super.onDestroyView();
//    }




}
