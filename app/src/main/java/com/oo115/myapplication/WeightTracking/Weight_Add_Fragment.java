package com.oo115.myapplication.WeightTracking;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.bodyMeasures.MyXAxisValueFormatter;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.ResponseDB;
import com.oo115.myapplication.retrofitAPI.WeightMeasurement_Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Weight_Add_Fragment extends Fragment {

    public Weight_Add_Fragment() {
        // Required empty public constructor
    }

    private Toolbar toolbar;
    private SimpleDateFormat dateFormatter;
    public static ApiInterface apiInterface;
    public static PrefConFig prefConFig;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Date java_dateOfMeasure;
    private Button addMeasure;
    private ArrayList<String> measurements_size = new ArrayList<String>();

    private ArrayList<String> measurements_date = new ArrayList<String>();
    private ArrayList<String> measurements_Id = new ArrayList<String>();
    java.sql.Date sql_date;
    private EditText measurementText;
    private String databasetable_name;

    private ListView listView;
    float measurement;
    String dateChanged_watcher;
    private View view;
    private String metrics;
    private String extras_pageName;
    private ArrayList<String> measurements_size_graph = new ArrayList<String>();

    private ArrayList<String> measurements_date_graph = new ArrayList<String>();
    private ArrayList<String> getMeasurements_size_graph = new ArrayList<String>();
    LineData data;
    LineDataSet set1;
    private LineChart mChart = null;
    ArrayList<ILineDataSet> dataSets;
    private Calendar calendar;
    private int year_current;
    int proposed_date;
    ArrayList<Entry> yvalue = new ArrayList<>();

    public static Weight_Add_Fragment newInstance(String name, ArrayList<String> measurement_size, ArrayList<String> measurement_date, boolean showInput) {
        Weight_Add_Fragment f = new Weight_Add_Fragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putBoolean("showInput", showInput);
        args.putStringArrayList("measure_size", measurement_size);
        args.putStringArrayList("measure_date", measurement_date);
        f.setArguments(args);

        return f;
    }



    public void apiCall(int id, String measurement_type) {

        measurements_date.add("-");
        measurements_date.add("-");
        measurements_date.add("-");
        measurements_date.add("-");
        measurements_date.add("-");

        measurements_size.add("-");
        measurements_size.add("-");
        measurements_size.add("-");
        measurements_size.add("-");
        measurements_size.add("-");

        extras_pageName = getArguments().getString("name");
        //setting the metric for the measurement
        if (extras_pageName.equals("Weight")) {
            metrics = " kg";
        } else {
            metrics = " %";
        }
        /*
        getting the users most recent measurements
         */
        Call<WeightMeasurement_Response> search_weight = apiInterface.search_weight_measurements(id, measurement_type);


        search_weight.enqueue(new Callback<WeightMeasurement_Response>() {
            @Override
            public void onResponse(Call<WeightMeasurement_Response> call, Response<WeightMeasurement_Response> response) {
                // this if statement will check the response from the server to see if there is a measurement for that user in the database.
                if (response.body().getResponse().equals("Measurement Found")) {

                    //initialises an int for the size of the list sent back by the server
                    int listSize = response.body().getMeasurementArray().size();

                    //this will check if the size of the list sent back by the server is more than or equal to 5
                    //the nested for loops will the get the date and the measurements and store them in an array
                    if (listSize >= 5) {


                        for (int i = 0; i < 5; i++) {

                            measurements_date.add(i, response.body().getMeasurementArray().get(i).getDate());
                            measurements_size.add(i, response.body().getMeasurementArray().get(i).getMeasurement() + metrics);
                            measurements_Id.add(i, response.body().getMeasurementArray().get(i).getId());


                        }

                    } else {
                        //if the list size is not up to 5, then we assign a new size to the array
                        //we then use a for  loop to get the size and the date of the measurement

//                        measurements_size = new String[listSize];
//


                        for (int i = 0; i < listSize; i++) {

                            measurements_date.add(i, response.body().getMeasurementArray().get(i).getDate());
                            measurements_size.add(i, response.body().getMeasurementArray().get(i).getMeasurement() + metrics);
                            measurements_Id.add(i, response.body().getMeasurementArray().get(i).getId());

                        }
//
//

                    }
                } else {
                    prefConFig.distplayToast("here");
                }

                //ist view for history of measurements
                listView = view.findViewById(R.id.listView_weight);

                //giving the list view an adaptor of class bodyPartHistoryAdaptor
                Weight_LVAdapter weight_lvAdapter = new Weight_LVAdapter(getActivity(), measurements_size, measurements_date, measurements_Id, databasetable_name);
                listView.setAdapter(weight_lvAdapter);
                weight_lvAdapter.notifyDataSetChanged();
//                prefConFig.distplayToast(Integer.toString(response.body().getMeasurementArray().size()));

            }

            @Override
            public void onFailure(Call<WeightMeasurement_Response> call, Throwable t) {

            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weight__add, container, false);

        dateChanged_watcher = "date has not been set";
        assert getArguments() != null;
        extras_pageName = getArguments().getString("name");
        measurements_size_graph = getArguments().getStringArrayList("measure_size");
        measurements_date_graph = getArguments().getStringArrayList("measure_date");

        toolbar = view.findViewById(R.id.weight_add_toolbar);
        listView = view.findViewById(R.id.listView_weight);
        //removing the actionbar so that tool bar can show
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setTitle(extras_pageName);
        mChart = view.findViewById(R.id.body_weightChart);


        /*
        GRAPH
         */

        ArrayList<Integer> month = new ArrayList<Integer>();

        for (int i = 0; i < measurements_date_graph.size(); i++) {


            try {
                Date datetype = new SimpleDateFormat("yyyy-MM-dd").parse(measurements_date_graph.get(i));

                month.add(datetype.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue());

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }


        String[] values = new String[]{"", "Jan", "feb", "March", "April", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


        assert measurements_size_graph != null;

        if (!measurements_size_graph.isEmpty()) {

            for (int i = 0; i < measurements_size_graph.size(); i++) {


                yvalue.add(new Entry(month.get(i), Float.parseFloat(measurements_size_graph.get(i))));
                Collections.sort(yvalue, new EntryXComparator());


            }
            set1 = new LineDataSet(yvalue, "Progress over the last 12 months");

//        set1.setFillAlpha(110);

            dataSets = new ArrayList<>();
            dataSets.add(set1);

            data = new LineData(dataSets);

            XAxis xAxis = mChart.getXAxis();
            xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
            xAxis.setGranularity(1f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);


            mChart.setData(data);
            mChart.getDescription().setEnabled(false);
            mChart.setEnabled(true);
        }


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        databasetable_name = "tester";

        //using extras to set the name of the database table the server call is made to
        switch (extras_pageName) {
            case "Weight":
                databasetable_name = "Weight_measurement";
                break;
            case "Body Fat":
                databasetable_name = "BodyFatPercentage";
                break;
            case "Water":
                databasetable_name = "WaterPercentage";
                break;
            case "Muscle":
                databasetable_name = "MusclePercentage";
                break;

        }
        // making the server call to get measurements
        apiCall(Integer.parseInt(prefConFig.readId()), databasetable_name);


        mDisplayDate = view.findViewById(R.id.measuredate);
        addMeasure = view.findViewById(R.id.buttonAddMeasure);
        measurementText = view.findViewById(R.id.weightMeasurement);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //we need to add 1 to the month because january is 0 and december is 11
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        mDisplayDate.setText(date);
                        try {
//                            dateFormatter.setLenient(false);
                            java_dateOfMeasure = dateFormatter.parse(date);
                            sql_date = new java.sql.Date(java_dateOfMeasure.getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    }
                };

                //create calender object so that when text view is clicked it immediately goes to the current date
                calendar = Calendar.getInstance();
                //getting the current year
                year_current = calendar.get(Calendar.YEAR);
                //getting the current month
                int month = calendar.get(Calendar.MONTH);
                //getting the current day
                //day of month because they are different  number of days in each month
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Objects.requireNonNull(getContext()),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year_current, month, day

                );
                //making background transparent
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        //        adding a text watcher
        mDisplayDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                dateChanged_watcher = "date has not been set";
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dateChanged_watcher = "date has been set";
            }

            @Override
            public void afterTextChanged(Editable s) {
                dateChanged_watcher = "date has been set";
            }
        });

        assert extras_pageName != null;
        switch (extras_pageName) {
            case "Weight":

                addMeasure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //checking if the user has entered a measurement and a date
                        if (!measurementText.getText().toString().isEmpty() && dateChanged_watcher.equals("date has been set")) {
                            //setting the maximum weight the user can enter
                            if (Double.parseDouble(measurementText.getText().toString()) <= 636 && Double.parseDouble(measurementText.getText().toString()) >= 41) {


                                measurement = Float.parseFloat(measurementText.getText().toString());

                                calendar.setTime(java_dateOfMeasure);
                                proposed_date = calendar.get(Calendar.YEAR);
                                //checking that users are not entering measurements for the future
                                if (proposed_date <= year_current) {
                                    Call<ResponseDB> callAddMeasurement = apiInterface.add_weight_measurement(Integer.parseInt(prefConFig.readId()), sql_date, measurement);
//                                addMeasurement(id, bodyPart, measurement, sql_date);
                                    SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Loading");
                                    pDialog.setCancelable(false);
                                    pDialog.show();
                                    callAddMeasurement.enqueue(new Callback<ResponseDB>() {
                                        @Override
                                        public void onResponse(Call<ResponseDB> call, Response<ResponseDB> response) {
                                            if (response.body().getResponse().equals("measurement added")) {

                                                measurements_size.clear();
                                                measurements_date.clear();
                                                measurements_Id.clear();
//                                            apiCall(Integer.parseInt(prefConFig.readId()), databasetable_name);

                                                pDialog.dismissWithAnimation();

                                                apiCall(Integer.parseInt(prefConFig.readId()), databasetable_name);
                                                measurementText.setText("");
                                                mDisplayDate.setText(getString(R.string.selet_date));
                                                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                                        .setTitleText(getString(R.string.success_))
                                                        .setContentText(getString(R.string.measurement_added))
                                                        .show();


                                                int month = sql_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
                                                int year = sql_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();

//                                prefConFig.distplayToast(Integer.toString(month ));

                                                //plotting the values on the chart everytime a new measurement is added
                                                if (year == year_current) {
                                                    yvalue.add(new Entry(month, measurement));
                                                    Collections.sort(yvalue, new EntryXComparator());
                                                    set1 = new LineDataSet(yvalue, "Progress over the last 12 months");

                                                    dataSets = new ArrayList<>();

                                                    dataSets.add(set1);


                                                    data = new LineData(dataSets);
                                                    set1.notifyDataSetChanged();
                                                    data.notifyDataChanged();
                                                    mChart.notifyDataSetChanged();
                                                    mChart.invalidate();
                                                    mChart.setData(data);

                                                    XAxis xAxis = mChart.getXAxis();
                                                    xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
                                                    xAxis.setGranularity(1f);
                                                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);


                                                    mChart.setData(data);
                                                    mChart.getDescription().setEnabled(false);
                                                    mChart.setEnabled(true);

                                                }

                                            } else {
                                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                                        .setTitleText(getString(R.string.str_error))
                                                        .setContentText(getString(R.string.default_msg))
                                                        .show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseDB> call, Throwable t) {

                                        }
                                    });
                                } else {
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText(getString(R.string.str_error))
                                            .setContentText("Cannot enter future dates")
                                            .show();
                                    mDisplayDate.requestFocus();
                                    mDisplayDate.requestFocus();
                                }

//
                            } else {
//                                display error
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText(getString(R.string.str_error))
                                        .setContentText("enter a valid weight")
                                        .show();
                            }

                        } else {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(getString(R.string.str_error))
                                    .setContentText(getString(R.string.dateError))
                                    .show();

                        }

                    }
                });

                break;
            case "Body Fat":
                measurementText.setHint("Enter body fat %");
                addMeasure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!measurementText.getText().toString().isEmpty() && dateChanged_watcher.equals("date has been set")) {
                            measurement = Float.parseFloat(measurementText.getText().toString());
                            if (Double.parseDouble(measurementText.getText().toString()) <= 70 && Double.parseDouble(measurementText.getText().toString()) >= 2) {

                                calendar.setTime(java_dateOfMeasure);
                                proposed_date = calendar.get(Calendar.YEAR);
                                if (proposed_date <= year_current) {


                                    Call<ResponseDB> callAddMeasurement = apiInterface.add_bodyFat_measurement(Integer.parseInt(prefConFig.readId()), sql_date, measurement);
                                    SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Loading");
                                    pDialog.setCancelable(false);
                                    pDialog.show();
                                    callAddMeasurement.enqueue(new Callback<ResponseDB>() {
                                        @Override
                                        public void onResponse(Call<ResponseDB> call, Response<ResponseDB> response) {
                                            if (response.body().getResponse().equals("measurement added")) {
                                                apiCall(Integer.parseInt(prefConFig.readId()), databasetable_name);
                                                pDialog.dismissWithAnimation();
                                                measurementText.setText("");
                                                mDisplayDate.setText("Select a date");

                                                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                                        .setTitleText(getString(R.string.success_))
                                                        .setContentText(getString(R.string.measurement_added))
                                                        .show();

                                                int month = sql_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
                                                int year = sql_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();

//                                prefConFig.distplayToast(Integer.toString(month ));

                                                if (year == year_current) {
                                                    yvalue.add(new Entry(month, measurement));
                                                    Collections.sort(yvalue, new EntryXComparator());
                                                    set1 = new LineDataSet(yvalue, "Progress over the last 12 months");

                                                    dataSets = new ArrayList<>();

                                                    dataSets.add(set1);


                                                    data = new LineData(dataSets);
                                                    set1.notifyDataSetChanged();
                                                    data.notifyDataChanged();
                                                    mChart.notifyDataSetChanged();
                                                    mChart.invalidate();
                                                    mChart.setData(data);

                                                    XAxis xAxis = mChart.getXAxis();
                                                    xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
                                                    xAxis.setGranularity(1f);
                                                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);


                                                    mChart.setData(data);
                                                    mChart.getDescription().setEnabled(false);
                                                    mChart.setEnabled(true);

                                                }

                                            } else {
                                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                                        .setTitleText(getString(R.string.str_error))
                                                        .setContentText(getString(R.string.default_msg))
                                                        .show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseDB> call, Throwable t) {

                                        }
                                    });

                                } else {
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText(getString(R.string.str_error))
                                            .setContentText("Cannot enter future dates")
                                            .show();
                                    mDisplayDate.requestFocus();
                                    mDisplayDate.requestFocus();

                                }
//
                            } else {
//                                display error
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText(getString(R.string.str_error))
                                        .setContentText("enter a valid body fat percentage")
                                        .show();
                            }
                        } else {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(getString(R.string.str_error))
                                    .setContentText(getString(R.string.dateError))
                                    .show();
                        }


                    }
                });
                break;
            case "Muscle":
                measurementText.setHint("Enter Muscle %");
                addMeasure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!measurementText.getText().toString().isEmpty() && dateChanged_watcher.equals("date has been set")) {
                            if (Double.parseDouble(measurementText.getText().toString()) <= 50 && Double.parseDouble(measurementText.getText().toString()) >= 20) {


                                measurement = Float.parseFloat(measurementText.getText().toString());
                                calendar.setTime(java_dateOfMeasure);
                                proposed_date = calendar.get(Calendar.YEAR);
                                if (proposed_date <= year_current) {

//                        Call<ResponseDB> callAddMeasurement = apiInterface.add_weight_measurement(Integer.parseInt(prefConFig.readId()), sql_date, measurement);
                                    Call<ResponseDB> callAddMeasurement = apiInterface.add_musclePercentage_measurement(Integer.parseInt(prefConFig.readId()), sql_date, measurement);
//                                addMeasurement(id, bodyPart, measurement, sql_date);
                                    SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Loading");
                                    pDialog.setCancelable(false);
                                    pDialog.show();
                                    callAddMeasurement.enqueue(new Callback<ResponseDB>() {
                                        @Override
                                        public void onResponse(Call<ResponseDB> call, Response<ResponseDB> response) {
                                            if (response.body().getResponse().equals("measurement added")) {
                                                pDialog.dismissWithAnimation();
                                                apiCall(Integer.parseInt(prefConFig.readId()), databasetable_name);
                                                measurementText.setText("");
                                                mDisplayDate.setText("Select a date");
                                                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                                        .setTitleText(getString(R.string.success_))
                                                        .setContentText(getString(R.string.measurement_added))
                                                        .show();

                                                int month = sql_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
                                                int year = sql_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();

//                                prefConFig.distplayToast(Integer.toString(month ));

                                                if (year == year_current) {
                                                    yvalue.add(new Entry(month, measurement));
                                                    Collections.sort(yvalue, new EntryXComparator());
                                                    set1 = new LineDataSet(yvalue, "Progress over the last 12 months");

                                                    dataSets = new ArrayList<>();

                                                    dataSets.add(set1);


                                                    data = new LineData(dataSets);
                                                    set1.notifyDataSetChanged();
                                                    data.notifyDataChanged();
                                                    mChart.notifyDataSetChanged();
                                                    mChart.invalidate();
                                                    mChart.setData(data);

                                                    XAxis xAxis = mChart.getXAxis();
                                                    xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
                                                    xAxis.setGranularity(1f);
                                                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);


                                                    mChart.setData(data);
                                                    mChart.getDescription().setEnabled(false);
                                                    mChart.setEnabled(true);

                                                }

                                            } else {
                                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                                        .setTitleText(getString(R.string.str_error))
                                                        .setContentText(getString(R.string.default_msg))
                                                        .show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseDB> call, Throwable t) {

                                        }
                                    });

                                } else {
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText(getString(R.string.str_error))
                                            .setContentText("Cannot enter future dates")
                                            .show();
                                    mDisplayDate.requestFocus();
                                    mDisplayDate.requestFocus();

                                }
//
//
                            } else {
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText(getString(R.string.str_error))
                                        .setContentText("enter a valid muscle percentage")
                                        .show();
                            }

                        } else {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(getString(R.string.str_error))
                                    .setContentText(getString(R.string.dateError))
                                    .show();

                        }


                    }
                });

                break;
            case "Water":
                measurementText.setHint("Enter water %");
                addMeasure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (!measurementText.getText().toString().isEmpty() && dateChanged_watcher.equals("date has been set")) {
                            measurement = Float.parseFloat(measurementText.getText().toString());
                            if (Double.parseDouble(measurementText.getText().toString()) <= 60 && Double.parseDouble(measurementText.getText().toString()) >= 40) {

                                calendar.setTime(java_dateOfMeasure);
                                proposed_date = calendar.get(Calendar.YEAR);
                                if (proposed_date <= year_current) {


                                    Call<ResponseDB> callAddMeasurement = apiInterface.add_waterPercentage_measurement(Integer.parseInt(prefConFig.readId()), sql_date, measurement);
                                    SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("Loading");
                                    pDialog.setCancelable(false);
                                    pDialog.show();
                                    callAddMeasurement.enqueue(new Callback<ResponseDB>() {
                                        @Override
                                        public void onResponse(Call<ResponseDB> call, Response<ResponseDB> response) {
                                            if (response.body().getResponse().equals("measurement added")) {
                                                pDialog.dismissWithAnimation();
                                                apiCall(Integer.parseInt(prefConFig.readId()), databasetable_name);

                                                measurementText.setText("");
                                                mDisplayDate.setText("Select a date");


                                                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                                        .setTitleText(getString(R.string.success_))
                                                        .setContentText(getString(R.string.measurement_added))
                                                        .show();

                                                int month = sql_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
                                                int year = sql_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();

//                                prefConFig.distplayToast(Integer.toString(month ));
                                                if (year == year_current) {
                                                    yvalue.add(new Entry(month, measurement));
                                                    Collections.sort(yvalue, new EntryXComparator());
                                                    set1 = new LineDataSet(yvalue, "Progress over the last 12 months");

                                                    dataSets = new ArrayList<>();

                                                    dataSets.add(set1);


                                                    data = new LineData(dataSets);
                                                    set1.notifyDataSetChanged();
                                                    data.notifyDataChanged();
                                                    mChart.notifyDataSetChanged();
                                                    mChart.invalidate();
                                                    mChart.setData(data);

                                                    XAxis xAxis = mChart.getXAxis();
                                                    xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
                                                    xAxis.setGranularity(1f);
                                                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);


                                                    mChart.setData(data);
                                                    mChart.getDescription().setEnabled(false);
                                                    mChart.setEnabled(true);

                                                }

                                            } else {
                                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                                        .setTitleText(getString(R.string.str_error))
                                                        .setContentText(getString(R.string.default_msg))
                                                        .show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseDB> call, Throwable t) {

                                        }
                                    });
                                } else {
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText(getString(R.string.str_error))
                                            .setContentText("Cannot enter future dates")
                                            .show();
                                    mDisplayDate.requestFocus();
                                    mDisplayDate.requestFocus();

                                }

//
//
                            } else {
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText(getString(R.string.str_error))
                                        .setContentText("enter a valid water percentage")
                                        .show();
                            }
                        } else {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText(getString(R.string.str_error))
                                    .setContentText(getString(R.string.dateError))
                                    .show();


                        }


                    }
                });
                break;
        }

        return view;
    }
}