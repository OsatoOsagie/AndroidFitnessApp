package com.oo115.myapplication.Workout_feature;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.Exercise_historyResponse;
import com.oo115.myapplication.retrofitAPI.ResponseDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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
public class ExerciseLogFragment extends Fragment {

    private ArrayList<String> workout_date = new ArrayList<String>();
    private ArrayList<ArrayList<String>> workout_id = new ArrayList<ArrayList<String>>();
    private ArrayList<String> table_data_id = new ArrayList<String>();
    private ArrayList<String> table_data_id_two = new ArrayList<String>();
    private ArrayList<String> table_data_id_three = new ArrayList<String>();
    private ArrayList<String> table_data_id_four = new ArrayList<String>();
    private ArrayList<String> table_data_id_five = new ArrayList<String>();
    private ArrayList<String> table_data = new ArrayList<String>();
    private ArrayList<String> table_data_two = new ArrayList<String>();
    private ArrayList<String> table_data_three = new ArrayList<String>();
    private ArrayList<String> table_data_four = new ArrayList<String>();
    private ArrayList<String> table_data_five = new ArrayList<String>();

    private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

    public ExerciseLogFragment() {
        // Required empty public constructor
    }

    public static ExerciseLogFragment newInstance(String exercise_name, boolean showInput) {
        ExerciseLogFragment f = new ExerciseLogFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("Exercise_name", exercise_name);
        args.putBoolean("showInput", showInput);
        f.setArguments(args);

        return f;
    }

    public static ApiInterface apiInterface;
    private Chronometer chronometer;
    private Button add;
    private long pauseOffSet;
    private boolean running;
    private TextView mDisplayDate, ex_name;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int current_day, current_year, current_month;
    private String date;
    private PrefConFig prefConFig;
    private SimpleDateFormat dateFormatter;
    private java.sql.Date sql_date = null;
    private Date java_dateOfMeasure;
    private Date _dateOfMeasure;

    private Toolbar toolbar;
    private EditText reps, sets, weight_lifted;
    private int workout_num;
    private ExpandableListView history;
    private String hist_toAdd;
    private String extras_exname;
    ExerciseLogListView adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise_log, container, false);


        ex_name = view.findViewById(R.id.ex_name_log);
        mDisplayDate = view.findViewById(R.id.date_picker_log);
        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        toolbar = view.findViewById(R.id.toolbar_exercise_log);
        add = view.findViewById(R.id.add_btn_log);
        sets = view.findViewById(R.id.sets_input);
        reps = view.findViewById(R.id.reps_input);
        weight_lifted = view.findViewById(R.id.weight_lifted);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        history = view.findViewById(R.id.expandable_lV_history);
        extras_exname = getArguments().getString("Exercise_name");
        //removing the actionbar so that tool bar can show
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setTitle("Exercise Log");
        Calendar calendar = Calendar.getInstance();
        //getting the current year
        current_year = calendar.get(Calendar.YEAR);
        //getting the current month
        current_month = calendar.get(Calendar.MONTH);
        //getting the current day
        //day of month because they are different  number of days in each month
        current_day = calendar.get(Calendar.DAY_OF_MONTH);

        adapter = new ExerciseLogListView(getContext(), workout_date, table, workout_id);
        history.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        ex_name.setText(extras_exname);

        mDisplayDate.setText("Select Date");

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //we need to add 1 to the month because january is 0 and december is 11
                        month = month + 1;
                        current_day = dayOfMonth;
                        current_year = year;
                        current_month = month;
                        date = current_day + "/" + current_month + "/" + current_year;
                        mDisplayDate.setText(date);

                        try {
                            java_dateOfMeasure = dateFormatter.parse(date);
                            sql_date = new java.sql.Date(java_dateOfMeasure.getTime());
                            prefConFig.distplayToast(sql_date.toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }




                    }

                };
                //create calender object so that when text view is clicked it immediately goes to the current date

                DatePickerDialog dialog = new DatePickerDialog(
                        Objects.requireNonNull(getContext()),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        current_year, current_month, current_day

                );


                //making background transparent
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }

        });


        refresh_listView(Integer.parseInt(prefConFig.readId()), extras_exname);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reps sets and weight


                if (sql_date != null) {
                    if (!reps.getText().toString().isEmpty() && !sets.getText().toString().isEmpty() && !weight_lifted.getText().toString().isEmpty()) {
                        int reps_done = Integer.parseInt(reps.getText().toString());
                        int set = Integer.parseInt(sets.getText().toString());
                        double weight = Integer.parseInt(weight_lifted.getText().toString());


                        Call<ResponseDB> add_to_log = apiInterface.add_exercise_history(sql_date, sql_date.toString(), set, reps_done, weight, Integer.parseInt(prefConFig.readId()), extras_exname);

                        SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Loading");
                        pDialog.setCancelable(false);
                        pDialog.show();


                        add_to_log.enqueue(new Callback<ResponseDB>() {
                            @Override
                            public void onResponse(Call<ResponseDB> call, Response<ResponseDB> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getResponse().equals("history added")) {
                                        prefConFig.distplayToast(sql_date.toString());

                                        pDialog.getProgressHelper().stopSpinning();
                                        pDialog.dismissWithAnimation();
                                        refresh_listView(Integer.parseInt(prefConFig.readId()), extras_exname);
                                        adapter.notifyDataSetChanged();


                                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText(getString(R.string.success_))
                                                .setContentText(getString(R.string.histroy_added))
                                                .show();
                                    } else {
                                        prefConFig.distplayToast("Failed");
//
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDB> call, Throwable t) {

                            }
                        });

                    }
                } else {
                    prefConFig.distplayToast("Please select date");
                }


            }
        });


        table.add(table_data);
        table.add(table_data_two);
        table.add(table_data_three);
        table.add(table_data_four);
        table.add(table_data_five);
        adapter.notifyDataSetChanged();




        return view;
    }

    private void refresh_listView(int user_id, String extras_exname) {
        table.clear();
        workout_date.clear();

        Call<Exercise_historyResponse> get_workout_num = apiInterface.get_workout_num(user_id, extras_exname);
        get_workout_num.enqueue(new Callback<Exercise_historyResponse>() {
            @Override
            public void onResponse(Call<Exercise_historyResponse> call, Response<Exercise_historyResponse> response) {

//                hist_toAdd= response.body().getWorkoutOne_array().get(0).getSet() + "X" + response.body().getWorkoutOne_array().get(0).getRep();

                if (response.body().getWorkoutOne_array() != null) {
                    for (int i = 0; i < response.body().getWorkoutOne_array().size(); i++) {
                        table_data.add(response.body().getWorkoutOne_array().get(i).getSet() + "X" + response.body().getWorkoutOne_array().get(i).getRep());
                        table_data_id.add(response.body().getWorkoutOne_array().get(i).getId());
                        adapter.notifyDataSetChanged();

                    }
                    workout_date.add(response.body().getWorkoutOne_array().get(0).getWorkout_date());
                    workout_id.add(table_data_id);
                }


                if (response.body().getWorkoutTwo_array() != null) {
                    for (int i = 0; i < response.body().getWorkoutTwo_array().size(); i++) {
                        table_data_two.add(response.body().getWorkoutTwo_array().get(i).getSet() + "X" + response.body().getWorkoutTwo_array().get(i).getRep());
                        table_data_id_two.add(response.body().getWorkoutTwo_array().get(i).getId());
                        adapter.notifyDataSetChanged();


                    }
                    workout_date.add(response.body().getWorkoutTwo_array().get(0).getWorkout_date());
                    workout_id.add(table_data_id_two);
                }


                if (response.body().getWorkoutThree_array() != null) {
                    for (int i = 0; i < response.body().getWorkoutThree_array().size(); i++) {
                        table_data_three.add(response.body().getWorkoutThree_array().get(i).getSet() + "X" + response.body().getWorkoutThree_array().get(i).getRep());
                        table_data_id_three.add(response.body().getWorkoutThree_array().get(i).getId());
                        adapter.notifyDataSetChanged();


                    }
                    workout_date.add(response.body().getWorkoutThree_array().get(0).getWorkout_date());
                    workout_id.add(table_data_id_three);
                }

                if (response.body().getWorkoutFour_array() != null) {
                    for (int i = 0; i < response.body().getWorkoutFour_array().size(); i++) {
                        table_data_four.add(response.body().getWorkoutFour_array().get(i).getSet() + "X" + response.body().getWorkoutFour_array().get(i).getRep());
                        table_data_id_four.add(response.body().getWorkoutFour_array().get(i).getId());
                        adapter.notifyDataSetChanged();


                    }
                    workout_date.add(response.body().getWorkoutFour_array().get(0).getWorkout_date());
                    workout_id.add(table_data_id_four);
                }

                if (response.body().getWorkoutFive_array() != null) {
                    for (int i = 0; i < response.body().getWorkoutFive_array().size(); i++) {
                        table_data_five.add(response.body().getWorkoutFive_array().get(i).getSet() + "X" + response.body().getWorkoutFive_array().get(i).getRep());
                        table_data_id_five.add(response.body().getWorkoutFive_array().get(i).getId());
                        adapter.notifyDataSetChanged();


                    }
                    workout_date.add(response.body().getWorkoutFive_array().get(0).getWorkout_date());
                    workout_id.add(table_data_id_five);
                }

                adapter.notifyDataSetChanged();


            }


            @Override
            public void onFailure(Call<Exercise_historyResponse> call, Throwable t) {

            }
        });

        table.add(table_data);
        table.add(table_data_two);
        table.add(table_data_three);
        table.add(table_data_four);
        table.add(table_data_five);



    }


}
