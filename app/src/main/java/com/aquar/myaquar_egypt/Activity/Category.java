package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aquar.myaquar_egypt.Adapter.example_adapter_for_home_fragment;
import com.aquar.myaquar_egypt.Fragments.fragment_home;
import com.aquar.myaquar_egypt.InterFaces.ForRitrofit;
import com.aquar.myaquar_egypt.KeyAndValueClass.KeyAndValueOfProjects;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelObjects;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.RetrofitConnection;
import com.aquar.myaquar_egypt.Utils.myUtils;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Category extends AppCompatActivity {

    fragment_home  getid = new fragment_home();
    private RecyclerView mRecyclerView;
    private TextView textOfHeader ;
    private ImageView love_behind;
    private example_adapter_for_home_fragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelObjects>  list  = new ArrayList<>();
    private AlertDialog dialog1;
    private LinearLayout parentOfCategory ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categroy);
        myUtils.setLocale(this);

        mRecyclerView = findViewById(R.id.recyclerView_categry);
        textOfHeader = findViewById(R.id.textOfCategory);

        parentOfCategory = findViewById(R.id.parentOfCategory);


        dialog1 = new SpotsDialog.Builder().setContext(Category.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();



        //send id of category from nav to here
        MainActivity data = new MainActivity();
        GetCategoryData(data.idForCategoryOfNav);


        textOfHeader.setText(data.headerOfCategory);


    }
 private void GetCategoryData(String value){



     Retrofit retrofit = RetrofitConnection.connectWith() ;
     ForRitrofit r = retrofit.create(ForRitrofit.class);


     KeyAndValueOfProjects values = new KeyAndValueOfProjects(value);

     Call<ModelArray> connection =  r.GetCategoryData(values);
     connection.enqueue(new Callback<ModelArray>() {
         @Override
         public void onResponse(Call<ModelArray>call, Response<ModelArray> response) {
             parentOfCategory.setVisibility(View.VISIBLE);
             dialog1.dismiss();

             try {


               list = response.body().getProjects();
             setRecyclerData(list);



             mAdapter.setOnItemClickListener(new example_adapter_for_home_fragment.OnItemClickListener() {

                 @Override
                 public void intent_to_detales(int pos, ImageView imageView ) {
                     go_detales(pos, imageView);

                     getid.id = list.get(pos).getProduct_id();

                 }
                 @Override
                 public void make_love(int pos, ImageView img) {

                 }
             });
         }catch (Exception c){

                 Toast.makeText(Category.this, "No Result", Toast.LENGTH_SHORT).show();

             }

         }

         @Override
         public void onFailure(Call<ModelArray>call, Throwable t) {

             dialog1.dismiss();
             Toast.makeText(Category.this, "connection field", Toast.LENGTH_SHORT).show();

         }
     });
 }







    private void setRecyclerData(ArrayList<ModelObjects> list) {

    mAdapter = new example_adapter_for_home_fragment(Category.this, list);
    LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }
    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(Category.this, Projectdetails.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Category.this, MainActivity.class));
        finish();

    }

}
