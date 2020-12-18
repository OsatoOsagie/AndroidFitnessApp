package com.oo115.myapplication.Workout_feature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oo115.myapplication.R;

import java.io.IOException;
import java.util.Objects;

import androidx.fragment.app.Fragment;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Exercise_GifsTabFragment extends Fragment {

    public Exercise_GifsTabFragment() {
        // Required empty public constructor
    }

    GifImageView gifImageView;

    public static Exercise_GifsTabFragment newInstance(String name, String desc, String gif, boolean showInput) {
        Exercise_GifsTabFragment f = new Exercise_GifsTabFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("ex_name", name);
        args.putString("ex_dec", desc);
        args.putString("ex_gif", gif);
        args.putBoolean("showInput", showInput);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise__gifs_tab, container, false);
        String ex_gif = Objects.requireNonNull(getArguments()).getString("gif");
        gifImageView = view.findViewById(R.id.gif);
        //resource (drawable or raw)
        try {

            GifDrawable gifFromResource = new GifDrawable(getResources(), R.drawable.gif);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }

}
