package com.oo115.myapplication.Workout_feature;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oo115.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Plans_lvAdapter extends ArrayAdapter<String> implements View.OnClickListener {

    private String[] plans;
    private Activity context;


    public Plans_lvAdapter(Activity context, String[] plans) {
        super(context, R.layout.plans__tab, plans);

        this.context = context;
        this.plans = plans;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        Plans_lvAdapter.ViewHolder viewHolder = null;
        if (view == null) {
            //build in class to convert xml file to corressponding java object
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.plans_layout, null, true);
            viewHolder = new Plans_lvAdapter.ViewHolder(view);
            //view class method which stores view
            view.setTag(viewHolder);
        } else {
            //this gets the stored method in set tag
            viewHolder = (Plans_lvAdapter.ViewHolder) view.getTag();
        }
        //setting the reference for the image and text view
        viewHolder.plan_name.setText(plans[position]);

        //making sure the information button doesn't stop the list view from being clickable
        viewHolder.help_button.setFocusable(false);
        viewHolder.help_button.setFocusableInTouchMode(false);

        viewHolder.help_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position) {
                    case 0:
                        new SweetAlertDialog(getContext(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                .setTitleText("Custom")
                                .setContentText("Workout frequency is 2 times per week. We recommend 1 or 2 rest days between the workouts.")
                                .setCustomImage(R.drawable.ic_info_black_24dp)
                                .show();
                        break;
                    case 1:
                        new SweetAlertDialog(getContext(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                .setTitleText("Beginner")
                                .setContentText("Workout frequency is 2 times per week. We recommend 1 or 2 rest days between the workouts.")
                                .setCustomImage(R.drawable.ic_info_black_24dp)
                                .show();
                        break;
                    case 2:
                        new SweetAlertDialog(getContext(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                .setTitleText("Intimidate")
                                .setContentText("Workout frequency is 2 times per week. We recommend 1 or 2 rest days between the workouts.")
                                .setCustomImage(R.drawable.ic_info_black_24dp)
                                .show();
                        break;
                    case 3:
                        new SweetAlertDialog(getContext(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                .setTitleText("Advanced")
                                .setContentText("Workout frequency is 2 times per week. We recommend 1 or 2 rest days between the workouts.")
                                .setCustomImage(R.drawable.ic_info_black_24dp)
                                .show();
                        break;
                    case 4:
                        new SweetAlertDialog(getContext(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                .setTitleText("Elite")
                                .setContentText("Workout frequency is 2 times per week. We recommend 1 or 2 rest days between the workouts.")
                                .setCustomImage(R.drawable.ic_info_black_24dp)
                                .show();
                        break;
                }


            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder {
        TextView plan_name;
        ImageButton help_button;

        ViewHolder(View view) {
            plan_name = view.findViewById(R.id.plan_name);
            help_button = view.findViewById(R.id.help_btn);

        }
    }


}
