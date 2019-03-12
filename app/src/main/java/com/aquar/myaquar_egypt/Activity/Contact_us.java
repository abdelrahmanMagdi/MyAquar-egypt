package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.InterFaces.ForRitrofit;
import com.aquar.myaquar_egypt.Model.AboutUs.AboutUsModelObject;
import com.aquar.myaquar_egypt.Model.ContactUsModel.ContactUsModelObject;
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

public class Contact_us extends AppCompatActivity {
    private TextView contct,location,mail,phone;
    private String instaUrl,faceUrl,youtubeUrl,twitterUrl;
    private ScrollView parent ;
    private AlertDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        myUtils.setLocale(this);

        contct=findViewById(R.id.contactus);
        location=findViewById(R.id.location);
        mail=findViewById(R.id.mail);
        phone=findViewById(R.id.phone);
        Get_Data_ContactUS();

        parent = findViewById(R.id.parentCountactUs);
        dialog1 = new SpotsDialog.Builder().setContext(Contact_us.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");
        dialog1.show();


    }

    private void Get_Data_ContactUS(){

        Retrofit retrofit = RetrofitConnection.connectWith() ;
        ForRitrofit r = retrofit.create(ForRitrofit.class);


        Call<ContactUsModelObject> connection =  r.Get_Data_ContactUS();
        connection.enqueue(new Callback<ContactUsModelObject>() {
            @Override
            public void onResponse(Call<ContactUsModelObject>call, Response<ContactUsModelObject> response) {
                dialog1.dismiss();
                parent.setVisibility(View.VISIBLE);
              try {



                contct.setText(  response.body().getText());
                location.setText(  response.body().getAddress());
                mail.setText( response.body().getMail());
                phone.setText( response.body().getPhone());


                instaUrl=response.body().getInstagram();
                faceUrl=response.body().getFacebook();
                youtubeUrl=response.body().getYoutube();
                twitterUrl=response.body().getTwitter();

              }catch (Exception d){
                  Toast.makeText(Contact_us.this, "DataBase Error", Toast.LENGTH_SHORT).show();

              }


            }

            @Override
            public void onFailure(Call<ContactUsModelObject>call, Throwable t) {
                Toast.makeText(Contact_us.this, "connection field", Toast.LENGTH_SHORT).show();
                dialog1.dismiss();

            }
        });




    }


    public void openTwitter(View view) {

        try {


        Intent intent = null;
        try {
            // get the Twitter app if possible
            Contact_us.this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
        }
        Contact_us.this.startActivity(intent);
    }catch (Exception o ){}
        Toast.makeText(this, twitterUrl+"", Toast.LENGTH_SHORT).show();
    }

    public void openfacebook(View view) {


        try {



        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(faceUrl));
            startActivity(intent);
        } catch(Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(faceUrl)));
        }


        }catch (Exception n ){
            Toast.makeText(this, faceUrl+"", Toast.LENGTH_SHORT).show();
        }
    }

    public void openinsta(View view) {
      try {


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(instaUrl));
        startActivity(intent);
      }catch (Exception o ){

          Toast.makeText(this, instaUrl+"", Toast.LENGTH_SHORT).show();
      }
    }

    public void openyoutube(View view) {

        try {


        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(youtubeUrl));
        startActivity(intent);
    }catch (Exception o ){
            Toast.makeText(this,youtubeUrl+ "", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Contact_us.this, MainActivity.class));
        finish();

    }

}
