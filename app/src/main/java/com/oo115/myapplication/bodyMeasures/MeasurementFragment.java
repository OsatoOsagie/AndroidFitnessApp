package com.oo115.myapplication.bodyMeasures;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.oo115.myapplication.BodyPart;
import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.BodyMeasuresResponse;

import java.util.ArrayList;
import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeasurementFragment extends Fragment {
    BodyPart bodyPartNum;

    String[] measurements_array = new String[3];
    private static final String TAG = "profileFragment";
    ListView listView;
    String[] bodyPart = {"Left bicep", "Right bicep", "Chest", "Waist", "Hips", "Left thigh", "Right thigh", "Left calves", "Right calves"};
    //datasource for the pictures of the macronutrients
    Integer[] bodyPartIMV = {R.drawable.ic_arm_measure, R.drawable.ic_arm_measure, R.drawable.ic_chest_measure, R.drawable.ic_waist_measure,
            R.drawable.ic_buttock_measure, R.drawable.ic_tight_measure, R.drawable.ic_tight_measure, R.drawable.ic_calve_measure, R.drawable.ic_calve_measure};

    int[] bodypartNumber = {BodyPart.LEFTARM, BodyPart.RIGHTARM, BodyPart.PECTORAUX, BodyPart.WAIST, BodyPart.BEHIND, BodyPart.LEFTTHIGH, BodyPart.RIGHTTHIGH, BodyPart.LEFTCALVES, BodyPart.RIGHTCALVES};
    String[] recent_measureArray = new String[9];


    public static ApiInterface apiInterface;
    public static PrefConFig prefConFig;
    private int id;
    ArrayList<String> measurements_size = new ArrayList<String>();

    ArrayList<String> measurements_date = new ArrayList<String>();
    ArrayList<String> measurements_Id = new ArrayList<String>();
    int bodypart_num;

    public MeasurementFragment() {
        // Required empty public constructor
    }


    public void apiCall(int bodypart_num, int bodyPartID) {


        Call<BodyMeasuresResponse> bodyMeasuresResponse_forGraphCall = apiInterface.measurementsFor_graph(Integer.parseInt(prefConFig.readId()), bodypart_num);


        bodyMeasuresResponse_forGraphCall.enqueue(new Callback<BodyMeasuresResponse>() {
            @Override
            public void onResponse(Call<BodyMeasuresResponse> call, Response<BodyMeasuresResponse> response) {
                if (response.body().getResponse().equals("Measurement Found")) {

                    //initialises an int for the size of the list sent back by the server
                    int listSize = response.body().getMeasurementArray().size();

                    //this will check if the size of the list sent back by the server is more than or equal to 5
                    //the nested for loops will the get the date and the measurements and store them in an array


                    for (int i = 0; i < listSize; i++) {

                        measurements_date.add(i, response.body().getMeasurementArray().get(i).getDate());
                        measurements_size.add(i, response.body().getMeasurementArray().get(i).getMeasurement());
                        measurements_Id.add(i, response.body().getMeasurementArray().get(i).getId());


                    }


                    BodyPartMeasurements fragment = BodyPartMeasurements.newInstance(bodyPartID, measurements_size, measurements_date, true);

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_containerHome, fragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();


                } else {
                    measurements_size = new ArrayList<String>();

                    measurements_date = new ArrayList<String>();

                    BodyPartMeasurements fragment = BodyPartMeasurements.newInstance(bodyPartID, measurements_size, measurements_date, true);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_containerHome, fragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                }

            }


            @Override
            public void onFailure(Call<BodyMeasuresResponse> call, Throwable t) {

            }
        });

    }

    private AdapterView.OnItemClickListener onClickListItem = (parent, view, position, id) -> {

        TextView textView = view.findViewById(R.id.LIST_BODYPART_ID);
        int bodyPartID = position;
        measurements_size.clear();
        measurements_date.clear();
        measurements_Id.clear();


        switch (bodyPartID) {
            case 0:

                bodypart_num = BodyPart.LEFTARM;
                apiCall(bodypart_num, bodyPartID);


                break;
            case 1:

                bodypart_num = BodyPart.RIGHTARM;
                apiCall(bodypart_num, bodyPartID);
                break;
            case 2:
                bodypart_num = BodyPart.PECTORAUX;
                apiCall(bodypart_num, bodyPartID);

                break;
            case 3:

                bodypart_num = BodyPart.WAIST;
                apiCall(bodypart_num, bodyPartID);

                break;
            case 4:

                bodypart_num = BodyPart.BEHIND;
                apiCall(bodypart_num, bodyPartID);
                break;
            case 5:

                bodypart_num = BodyPart.LEFTTHIGH;
                apiCall(bodypart_num, bodyPartID);

                break;
            case 6:

                bodypart_num = BodyPart.RIGHTTHIGH;
                apiCall(bodypart_num, bodyPartID);

                break;
            case 7:

                bodypart_num = BodyPart.LEFTCALVES;
                apiCall(bodypart_num, bodyPartID);

                break;
            case 8:

                bodypart_num = BodyPart.RIGHTCALVES;
                apiCall(bodypart_num, bodyPartID);
                break;
        }













    };


//    public void apiCalls(int user_id) {
//
//         String[] measurement = new String[1];
//        measurement[0]= "hey";
//
//
//
//
//        for (int i=0; i<bodypartNumber.length; i++){
//            Call<BodyMeasuresResponse> recent_Measurement = apiInterface.lastMeasureSearch(user_id, bodypartNumber[i]);
//
//                int b=i;
//
//            recent_Measurement.enqueue(new Callback<BodyMeasuresResponse>() {
//                @Override
//                public void onResponse(Call<BodyMeasuresResponse> call, Response<BodyMeasuresResponse> response) {
//
//
//
//                    if (response.body().getResponse().equals("Measurement Found")){
//
//                        recent_measureArray[b] = response.body().getMeasurementArray().get(0).getMeasurement();
//
//
//                    } else {
//                        recent_measureArray[b]="-";
//                    }
//
//
////            prefConFig.distplayToast();
//
//
//                }
//
//                @Override
//                public void onFailure(Call<BodyMeasuresResponse> call, Throwable t) {
//
//                }
//            });
//
//
//
//        }
////
//
//
////
//
//
//
//    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_measurement, container, false);

        //setting the title of the fragmentf
        Objects.requireNonNull(getActivity()).setTitle("Measurements");
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConFig = new PrefConFig(Objects.requireNonNull(getContext()));

        listView = view.findViewById(R.id.measurementListView);

        String[] measurement = new String[1];
        measurement[0] = "hey";




        BodyPartAdaptor bodyPartAdaptor = new BodyPartAdaptor(this.getActivity(), bodyPart, bodyPartIMV, recent_measureArray);
        listView.setAdapter(bodyPartAdaptor);
        listView.setOnItemClickListener(onClickListItem);
        bodyPartAdaptor.notifyDataSetChanged();
//        apiCalls(154);


        id = Integer.parseInt(prefConFig.readId());
        for (int i = 0; i < bodypartNumber.length; i++) {
            Call<BodyMeasuresResponse> recent_Measurement = apiInterface.lastMeasureSearch(id, bodypartNumber[i]);

            int b = i;

            recent_Measurement.enqueue(new Callback<BodyMeasuresResponse>() {
                @Override
                public void onResponse(Call<BodyMeasuresResponse> call, Response<BodyMeasuresResponse> response) {


                    if (response.body() != null && response.body().getResponse().equals("Measurement Found")) {

                        recent_measureArray[b] = response.body().getMeasurementArray().get(0).getMeasurement() + " cm";
                        bodyPartAdaptor.notifyDataSetChanged();

                    } else {
                        recent_measureArray[b] = "-";
                        bodyPartAdaptor.notifyDataSetChanged();
                    }


//            prefConFig.distplayToast();


                }

                @Override
                public void onFailure(Call<BodyMeasuresResponse> call, Throwable t) {

                }
            });


        }
//







        return view;
    }

}
