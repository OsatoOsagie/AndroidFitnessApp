package com.oo115.myapplication.ForgotPassword;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.ForgotEmailDB;

import java.util.Objects;
import java.util.Random;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import cn.refactor.lib.colordialog.PromptDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * This class handles the implementation of the forgot password feature
 */
public class ForgotPasswordFragment extends Fragment {

    private EditText email_address;
    private TextView responseMsg;
    private Button confirm_emailBtn;
    private String user_email = "empty";

    public static PrefConFig prefConFig;
    public static ApiInterface apiInterface;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        confirm_emailBtn = view.findViewById(R.id.forgotPassword_emailConfirmBtn);
        email_address = view.findViewById(R.id.forgotPassword_emailET);
        responseMsg = view.findViewById(R.id.forgotPassword_responseMsg);
        prefConFig = new PrefConFig(Objects.requireNonNull(getActivity()));

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        responseMsg.setVisibility(View.GONE);

        confirm_emailBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {


                user_email = email_address.getText().toString();
                //checking if user email matches the required patterns
                if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
                    email_address.setError("Enter a valid email");
                    email_address.requestFocus();
                } else {


                    //creating a random string to set as the users password
                    int leftLimit = 48; // numeral '0'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = 10;
                    Random random = new Random();

                    //generating a random password string
                    String generatedString = random.ints(leftLimit, rightLimit + 1)
                            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                            .limit(targetStringLength)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();


                    //if the user exists in the database then execute

                    //alert asking the user to confirm their password reset
                    new AlertDialog.Builder(Objects.requireNonNull(getContext()))
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Forgot password")
                            .setMessage("Clicking yes will reset your password, are you sure you want to continue?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    new Thread(new Runnable() {
                                        //                                It’s also important to send email in a separate Thread to avoid NetworkOnMainException.
                                        @Override
                                        public void run() {
                                            try {
                                                //sending an email to the user with their new password
                                                GMailSender sender = new GMailSender("totalfitnessapplication@gmail.com",
                                                        "uniProject");
                                                sender.sendMail("Password Reset", generatedString, "totalfitnessapplication@gmail.com", user_email);

                                                //making a call to the web service via REST API Client
                                                Call<ForgotEmailDB> call = apiInterface.performEmailSearch(user_email, generatedString);

                                                call.enqueue(new Callback<ForgotEmailDB>() {
                                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                                    @Override
                                                    public void onResponse(Call<ForgotEmailDB> call, Response<ForgotEmailDB> response) {

                                                        //checking the response from the server
                                                        if (response.body().getResponse().equals("changed")) {
                                                            new PromptDialog(getContext())
                                                                    .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                                                                    .setAnimationEnable(true)
                                                                    .setTitleText(getString(R.string.str_confrimation_sent))
                                                                    .setContentText(getString(R.string.str_confrimation_sent_text))
                                                                    .setPositiveListener(getString(R.string.str_ok), new PromptDialog.OnPositiveListener() {
                                                                        @Override
                                                                        public void onClick(PromptDialog dialog) {
                                                                            dialog.dismiss();
                                                                        }
                                                                    }).show();
                                                        } else {
                                                            new PromptDialog(getContext())
                                                                    .setDialogType(PromptDialog.DIALOG_TYPE_WRONG)
                                                                    .setAnimationEnable(true)
                                                                    .setTitleText(getString(R.string.str_error))
                                                                    .setContentText(getString(R.string.str_invalid_email))
                                                                    .setPositiveListener(getString(R.string.str_ok), new PromptDialog.OnPositiveListener() {
                                                                        @Override
                                                                        public void onClick(PromptDialog dialog) {
                                                                            dialog.dismiss();
                                                                        }
                                                                    }).show();

                                                        }

                                                    }

                                                    @Override
                                                    public void onFailure(Call<ForgotEmailDB> call, Throwable t) {

                                                    }
                                                });
                                            } catch (Exception e) {
                                                Log.e("SendMail", e.getMessage(), e);
                                            }
                                        }

                                    }).start();

                                }

                            })
                            .setNegativeButton("No", null)
                            .show();


                }
            }
        });


        return view;
    }

}
