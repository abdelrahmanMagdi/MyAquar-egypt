package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.InterFaces.ForRitrofit;
import com.aquar.myaquar_egypt.Model.AboutUs.AboutUsModelObject;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.RetrofitConnection;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TermsAndPolicies extends AppCompatActivity {
     private  TextView textview ;
    private AlertDialog dialog1;

    private ScrollView parentOfTermesAndPolicies ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_policies);
        textview = findViewById(R.id.text_of_terms);
        myUtils.setLocale(this);


        parentOfTermesAndPolicies = findViewById(R.id.parentOfTermesAndPolicies);


        dialog1 = new SpotsDialog.Builder().setContext(TermsAndPolicies.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();

        Get_Data_TermsAndPolicis();
    }


        private void Get_Data_TermsAndPolicis(){


            Retrofit retrofit = RetrofitConnection.connectWith() ;
            final ForRitrofit r = retrofit.create(ForRitrofit.class);


            Call<AboutUsModelObject> connection =  r.Get_Data_TermsAndPolicies();
            connection.enqueue(new Callback<AboutUsModelObject>() {
                @Override
                public void onResponse(Call<AboutUsModelObject>call, Response<AboutUsModelObject> response) {
                    dialog1.dismiss();

                    try {
                        textview.setText(response.body().getText());
                        parentOfTermesAndPolicies.setVisibility(View.VISIBLE);

                    }catch (Exception s){

                        Toast.makeText(TermsAndPolicies.this, "DataBase Error", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<AboutUsModelObject>call, Throwable t) {
                    Toast.makeText(TermsAndPolicies.this, "connection field", Toast.LENGTH_SHORT).show();
                    dialog1.dismiss();

                }
            });



        }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(TermsAndPolicies.this, MainActivity.class));
        finish();

    }

}
