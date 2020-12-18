package com.oo115.myapplication.Settings;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdaptor extends ArrayAdapter<String> implements View.OnClickListener {
    String[] settings_titles;
    public static PrefConFig prefConFig;
    private Activity context;

    public ListAdaptor(Activity context, String[] settings_titles) {
        super(context, R.layout.settings, settings_titles);

        this.context = context;
        this.settings_titles = settings_titles;


    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ListAdaptor.ViewHolder viewHolder = null;
        if (view == null) {
            //build in class to convert xml file to corressponding java object
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.settings, null, true);
            viewHolder = new ListAdaptor.ViewHolder(view);
            //view class method which stores view
            view.setTag(viewHolder);
        } else {
            //this gets the stored method in set tag
            viewHolder = (ListAdaptor.ViewHolder) view.getTag();
        }
        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        //setting the reference for the image and text view
        viewHolder.setting_title_tv.setText(settings_titles[position]);

        if (position == 0) {
            viewHolder.setting_content_tv.setText(prefConFig.readEmail());

        } else {
            viewHolder.setting_content_tv.setText("");
        }


        viewHolder.forward_nav.setImageResource(R.drawable.ic_arrow_forward_black_24dp);


        return view;


    }


    @Override
    public void onClick(View v) {

    }

    class ViewHolder {
        TextView setting_title_tv, setting_content_tv;
        ImageButton forward_nav;


        ViewHolder(View view) {
            setting_title_tv = view.findViewById(R.id.setting_title_tv);
            setting_content_tv = view.findViewById(R.id.setting_content_tv);
            forward_nav = view.findViewById(R.id.forward_nav);
        }
    }
}
