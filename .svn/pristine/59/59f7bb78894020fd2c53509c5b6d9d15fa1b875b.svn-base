package com.oo115.myapplication.Workout_feature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.oo115.myapplication.R;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Select_Muscle_GroupFragment extends Fragment {

    public Select_Muscle_GroupFragment() {
        // Required empty public constructor
    }

    TabLayout tabs;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selectmuscle, container, false);


        ViewPager viewPager = view.findViewById(R.id.viewpager_exercises);
        setupViewPager(viewPager);

        tabs = view.findViewById(R.id.result_tabs_exercises);
        tabs.setupWithViewPager(viewPager);

//        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
//        toolbar = view.findViewById(R.id.toolbar_exercises_muscle);
//
//
//
//
////        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
//
////        String extras = getArguments().getString("exerciseName");
//        toolbar.setTitle("test");
//        toolbar.setNavigationIcon(R.drawable.ic_back);
//        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        return view;
    }


    private void setupViewPager(ViewPager viewPager) {


        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new WorkoutExercisesFragment(), "Workout");
        adapter.addFragment(new Favourite_exercises_Fragment(), "Favourites");
        adapter.addFragment(new Plans_Tab(), "Plans");
        viewPager.setAdapter(adapter);


    }
}
