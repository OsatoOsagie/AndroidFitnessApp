package com.oo115.myapplication;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.Profile_UpdateDB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {


    private static final String TAG = "profileFragment";
    private TextView mDisplayDate;
    private EditText centimetersET, feetET, inchesET;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String user_gender, user_goal, date;
    private SimpleDateFormat dateFormatter;
    private java.sql.Date sql_date = null;
    private Date java_dateOfMeasure;
    private int current_year, current_day, current_month;
    private PrefConFig prefConFig;
    private RadioButton cm;
    private RadioButton pounds;
    private RadioButton kilograms;
    private RadioButton heightMeasurement;
    public static ApiInterface apiInterface;
    private double user_height = 0;
    private String dateChanged_watcher = "tester";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Objects.requireNonNull(getActivity()).setTitle("Profile");


        Spinner gender = view.findViewById(R.id.gender);
        Spinner goal = view.findViewById(R.id.goalSpinner);
        mDisplayDate = view.findViewById(R.id.selectBirthday);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        RadioButton feet_rad = view.findViewById(R.id.feetRadioButton);

        cm = view.findViewById(R.id.centimetersRadioButton);
        centimetersET = view.findViewById(R.id.centimeteresET);
        RadioGroup heightgroup = view.findViewById(R.id.heightRadioGroup);
        feetET = view.findViewById(R.id.height_feet);
        inchesET = view.findViewById(R.id.height_inches);
        Button btnSave = view.findViewById(R.id.saveBtn2);
        DateFormat dateFormater_two = new SimpleDateFormat("dd/MM-/yyy");
        centimetersET.setVisibility(View.GONE);


        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        /*
         *
         * HEIGHT
         */

        feet_rad.setChecked(true);

        /*
        If user alredy has a height present when the page loads then calculate their height in inches
         */
        if (!prefConFig.readHeight().equals("no height found")) {
            double height_feet = (Double.parseDouble(prefConFig.readHeight()) / 30.38);

            //users height in feet
            double height_inch = (height_feet - Math.floor(height_feet)) * 10;


            int height_ft = (int) (Math.floor(Double.parseDouble(prefConFig.readHeight()) / 30.38));
            int height_in = (int) ((Math.round(height_inch)));

            inchesET.setText(Integer.toString(height_in));
            feetET.setText(Integer.toString(height_ft));
        }

        /*
        Checks if the user has selected cm or feet as the metrics for their height measurements
         */
        heightgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                heightMeasurement = view.findViewById(checkedId);

                if (!prefConFig.readHeight().equals("no height found")) {
                    if (cm.isChecked()) {

                        centimetersET.setVisibility(View.VISIBLE);
                        centimetersET.setText(prefConFig.readHeight());
                        feetET.setVisibility(View.GONE);
                        inchesET.setVisibility(View.GONE);

                    } else {
                        centimetersET.setVisibility(View.GONE);
                        feetET.setVisibility(View.VISIBLE);
                        inchesET.setVisibility(View.VISIBLE);
                        double height_feet = (Double.parseDouble(prefConFig.readHeight()) / 30.38);

                        //users height in feet
                        double height_inch = (height_feet - Math.floor(height_feet)) * 10;


                        int height_ft = (int) (Math.floor(Double.parseDouble(prefConFig.readHeight()) / 30.38));
                        int height_in = (int) ((Math.round(height_inch)));

                        inchesET.setText(Integer.toString(height_in));
                        feetET.setText(Integer.toString(height_ft));
                    }
                } else {
                    if (cm.isChecked()) {

                        centimetersET.setVisibility(View.VISIBLE);
                        feetET.setVisibility(View.GONE);
                        inchesET.setVisibility(View.GONE);

                    } else {
                        centimetersET.setVisibility(View.GONE);
                        feetET.setVisibility(View.VISIBLE);
                        inchesET.setVisibility(View.VISIBLE);
                    }
                }


            }
        });


        /*checks if the user already has a dob in the database,
        if they do, it is split up into a string and displayed to the user

         */
        if (!prefConFig.readDob().equals("no dob found")) {
            String dob = prefConFig.readDob();
            String[] dateParts = dob.split("-");
            String day_ofDOb = dateParts[0];
            String month_ofDOb = dateParts[1];

            String yearOfBirth = dateParts[2];
            date = yearOfBirth + "/" + month_ofDOb + "/" + day_ofDOb;
            mDisplayDate.setText(date);
            dateChanged_watcher = "date has been set";
            try {
                java_dateOfMeasure = dateFormatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sql_date = new java.sql.Date(java_dateOfMeasure.getTime());
        }



        /*
         Check if the user has selected a new date for their dob,
         if they do, this date is displayed and set as the date to be sent to the server
         */
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


                        prefConFig.distplayToast(date);
                        mDisplayDate.setText(date);
                        try {
                            java_dateOfMeasure = dateFormatter.parse(date);
                            sql_date = new java.sql.Date(java_dateOfMeasure.getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }

                };
                //create calender object so that when text view is clicked it immediately goes to the current date
                Calendar calendar = Calendar.getInstance();
                //getting the current year
                current_year = calendar.get(Calendar.YEAR);
                //getting the current month
                current_month = calendar.get(Calendar.MONTH);
                //getting the current day
                //day of month because they are different  number of days in each month
                current_day = calendar.get(Calendar.DAY_OF_MONTH);
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


        /*
         *
         * GENDER
         */
        //adaptor= container that will hold gender values
        ArrayAdapter<String> myGender = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));


        //make it a dropdown list
        myGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set gender spinner to adaptor
        gender.setAdapter(myGender);
        String[] genders = getResources().getStringArray(R.array.gender);
        user_gender = gender.getSelectedItem().toString();

        // checking if the user already has a profile created
        switch (prefConFig.readSex()) {

            case "Male":
                gender.setSelection(0);
                break;
            case "Female":
                gender.setSelection(1);
                break;
            case "Prefer not to say":
                gender.setSelection(2);
                break;
        }

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user_gender = genders[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
         *
         * GAOL
         */
        //adaptor= container that will hold gender values
        ArrayAdapter<String> myGoal = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.goal));

        switch (prefConFig.readGoal()) {

            case "Fat loss":
                goal.setSelection(0);
                break;
            case "Muscle Gainz":
                goal.setSelection(1);
                break;
            case "Maintenance":
                goal.setSelection(2);
                break;
        }
        //make it a dropdown list
        myGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set gender spinner to adaptor
        goal.setAdapter(myGoal);
        String[] goals = getResources().getStringArray(R.array.goal);
        user_goal = goal.getSelectedItem().toString();
        goal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user_goal = goals[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        /*
         *
         * SAVE
         */

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!dateChanged_watcher.equals("date has been set")) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getString(R.string.str_error))
                            .setContentText(getString(R.string.dateError))
                            .show();
                }

                if (cm.isChecked()) {
                    if (centimetersET.getText().toString().isEmpty() || Double.parseDouble(centimetersET.getText().toString()) > 275 ||
                            Double.parseDouble(centimetersET.getText().toString()) < 55 || centimetersET.getText().toString().startsWith("0")) {
                        centimetersET.setError("Enter a valid height");
                        centimetersET.requestFocus();


                    } else {
                        user_height = Integer.parseInt(centimetersET.getText().toString());
                    }
                } else {
                    if ((!feetET.getText().toString().isEmpty() && !inchesET.getText().toString().isEmpty())) {
                        if (((Integer.parseInt(inchesET.getText().toString()) <= 12) && ((Integer.parseInt(feetET.getText().toString()) <= 8) &&
                                (Integer.parseInt(feetET.getText().toString()) >= 1))) && (!inchesET.getText().toString().startsWith("0") && !feetET.getText().toString().startsWith("0"))) {
                            user_height = (Integer.parseInt(feetET.getText().toString()) * 30.48) + (Integer.parseInt(inchesET.getText().toString()) * 2.54);

                        } else {
                            feetET.setError("Enter a valid height");
                            feetET.requestFocus();



                        }
                    } else {
                        feetET.setError("Enter a valid height");
                        feetET.requestFocus();


                        inchesET.setError("Enter a valid height");
                        inchesET.requestFocus();
                    }
                }

                if (dateChanged_watcher.equals("date has been set") && user_height > 0) {

                    Date date_current = new Date();


                    String current_date = dateFormater_two.format(date_current);
                    String[] current_dateParts = current_date.split("/");

                    String current_year = current_dateParts[2];


                    String dob = date;
                    String[] dateParts = dob.split("/");
                    String yearOfBirth = dateParts[2];

                    double users_age = Integer.parseInt(current_year) - Integer.parseInt(yearOfBirth);

                    if (users_age >= 13) {
                        Call<Profile_UpdateDB> update_profile = apiInterface.updateProfile(Integer.parseInt(prefConFig.readId()), user_gender, sql_date, user_height, user_goal);


                        SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Loading");
                        pDialog.setCancelable(false);
                        pDialog.show();

                        update_profile.enqueue(new Callback<Profile_UpdateDB>() {
                            @Override
                            public void onResponse(Call<Profile_UpdateDB> call, Response<Profile_UpdateDB> response) {

                                if (response.body().getResponse().equals("ok")) {
                                    prefConFig.writeUser_dob(sql_date.toString());
                                    prefConFig.writeUser_sex(user_gender);
                                    prefConFig.writeUser_height(Double.toString(user_height));
                                    prefConFig.writeUser_goal(user_goal);

                                    pDialog.getProgressHelper().stopSpinning();
                                    pDialog.dismissWithAnimation();

                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText(getString(R.string.success_))
                                            .setContentText(getString(R.string.profile_updated))
                                            .show();
                                } else {
                                    pDialog.getProgressHelper().stopSpinning();
                                    pDialog.dismissWithAnimation();

                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText(getString(R.string.str_error))
                                            .setContentText(getString(R.string.create_profile_msg))
                                            .show();

                                }

                            }

                            @Override
                            public void onFailure(Call<Profile_UpdateDB> call, Throwable t) {

                            }
                        });
                    } else {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(getString(R.string.str_error))
                                .setContentText("Please enter a valid age above 13")
                                .show();

                    }
                }

            }









        });
        return view;
    }
}
