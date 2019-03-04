package com.aquar.myaquar_egypt.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Model.AboutUs.AboutUsModelObject;
import com.aquar.myaquar_egypt.Model.ContactUsModel.ContactUsModelObject;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class Contact_us extends AppCompatActivity {
    private TextView contct,location,mail,web,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        contct=findViewById(R.id.contactus);
        location=findViewById(R.id.location);
        mail=findViewById(R.id.mail);
        phone=findViewById(R.id.phone);
        Get_Data();



    }
    private void Get_Data() {


        AndroidNetworking.get(ConstantsUrl.contactUs)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        ContactUsModelObject array = gson.fromJson(response.toString(), ContactUsModelObject.class);

                        contct.setText(  array.getText());
                        location.setText(  array.getAddress());
                        mail.setText(  array.getMail());
                        phone.setText(  array.getPhone());

                    }

                    @Override
                    public void onError(ANError anError) {

                        Toast.makeText(Contact_us.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });
    }

}
