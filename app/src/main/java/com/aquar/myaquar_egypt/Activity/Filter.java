package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.InterFaces.ForRitrofit;
import com.aquar.myaquar_egypt.Model.ContactUsModel.ContactUsModelObject;
import com.aquar.myaquar_egypt.Model.Search.SearchModelObject;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.RetrofitConnection;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.widget.ArrayAdapter;


import org.json.JSONObject;



import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Filter extends AppCompatActivity {
    public RadioGroup rg1, rg2, rg3;
    public RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    Spinner maxarea,minarea,minbed,maxbed,location,minbath,maxbath,minprice,maxprice;

   public static String itemMinArea  ,  itemMaxArea  , itemMinBedroom  , itemMaxBedroom ,  itemMinBathroom  , itemMaxBathroom , itemMinPrice , itemMaxPrice;
     public static  int radioBtn ;
     public static String locationOfSpinner ;

     private RelativeLayout parentOfFilter ;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterString;
    private AlertDialog dialog1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        myUtils.setLocale(this);

        parentOfFilter =findViewById(R.id.parentOfFilter);

        dialog1 = new SpotsDialog.Builder().setContext(Filter.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();

        rg1 = findViewById(R.id.radiogroup1);
        rg3 = findViewById(R.id.radiogroup3);
        rg2 = findViewById(R.id.radiogroup2);
        rb1 = findViewById(R.id.one);
        rb2 = findViewById(R.id.two);
        rb3 = findViewById(R.id.three);
        rb4 = findViewById(R.id.four);
        rb5 = findViewById(R.id.five);

        maxarea =findViewById(R.id.maxAreaSpinner);

        minarea=findViewById(R.id.minAreaSpinner);
        minbed=findViewById(R.id.minBedroomsSpinner);
        maxbed=findViewById(R.id.maxBedroomSpinner);
        location=findViewById(R.id.locationSpinner);
        minbath=findViewById(R.id.minbathroomsSpinner);
        maxbath=findViewById(R.id.maxBathroomsSpinner);
        minprice=findViewById(R.id.minPriceSpinner);
        maxprice=findViewById(R.id.maxPriceSpinner);




        radioButton();
        Get_Data_Of_Filter();
        onClickOfSpinners();







    }

    private void Get_Data_Of_Filter() {

        Retrofit retrofit = RetrofitConnection.connectWith() ;
        ForRitrofit r = retrofit.create(ForRitrofit.class);


        Call<SearchModelObject> connection =  r.Get_Data_Of_Filter();
        connection.enqueue(new Callback<SearchModelObject>() {
            @Override
            public void onResponse(Call<SearchModelObject>call, Response<SearchModelObject> response) {
                dialog1.dismiss();
             try {




                 adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, response.body().getMax_area());
                 maxarea.setAdapter(adapter);


                 parentOfFilter.setVisibility(View.VISIBLE);


                 adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, response.body().getMin_area());
                 minarea.setAdapter(adapter);

                 adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, response.body().getMin_price());
                 minprice.setAdapter(adapter);

                 adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, response.body().getMax_price());
                 maxprice.setAdapter(adapter);


                 adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, response.body().getMin_badrooms());
                 minbed.setAdapter(adapter);


                 adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, response.body().getMax_badrooms());
                 maxbed.setAdapter(adapter);

                 adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, response.body().getMin_bathrooms());
                 minbath.setAdapter(adapter);

                 adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, response.body().getMax_bathrooms());
                 maxbath.setAdapter(adapter);

                 adapterString = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_simple_text, response.body().getLocations());
                 location.setAdapter(adapterString);





             }catch (Exception f){
                 Toast.makeText(Filter.this, "DataBase Error", Toast.LENGTH_SHORT).show();
             }

            }

            @Override
            public void onFailure(Call<SearchModelObject>call, Throwable t) {
                dialog1.dismiss();
                Toast.makeText(Filter.this, "connection field", Toast.LENGTH_SHORT).show();


            }
        });

    }




    private void onClickOfSpinners (){

        minbed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMinBedroom   =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        maxbed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMaxBedroom   =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        minbath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMinBathroom   =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        maxbath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMaxBathroom =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        maxarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMaxArea   =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        minarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMinArea   =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        minprice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMinPrice   =  parent.getItemAtPosition(position).toString();;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        maxprice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMaxPrice   =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                locationOfSpinner   =  parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void radioButton(){

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioBtn =  myUtils.Residential ;

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb3.isChecked() || rb5.isChecked()) {
                    rb3.setChecked(false);
                    rb5.setChecked(false);
                }
                rb1.setChecked(true);


            }
        });

        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                radioBtn =  myUtils.LunchSoon ;


                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();


                if (rb3.isChecked() || rb1.isChecked()) {
                    rb3.setChecked(false);
                    rb1.setChecked(false);
                }

                rb5.setChecked(true);


            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioBtn =  myUtils.Medical ;

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb1.isChecked() || rb5.isChecked()) {
                    rb1.setChecked(false);
                    rb5.setChecked(false);
                }
                rb3.setChecked(true);


            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioBtn =  myUtils.Commercial;




                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();

                if (rb4.isChecked() || rb5.isChecked()) {
                    rb4.setChecked(false);
                    rb5.setChecked(false);
                }
                rb2.setChecked(true);


            }
        });

        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioBtn =  myUtils.HolidayHome ;

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb2.isChecked() || rb5.isChecked()) {
                    rb2.setChecked(false);
                    rb5.setChecked(false);
                }
                rb4.setChecked(true);

            }
        });
    }

    public void search(View view) {
       startActivity(new Intent(this,SearchResult.class));
       finish();


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Filter.this, MainActivity.class));
        finish();

    }

}