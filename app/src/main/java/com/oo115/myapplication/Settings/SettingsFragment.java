package com.oo115.myapplication.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.oo115.myapplication.GettingStarted.Getting_startedFragment;
import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.RegisterAndLoginActivity;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.UserDB;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private PrefConFig prefConFig;
    public static ApiInterface apiInterface;
    private String[] settings_titles = {"Email Address", "Change Password", "Delete Account", "Getting Started"};

    public SettingsFragment() {
        // Required empty public constructor
    }

    private AdapterView.OnItemClickListener onClickListItem = (parent, view, position, id) -> {

        if (position == 1) {
            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_containerHome, new ChangePasswordFragment());
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        } else if (position == 0) {
            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_containerHome, new Change_EmailFragment());
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();

        } else if (position == 2) {

            new SweetAlertDialog(Objects.requireNonNull(getActivity()), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(getString(R.string.delete_account))
                    .setContentText(getString(R.string.delete_account_confirm))
                    .setCancelText(getString(R.string.dialog_cancel))
                    .setConfirmText(getString(R.string.str_ok))
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            Call<UserDB> delete_profile = apiInterface.delete_profile(prefConFig.readEmail());

                            delete_profile.enqueue(new Callback<UserDB>() {
                                @Override
                                public void onResponse(Call<UserDB> call, Response<UserDB> response) {
                                    if (response.body().getResponse().equals("deleted")) {
                                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText(getString(R.string.success_))
                                                .setContentText("Your profile has been deleted")
                                                .show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserDB> call, Throwable t) {

                                }
                            });

                            Intent intToMain = new Intent(getActivity(), RegisterAndLoginActivity.class);
                            startActivity(intToMain);


                        }
                    })
                    .show();


        } else {
            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_containerHome, new Getting_startedFragment());
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        initialisation
        Toolbar toolbar = view.findViewById(R.id.settings_toolBar);
        ListView listView = view.findViewById(R.id.settings_listView);


        ListAdaptor listAdaptor = new ListAdaptor(this.getActivity(), settings_titles);
        listView.setAdapter(listAdaptor);

        //removing the actionbar so that tool bar can show
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
        toolbar.setTitle(R.string.settings);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        listView.setOnItemClickListener(onClickListItem);

        return view;


    }
}
