package com.oo115.myapplication.Workout_feature;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.FavouritesDB;
import com.oo115.myapplication.retrofitAPI.ResponseDB;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favourites_listAdaptor extends ArrayAdapter<String> implements View.OnClickListener, Filterable {

    //holds the urls for the images
    private ArrayList<String> image_url = new ArrayList<String>();
    //holds all the exercises in the favourites array
    private ArrayList<String> exercise = new ArrayList<String>();
    //holds all the exercises in the custom plan
    private ArrayList<String> custom_plan = new ArrayList<String>();
    private ArrayList<String> exercise_Id = new ArrayList<String>();
    private Activity context;

    public static PrefConFig prefConFig;
    public static ApiInterface apiInterface;

    Favourites_listAdaptor(Activity context, ArrayList<String> exercise, ArrayList<String> image_url, ArrayList<String> custom_plan, ArrayList<String> exercise_Id) {
        super(context, R.layout.exercises_layout, exercise);

        this.context = context;
        this.exercise = exercise;
        this.image_url = image_url;
        this.custom_plan = custom_plan;
        this.exercise_Id = exercise_Id;

    }


    @Override
    public void onClick(View v) {

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        View view = convertView;
        Favourites_listAdaptor.ViewHolder viewHolder;
        if (view == null) {
            //build in class to convert xml file to corressponding java object
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.exercises_layout, null, true);
            viewHolder = new ViewHolder(view);
            //view class method which stores view
            view.setTag(viewHolder);
        } else {
            //this gets the stored method in set tag
            viewHolder = (Favourites_listAdaptor.ViewHolder) view.getTag();
        }


        //removing buttons if the user has no favourites
        if (exercise.contains("No favourites found")) {

//            exercise.add("No favourites found");
            viewHolder.addToPlanbtn.setVisibility(View.GONE);
            viewHolder.likebtn.setVisibility(View.GONE);
        }
        //setting the reference for the image and text view
        viewHolder.exercise_array.setText(exercise.get(position));
//     viewHolder.image.setImageResource(image.get(0));


        //setting the image to the default image if no image is found in the database.
        if (image_url.size() > 0 && !image_url.get(position).isEmpty()) {
            Picasso.get().
                    load(image_url.get(position))
                    .placeholder(R.drawable.back)
                    .into(viewHolder.image);


        } else {
            Picasso.get().
                    load("http://peterp1.sgedu.site//images//noFavouritesFound.png")
                    .placeholder(R.drawable.back)
                    .into(viewHolder.image);
        }

        //all exercises in the favourites section should be liked automatically
        viewHolder.likebtn.setLiked(true);

        //all exercises in the custom plan should be ticked
        if (custom_plan.contains(viewHolder.exercise_array.getText().toString())) {
            viewHolder.addToPlanbtn.setLiked(true);
        }

        //all exercises should be added to the database and those unliked should be removed from the database
        viewHolder.likebtn.setOnLikeListener(new OnLikeListener() {
            String ex_name = exercise.get(position);

            @Override
            public void liked(LikeButton likeButton) {
                prefConFig.distplayToast(Integer.toString(exercise.size()));

                int user_id = Integer.parseInt(prefConFig.readId());

                Call<FavouritesDB> favourite_ex = apiInterface.favourite(user_id, ex_name);

                favourite_ex.enqueue(new Callback<FavouritesDB>() {
                    @Override
                    public void onResponse(Call<FavouritesDB> call, Response<FavouritesDB> response) {
                        if (response.body().getResponse().equals("Favourited")) {
                            likeButton.setLiked(true);

                        } else {
                            likeButton.setLiked(false);
                            prefConFig.distplayToast(ex_name);
                        }
                    }

                    @Override
                    public void onFailure(Call<FavouritesDB> call, Throwable t) {


                    }
                });
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                prefConFig.distplayToast(Integer.toString(exercise.size()));
                Call<FavouritesDB> unfavourite_ex = apiInterface.unfavourite(ex_name, Integer.parseInt(prefConFig.readId()));
                unfavourite_ex.enqueue(new Callback<FavouritesDB>() {
                    @Override
                    public void onResponse(Call<FavouritesDB> call, Response<FavouritesDB> response) {
                        if (response.body().getResponse().equals("deleted")) {
                            likeButton.setLiked(false);

                            if (exercise.size() == 1) {
                                exercise.remove(position);
                                exercise.add(position, "No favourites found");

                            } else {
                                exercise.remove(position);
                            }


                            notifyDataSetChanged();
                        } else {
                            likeButton.setLiked(true);
                        }


                    }

                    @Override
                    public void onFailure(Call<FavouritesDB> call, Throwable t) {

                    }
                });


            }
        });


        viewHolder.addToPlanbtn.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                String exercise_id = exercise_Id.get(position);
                Call<ResponseDB> add_to_plan = apiInterface.add_to_plan(Integer.parseInt(prefConFig.readId()), Integer.parseInt(exercise_id));

                add_to_plan.enqueue(new Callback<ResponseDB>() {
                    @Override
                    public void onResponse(Call<ResponseDB> call, Response<ResponseDB> response) {
                        if (response.body().getResponse().equals("added to plan")) {
                            likeButton.setLiked(true);
                        } else if (response.body().getResponse().equals("error")) {
                            likeButton.setLiked(false);

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDB> call, Throwable t) {

                    }
                });


            }

            @Override
            public void unLiked(LikeButton likeButton) {
                String exercise_id = exercise_Id.get(position);
                Call<ResponseDB> remove_from_plan = apiInterface.remove_from_plan(Integer.parseInt(prefConFig.readId()), Integer.parseInt(exercise_id));


                remove_from_plan.enqueue(new Callback<ResponseDB>() {
                    @Override
                    public void onResponse(Call<ResponseDB> call, Response<ResponseDB> response) {
                        if (response.body().getResponse().equals("removed from plan")) {
                            likeButton.setLiked(false);

                        } else if (response.body().getResponse().equals("not removed from plan")) {
                            likeButton.setLiked(true);
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseDB> call, Throwable t) {

                    }
                });

            }
        });


        return view;


    }


    static class ViewHolder {
        TextView exercise_array;
        ImageView image;
        LikeButton likebtn, addToPlanbtn;

        ViewHolder(View view) {
            exercise_array = view.findViewById(R.id.plan_list_exercise);
            image = view.findViewById(R.id.plan_exercise_picture);
            likebtn = view.findViewById(R.id.favouriteBtn);
            addToPlanbtn = view.findViewById(R.id.addToList_button);

        }
    }


}
