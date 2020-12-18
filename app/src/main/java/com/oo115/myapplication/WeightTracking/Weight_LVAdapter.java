package com.oo115.myapplication.WeightTracking;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.ResponseDB;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Weight_LVAdapter extends ArrayAdapter<String> implements View.OnClickListener {

    private ArrayList<String> measurement_size;
    private ArrayList<String> date_taken;
    private ArrayList<String> measurement_id;
    private Activity context;
    public static ApiInterface apiInterface;
    public static PrefConFig prefConFig;
    String table_name;


    public Weight_LVAdapter(Activity context, ArrayList<String> measurement_size, ArrayList<String> date_taken, ArrayList<String> measurement_id, String table_name) {
        super(context, R.layout.measure_history, measurement_size);

        this.context = context;
        this.measurement_size = measurement_size;
        this.measurement_id = measurement_id;
        this.date_taken = date_taken;
        this.table_name = table_name;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View view = convertView;
        Weight_LVAdapter.ViewHolder viewHolder = null;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        if (view == null) {
            //build in class to convert xml file to corressponding java object
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.measure_history, null, true);
            viewHolder = new Weight_LVAdapter.ViewHolder(view);
            //view class method which stores view
            view.setTag(viewHolder);
        } else {
            //this gets the stored method in set tag
            viewHolder = (Weight_LVAdapter.ViewHolder) view.getTag();
        }
        //setting the reference for the image and text view
        viewHolder.measurementDate.setText(date_taken.get(position));
        viewHolder.measurementSize.setText(measurement_size.get(position));
        viewHolder.deleteBtn.setVisibility(View.VISIBLE);

        //hiding the visibility of the bin icon if no measurement was entered
        if (date_taken.get(position).equals("-") && measurement_size.get(position).equals("-")) {
            viewHolder.deleteBtn.setVisibility(View.GONE);
        }


        viewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText(context.getString(R.string.delete));
                pDialog.setContentText(context.getString(R.string.confirm_delete));
                pDialog.setCancelText(context.getString(R.string.dialog_cancel));
                pDialog.setConfirmText(context.getString(R.string.str_ok));
                pDialog.showCancelButton(true);
                pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                });
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Call<ResponseDB> delete_measurementDBCall = apiInterface.delete_weight(Integer.parseInt(measurement_id.get(position)), table_name);


                        delete_measurementDBCall.enqueue(new Callback<ResponseDB>() {
                            @Override
                            public void onResponse(Call<ResponseDB> call, Response<ResponseDB> response) {
                                if (response.body().getResponse().equals("deleted")) {
                                    measurement_size.remove(position);
                                    date_taken.remove(position);
                                    notifyDataSetChanged();
                                    pDialog.dismissWithAnimation();


                                    prefConFig.writeUser_weight("no weight found");
                                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText(context.getString(R.string.success_))
                                            .setContentText(context.getString(R.string.profile_updated))
                                            .show();


                                } else {
                                    pDialog.dismissWithAnimation();
//
                                    new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText(context.getString(R.string.str_error))
                                            .setContentText(context.getString(R.string.default_msg))
                                            .show();

                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDB> call, Throwable t) {

                            }
                        });


                    }
                });
                pDialog.show();
            }
        });


        return view;
    }


    @Override
    public void onClick(View v) {

    }


    class ViewHolder {
        TextView measurementDate, measurementSize;
        ImageButton deleteBtn;


        ViewHolder(View view) {
            measurementDate = view.findViewById(R.id.LIST_DATE);
            measurementSize = view.findViewById(R.id.LIST_MEASUREMENT_SIZE);
            deleteBtn = view.findViewById(R.id.deleteBtn);

        }
    }
}
