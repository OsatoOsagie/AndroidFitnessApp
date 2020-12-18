package com.oo115.myapplication.bodyMeasures;


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
import com.oo115.myapplication.BodyPart;
import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.BodyMeasuresResponse;

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
public class BodyPartMeasurements extends Fragment {
    private Toolbar bodyToolbar;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button addMeasure;
    private float measurement;
    private SimpleDateFormat dateFormatter;
    private Date java_dateOfMeasure;
    private EditText measurementText;
    private String dateChanged_watcher;
    int bodyPart;
    private ListView listView;
    java.sql.Date sql_date;
    public static ApiInterface apiInterface;
    public static PrefConFig prefConFig;
    int id;
    private LineChart mChart = null;
    private int year_current;
    ArrayList<ILineDataSet> dataSets;

    ArrayList<String> measurements_size = new ArrayList<String>();

    ArrayList<String> measurements_date = new ArrayList<String>();
    ArrayList<String> measurements_Id = new ArrayList<String>();
    LineData data;
    LineDataSet set1;
    View view;
    //arrays for date and measurement size

    public BodyPartMeasurements() {
        // Required empty public constructor
    }

    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
    public static BodyPartMeasurements newInstance(int bodyPartID, ArrayList<String> measurement_size, ArrayList<String> measurement_date, boolean showInput) {
        BodyPartMeasurements f = new BodyPartMeasurements();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("bodyPartID", bodyPartID);
        args.putStringArrayList("measure_size", measurement_size);
        args.putStringArrayList("measure_date", measurement_date);
        args.putBoolean("showInput", showInput);
        f.setArguments(args);

        return f;
    }

    /**
     * Performs API call to get the history of measurement for each bodypart.
     * Checks if any body part has ever been entered by that user
     */

    public void apiCall(int id, int bodyPart) {
        mChart.setData(data);
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


        /*
        getting the users most recent measurements
         */
        Call<BodyMeasuresResponse> bodyMeasuresResponseCall = apiInterface.lastMeasureSearch(id, bodyPart);


        bodyMeasuresResponseCall.enqueue(new Callback<BodyMeasuresResponse>() {
            @Override
            public void onResponse(Call<BodyMeasuresResponse> call, Response<BodyMeasuresResponse> response) {
//                prefConFig.distplayToast(response.body().getResponse());

                // this if statement will check the response from the server to see if there is a measurement for that user in the database.
                if (response.body().getResponse().equals("Measurement Found")) {

                    //initialises an int for the size of the list sent back by the server
                    int listSize = response.body().getMeasurementArray().size();

                    //this will check if the size of the list sent back by the server is more than or equal to 5
                    //the nested for loops will the get the date and the measurements and store them in an array
                    if (listSize >= 5) {


                        for (int i = 0; i < 5; i++) {

                            measurements_date.add(i, response.body().getMeasurementArray().get(i).getDate());
                            measurements_size.add(i, response.body().getMeasurementArray().get(i).getMeasurement());
                            measurements_Id.add(i, response.body().getMeasurementArray().get(i).getId());



                        }

                    } else {
                        //if the list size is not up to 5, then we assign a new size to the array
                        //we then use a for  loop to get the size and the date of the measurement

//                        measurements_size = new String[listSize];
//


                        for (int i = 0; i < listSize; i++) {

                            measurements_date.add(i, response.body().getMeasurementArray().get(i).getDate());
                            measurements_size.add(i, response.body().getMeasurementArray().get(i).getMeasurement());
                            measurements_Id.add(i, response.body().getMeasurementArray().get(i).getId());


                        }
//
//

                    }


                }


                //ist view for history of measurements
                listView = view.findViewById(R.id.listView);

                //giving the list view an adaptor of class bodyPartHistoryAdaptor
                BodyPartHistoryAdaptor bodyPartHistoryAdaptor = new BodyPartHistoryAdaptor(getActivity(), measurements_size, measurements_date, measurements_Id);
                listView.setAdapter(bodyPartHistoryAdaptor);
                bodyPartHistoryAdaptor.notifyDataSetChanged();
//                mChart.setData(data);




            }

            @Override
            public void onFailure(Call<BodyMeasuresResponse> call, Throwable t) {

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bodypart_details, container, false);
        mChart = view.findViewById(R.id.weightChart);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        Calendar calendar = Calendar.getInstance();
        mChart.invalidate();


//        dateFormatter = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        dateChanged_watcher = "date has not been set";

        int extras = getArguments().getInt("bodyPartID");
        ArrayList<String> size = getArguments().getStringArrayList("measure_size");
        ArrayList<String> date = getArguments().getStringArrayList("measure_date");
        ArrayList<Date> date_array = new ArrayList<Date>();
        ArrayList<Integer> month = new ArrayList<Integer>();

        for (int i = 0; i < date.size(); i++) {


            try {
                Date datetype = new SimpleDateFormat("yyyy-MM-dd").parse(date.get(i));

                month.add(datetype.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue());

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }


//        ArrayList<String> size_draw = new ArrayList<String>();

//
//
//        assert size != null;
//        yvalue.add(new Entry(1, Float.parseFloat(size.get(0))));
//        yvalue.add(new Entry(2, Float.parseFloat(size.get(1))));
//
        String[] values = new String[]{"", "Jan", "feb", "March", "April", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        ArrayList<Entry> yvalue = new ArrayList<>();

        assert size != null;

        if (!size.isEmpty()) {

            for (int i = 0; i < size.size(); i++) {


                yvalue.add(new Entry(month.get(i), Float.parseFloat(size.get(i))));
                Collections.sort(yvalue, new EntryXComparator());


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
                mChart.fitScreen();
            }

        }


        //setting up the tool bar to have a back arrow on create and setting the title of the fragment
        bodyToolbar = view.findViewById(R.id.bodyTrackingDetailsToolbar_LeftArm);


        //removing the actionbar so that tool bar can show
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        bodyToolbar.setNavigationIcon(R.drawable.ic_back);
        bodyToolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());


        // Add the other graph

//        mChart.setDescription(null);
//        mGraph = new Graph(getContext(), mChart, "");
//        mChart.setDescription(null);
//        mChart.setScaleEnabled(false);
//        mChart.setDragEnabled(true);

//        mChart.set

//        mGraph = new Graph(getContext(), mChart, "");


        //a switch statements which collects the extra sent over (the body part position on the list) and determines what to display
        switch (extras) {
            case 0:


                bodyToolbar.setTitle(R.string.left_arm);
                bodyPart = BodyPart.LEFTARM;

                apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);

                break;
            case 1:
                bodyToolbar.setTitle(R.string.right_arm);
                bodyPart = BodyPart.RIGHTARM;
                apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);

//                DrawGraph(Integer.parseInt(prefConFig.readId()), bodyPart);
                break;
            case 2:

                bodyToolbar.setTitle(R.string.pectoraux);
                bodyPart = BodyPart.PECTORAUX;
                apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);


                break;
            case 3:

                bodyToolbar.setTitle(R.string.waist);
                bodyPart = BodyPart.WAIST;
                apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);

                break;
            case 4:

                bodyToolbar.setTitle(R.string.behind);
                bodyPart = BodyPart.BEHIND;
                apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);

                break;
            case 5:

                bodyToolbar.setTitle(R.string.left_thigh);
                bodyPart = BodyPart.LEFTTHIGH;
                apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);

                break;
            case 6:

                bodyToolbar.setTitle(R.string.right_thigh);
                bodyPart = BodyPart.RIGHTTHIGH;
                apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);

                break;
            case 7:

                bodyToolbar.setTitle(R.string.left_calves);
                bodyPart = BodyPart.LEFTCALVES;
                apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);

                break;
            case 8:

                bodyToolbar.setTitle(R.string.right_calves);
                bodyPart = BodyPart.RIGHTCALVES;
                apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);
                break;
        }


        //setting up select date
        mDisplayDate = view.findViewById(R.id.measuredate);
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

                //getting the current year
                year_current = calendar.get(Calendar.YEAR);
                //getting the current month
                int month_current = calendar.get(Calendar.MONTH);
                //getting the current day
                //day of month because they are different  number of days in each month
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Objects.requireNonNull(getContext()),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year_current, month_current, day

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
        //add measure button
        addMeasure = view.findViewById(R.id.buttonAddMeasure);
        measurementText = view.findViewById(R.id.bodyPartMeasurement);
        addMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!measurementText.getText().toString().isEmpty() && dateChanged_watcher.equals("date has been set")) {
                    measurement = Float.parseFloat(measurementText.getText().toString());
                    id = Integer.parseInt(prefConFig.readId());

                    calendar.setTime(java_dateOfMeasure);
                    int proposed_date = calendar.get(Calendar.YEAR);

                    if (proposed_date <= year_current) {
                        Call<BodyMeasuresResponse> callAddMeasurement = apiInterface.addMeasurement(id, bodyPart, measurement, sql_date);

                        SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Loading");
                        pDialog.setCancelable(false);
                        pDialog.show();


                        callAddMeasurement.enqueue(new Callback<BodyMeasuresResponse>() {
                            @Override
                            public void onResponse(Call<BodyMeasuresResponse> call, Response<BodyMeasuresResponse> response) {
                                if (response.body().getResponse().equals("measurement added")) {
                                    measurements_size.clear();
                                    measurements_date.clear();
                                    measurements_Id.clear();
                                    apiCall(Integer.parseInt(prefConFig.readId()), bodyPart);
                                    pDialog.getProgressHelper().stopSpinning();
                                    pDialog.dismissWithAnimation();


                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText(getString(R.string.success_))
                                            .setContentText(getString(R.string.measurement_added))
                                            .show();

//                                prefConFig.distplayToast(sql_date.toString());

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
                            public void onFailure(Call<BodyMeasuresResponse> call, Throwable t) {
                                prefConFig.distplayToast("something went wrong!");
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


                } else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getString(R.string.str_error))
                            .setContentText(getString(R.string.require_fields))
                            .show();

                }

            }
        });

        //delete


//prefConFig.distplayToast(Integer.toString(size_draw.size()));
        return view;
    }


}