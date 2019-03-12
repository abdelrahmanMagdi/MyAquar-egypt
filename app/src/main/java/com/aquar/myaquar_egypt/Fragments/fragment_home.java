package com.aquar.myaquar_egypt.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Activity.Projectdetails;
import com.aquar.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.myaquar_egypt.InterFaces.ForRitrofit;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.RetrofitConnection;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class fragment_home extends Fragment {
    int x = 0;
    int y = 0;


    public static int id;
    private AlertDialog dialog1;


    private int currnt = R.drawable.ic_favorite_normal_black_24dp;

    private RecyclerView mRecyclerView;
    private ImageView love_behind;
    private example_adapter_for_home_fragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final float buttonWidth = 300;


    private ArrayList<ModelObjects> list = new ArrayList<>();

    /* private ButtonsState buttonShowedState = ButtonsState.GONE;*/
    public fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerView_fragment_home);
        myUtils.setLocale(getActivity());

        dialog1 = new SpotsDialog.Builder().setContext(getContext()).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");

        GetHome_Data();


        //////////////////


        return v;

    }


    private void GetHome_Data() {
        dialog1.show();

        Retrofit retrofit = RetrofitConnection.connectWith();
        ForRitrofit r = retrofit.create(ForRitrofit.class);


        Call<ModelArray> connection = r.GetHome_Data();
        connection.enqueue(new Callback<ModelArray>() {
            @Override
            public void onResponse(Call<ModelArray> call, Response<ModelArray> response) {
                dialog1.dismiss();
                Log.e("kk", response.body() + "iiiii");

                list = response.body().getProjects();

                setRecyclerData(list);


                mAdapter.setOnItemClickListener(new example_adapter_for_home_fragment.OnItemClickListener() {

                    @Override
                    public void intent_to_detales(int pos, ImageView imageView) {
                        go_detales(pos, imageView);
                        id = list.get(pos).getProduct_id();
                    }

                    @Override
                    public void make_love(int pos, ImageView img) {

                    }
                });

            }

            @Override
            public void onFailure(Call<ModelArray> call, Throwable t) {
                Toast.makeText(getContext(), "Connection Field", Toast.LENGTH_SHORT).show();
                dialog1.dismiss();

            }
        });


    }

    private void setRecyclerData(ArrayList<ModelObjects> list) {


        mAdapter = new example_adapter_for_home_fragment(getActivity(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }


    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(getActivity(), Projectdetails.class);
        startActivity(intent);
    }


}