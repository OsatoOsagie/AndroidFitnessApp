package com.oo115.myapplication.Workout_feature;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.oo115.myapplication.BodyPart;
import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.Custom_plan_searchResponse;
import com.oo115.myapplication.retrofitAPI.Exercises_response;
import com.oo115.myapplication.retrofitAPI.Favourites_search_response;

import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is used to display a list view of each exercise for a particular muscle group.
 */
public class Exercises_for_muscle_groupsFragment extends Fragment {
    private Toolbar toolbar;
    public static PrefConFig prefConFig;
    public static ApiInterface apiInterface;
    ArrayList<String> exercises_array = new ArrayList<String>();
    ArrayList<Integer> exercise_Image = new ArrayList<Integer>();
    ArrayList<String> exercise_Id = new ArrayList<String>();
    ArrayList<String> exercise_gif = new ArrayList<String>();
    ArrayList<String> favourites = new ArrayList<String>();
    ArrayList<String> ex_desc = new ArrayList<String>();
    ArrayList<String> custom_plan = new ArrayList<String>();
    private ListView listView;
    private SearchView searchView;
    ArrayList<String> image_url = new ArrayList<String>();
    Exercises_LVAdapter adapter;




    public Exercises_for_muscle_groupsFragment() {
        // Required empty public constructor
    }


    /**
     * This creates a new instance of this class with specific parameters,
     * allows us to reuse a fragment multiple times.
     */
    public static Exercises_for_muscle_groupsFragment newInstance(int muscle_group, boolean showInput) {
        Exercises_for_muscle_groupsFragment f = new Exercises_for_muscle_groupsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("muscle_groupID", muscle_group);
        args.putBoolean("showInput", showInput);
        f.setArguments(args);

        return f;
    }


    /*
    This method acts as an onclick listener for each exercise in the list view
     */
    private AdapterView.OnItemClickListener onClickListItem = (parent, view, position, id) -> {

        TextView textView = view.findViewById(R.id.LIST_BODYPART_ID);
        String exerciseName = exercises_array.get(position);
        String exerciseDesc = ex_desc.get(position);
        String ex_gif = exercise_gif.get(position);

        Workout_Details_Fragment fragment = Workout_Details_Fragment.newInstance(exerciseName, exerciseDesc, ex_gif, true);
        String backStateName = fragment.getClass().getName();
        FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_containerHome, fragment);
        transaction.addToBackStack(backStateName);

        // Commit the transaction
        transaction.commit();

    };


    /*
    This method finds the exercises of each body part
     */
    private void find_exercises(int body_part) {
        Call<Exercises_response> api_call = apiInterface.findExercises(body_part);


        api_call.enqueue(new Callback<Exercises_response>() {
            @Override
            public void onResponse(Call<Exercises_response> call, Response<Exercises_response> response) {

//                        exercises_array=new String[response.body().getExercisesArray().size()];

                for (int i = 0; i < response.body().getExercisesArray().size(); i++) {

                    exercises_array.add(i, response.body().getExercisesArray().get(i).getName());
                    ex_desc.add(i, response.body().getExercisesArray().get(i).getDescription());
                    image_url.add(i, response.body().getExercisesArray().get(i).getImage());
                    exercise_Id.add(i, response.body().getExercisesArray().get(i).getId());
                    exercise_gif.add(i, response.body().getExercisesArray().get(i).getGif());
//                            exercise_Id.add(i, response.body().getExercisesArray().get(i).getId());
//                            exercises_array[i]=
                    adapter.notifyDataSetChanged();

                }
            }


            @Override
            public void onFailure(Call<Exercises_response> call, Throwable t) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercises_for_muscle_groups, container, false);

//setting up the tool bar to have a back arrow on create and setting the title of the fragment
        toolbar = view.findViewById(R.id.toolbar_exercises);


        //removing the actionbar so that tool bar can show
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());


        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        //getting the extras sent over when item was clicked on the list view
        int extras = getArguments().getInt("muscle_groupID");


        listView = view.findViewById(R.id.exercises_workout_ListView);

        exercise_Image.add(R.drawable.chest);
        adapter = new Exercises_LVAdapter(getActivity(), exercises_array, exercise_Image, image_url, favourites, exercise_Id, custom_plan);
        //setting the adaptor for the list view
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onClickListItem);

        //getting all the favourites by the user
        Call<Favourites_search_response> search_favourites = apiInterface.search_favourites(Integer.parseInt(prefConFig.readId()));

        search_favourites.enqueue(new Callback<Favourites_search_response>() {
            @Override
            public void onResponse(Call<Favourites_search_response> call, Response<Favourites_search_response> response) {
                if (response.body().getResponse().equals("favourites found")) {

                    for (int i = 0; i < response.body().getFavourites().size(); i++) {

                        favourites.add(i, response.body().getFavourites().get(i).getName());


                        adapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<Favourites_search_response> call, Throwable t) {

            }
        });


        //getting all the custom_plan
        Call<Custom_plan_searchResponse> get_custom_plan = apiInterface.get_custom_plan(Integer.parseInt(prefConFig.readId()));

        get_custom_plan.enqueue(new Callback<Custom_plan_searchResponse>() {
            @Override
            public void onResponse(Call<Custom_plan_searchResponse> call, Response<Custom_plan_searchResponse> response) {
                if (response.body().getResponse().equals("plan not empty")) {
                    prefConFig.distplayToast("here");
                    for (int i = 0; i < response.body().getCustom_planArraysArray().size(); i++) {

                        custom_plan.add(i, response.body().getCustom_planArraysArray().get(i).getEx_name());


                        adapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<Custom_plan_searchResponse> call, Throwable t) {

            }
        });




        switch (extras) {
            case 0:

                toolbar.setTitle(R.string.triceps);
                find_exercises(BodyPart.TRICEPS);

                break;
            case 1:
                toolbar.setTitle(R.string.pectoraux);
                find_exercises(BodyPart.PECTORAUX);

                break;
            case 2:
                toolbar.setTitle(R.string.deltoids);
                find_exercises(BodyPart.DELTOIDS);

                break;
            case 3:
                toolbar.setTitle(R.string.biceps);
                find_exercises(BodyPart.BICEPS);

                break;
            case 4:
                toolbar.setTitle(R.string.core);
                find_exercises(BodyPart.ABDOMINAUX);


                break;
            case 5:
                toolbar.setTitle(R.string.dorseaux);
                find_exercises(BodyPart.DORSEAUX);


                break;
            case 6:
                toolbar.setTitle(R.string.leg);
                find_exercises(BodyPart.QUADRICEPS);

                break;
            case 7:
                toolbar.setTitle(R.string.showAll);
                find_exercises(BodyPart.SHOWALLEXERCIES);
                break;


        }


        //search view
        searchView = view.findViewById(R.id.exercise_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                if (TextUtils.isEmpty(newText)) {
                    listView.clearTextFilter();
                    adapter.getFilter().filter("");

                } else {
                    adapter.getFilter().filter(newText);
                }
                return false;
            }
        });


        return view;

    }

}
