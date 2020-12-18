package com.oo115.myapplication.Workout_feature;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.oo115.myapplication.PrefConFig;
import com.oo115.myapplication.R;
import com.oo115.myapplication.retrofitAPI.ApiClient;
import com.oo115.myapplication.retrofitAPI.ApiInterface;
import com.oo115.myapplication.retrofitAPI.ResponseDB;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExerciseLogListView extends BaseExpandableListAdapter {
    private ArrayList<String> workout_date = new ArrayList<String>();
    private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<String>> workout_id = new ArrayList<ArrayList<String>>();
    private Context context;


    private PrefConFig prefConFig;
    public static ApiInterface apiInterface;

    ExerciseLogListView(Context context, ArrayList<String> workout_date, ArrayList<ArrayList<String>> table, ArrayList<ArrayList<String>> workout_id) {

        this.context = context;
        this.workout_date = workout_date;
        this.table = table;
        this.workout_id = workout_id;
    }

    public interface EditLog_dialog {
        void editLog_dialog();


    }


    @Override
    public int getGroupCount() {

        return workout_date.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return table.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return workout_date.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return table.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        String group = (String) getGroup(groupPosition);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.exerciselog_list_parent, null);

        }

        TextView txt_parent = view.findViewById(R.id.txtParent);
        txt_parent.setText(group);


        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        String record = (String) getChild(groupPosition, childPosition);
        prefConFig = new PrefConFig(context);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.exerciselog_listchild, null);

        }

        TextView txt_record = view.findViewById(R.id.txtChild);
        ImageButton button = view.findViewById(R.id.delete_log);
        txt_record.setText(record);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, workout_id.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();

                int history_id = Integer.parseInt(workout_id.get(groupPosition).get(childPosition));
                SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
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
                        Call<ResponseDB> remove_from_history = apiInterface.removeFromHistory(Integer.parseInt(prefConFig.readId()), history_id);

                        remove_from_history.enqueue(new Callback<ResponseDB>() {
                            @Override
                            public void onResponse(Call<ResponseDB> call, Response<ResponseDB> response) {

                                if (response.body().getResponse().equals("removed from history")) {
                                    if (getChildrenCount(groupPosition) == 1) {
                                        table.remove(groupPosition);
//                                        workout_id.remove(childPosition);
                                        workout_date.remove(groupPosition);


                                    } else {

                                        table.remove(groupPosition);
//                                        workout_id.remove(childPosition);


                                    }
                                    notifyDataSetChanged();
                                    pDialog.dismissWithAnimation();
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
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void setNewItems(ArrayList<String> listDataHeader, ArrayList<ArrayList<String>> listChildData) {
        this.workout_date = listDataHeader;
        this.table = listChildData;
        notifyDataSetChanged();
    }


}
