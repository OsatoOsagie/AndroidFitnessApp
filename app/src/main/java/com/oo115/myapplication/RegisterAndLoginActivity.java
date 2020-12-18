package com.oo115.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.Recent_weightSearch;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAndLoginActivity extends AppCompatActivity implements LoginFragment.OnLoginFormActivityListener {

    public static PrefConFig prefConFig;
    public static ApiInterface apiInterface;
    LoginFragment loginFragment;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefConFig = new PrefConFig(this);
        prefConFig.writeLoginStatus(false);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragment_containerHome) != null) {
            if (savedInstanceState != null) {
                return;
            }

            //checking if a user is already loggin
            if (prefConFig.readLoginStatus()) {
                Intent i = new Intent(RegisterAndLoginActivity.this, MainActivity.class);
                startActivity(i);

            } else {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerHome, new LoginFragment()).commit();
            }


        }



    }

    /*
    This method check the logins status of a user, if they are a valid user it starts a new activit
     */
    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerHome, new RegisterFragment()).addToBackStack(null).commit();

        if (prefConFig.readLoginStatus()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        } else {
//            prefConFig.distplayToast("Something went wrong please try again");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerHome, new RegisterFragment()).addToBackStack(null).commit();
        }


    }

    /*
  check the login status of a user, if true then start new activity else stay on the login fragment
   */
    @Override
    public void performLogin(String name, String id) {
        if (!prefConFig.readLoginStatus()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerHome, new LoginFragment()).addToBackStack(null).commit();
        }


        Call<Recent_weightSearch> weight_search = apiInterface.recent_weight_measurements(Integer.parseInt(id));


        weight_search.enqueue(new Callback<Recent_weightSearch>() {
            @Override
            public void onResponse(Call<Recent_weightSearch> call, Response<Recent_weightSearch> response) {
                prefConFig.writeUser_weight(response.body().getUser_weight());


            }

            @Override
            public void onFailure(Call<Recent_weightSearch> call, Throwable t) {

            }
        });

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


    }

}