package com.oo115.myapplication.Workout_feature;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.Custom_plan_searchResponse;

import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomPlanFragment extends Fragment {

    public CustomPlanFragment() {
        // Required empty public constructor
    }


    public static CustomPlanFragment newInstance() {
        CustomPlanFragment f = new CustomPlanFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        f.setArguments(args);

        return f;

    }


    private ListView listView;

    private Activity context;
    private ArrayList<String> exercise = new ArrayList<String>();
    private ArrayList<String> image_url = new ArrayList<String>();
    public static PrefConFig prefConFig;
    public static ApiInterface apiInterface;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_plan, container, false);
        listView = view.findViewById(R.id.custom_plan_Lv);
        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        CustomPlanLVAdapter adapter = new CustomPlanLVAdapter(getActivity(), exercise, image_url);
        listView.setAdapter(adapter);

        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
        toolbar = view.findViewById(R.id.toolbar_custom_plan);
        toolbar.setTitle("Custom Plan");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());


        Call<Custom_plan_searchResponse> get_custom_plan = apiInterface.get_custom_plan(Integer.parseInt(prefConFig.readId()));

        get_custom_plan.enqueue(new Callback<Custom_plan_searchResponse>() {
            @Override
            public void onResponse(Call<Custom_plan_searchResponse> call, Response<Custom_plan_searchResponse> response) {
                if (response.body().getResponse().equals("plan not empty")) {
                    for (int i = 0; i < response.body().getCustom_planArraysArray().size(); i++) {

//                        exercise.clear();
//                        image_url.clear();

                        exercise.add(i, response.body().getCustom_planArraysArray().get(i).getEx_name());
                        image_url.add(i, response.body().getCustom_planArraysArray().get(i).getEx_image());

                        adapter.notifyDataSetChanged();

                    }
                } else {


                    exercise.clear();
                    exercise.add("No exercises in plan");
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Custom_plan_searchResponse> call, Throwable t) {

            }
        });


        return view;
    }


    @Override
    public void onStart() {
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
        super.onStart();
    }


}
