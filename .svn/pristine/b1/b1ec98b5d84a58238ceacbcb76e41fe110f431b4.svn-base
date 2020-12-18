package com.oo115.myapplication.GettingStarted;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdaptor_gettingStarted extends ArrayAdapter<String> implements View.OnClickListener {

    private String[] description;
    private int[] images;
    public static PrefConFig prefConFig;
    private Activity context;

    public ListAdaptor_gettingStarted(Activity context, String[] description, int[] images) {
        super(context, R.layout.settings, description);

        this.context = context;
        this.description = description;
        this.images = images;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ListAdaptor_gettingStarted.ViewHolder viewHolder = null;
        if (view == null) {
            //build in class to convert xml file to corressponding java object
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.getting_started, null, true);
            viewHolder = new ListAdaptor_gettingStarted.ViewHolder(view);
            //view class method which stores view
            view.setTag(viewHolder);
        } else {
            //this gets the stored method in set tag
            viewHolder = (ListAdaptor_gettingStarted.ViewHolder) view.getTag();
        }
        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        //setting the reference for the image and text view
        viewHolder.description.setText(description[position]);


        viewHolder.img.setImageResource(images[position]);


        return view;


    }


    class ViewHolder {
        TextView description;
        ImageView img;


        ViewHolder(View view) {
            description = view.findViewById(R.id.gettingStarted_description);
            img = view.findViewById(R.id.gettingStarted_picture);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
