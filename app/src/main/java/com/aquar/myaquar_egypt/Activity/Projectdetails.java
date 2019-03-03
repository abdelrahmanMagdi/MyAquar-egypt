package com.aquar.myaquar_egypt.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;


import com.aquar.myaquar_egypt.Fragments.fragment_home;
import com.aquar.myaquar_egypt.Model.ModelsOfProjectDetails.ArrayModelOfProjectsDetails;
import com.aquar.myaquar_egypt.Model.ModelsOfProjectDetails.ModelObjectsOfProjectDetails;

import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Projectdetails extends AppCompatActivity {
    private SliderLayout Product_Slider;
    private Button see_more_btn , like_btn,struct_btn , location_btn , call_btn , share_btn , go_youtube ,send_email_btn;
    private TextView description,devolepor,project_name , textprice , texttype , textMinBedrooms ,textmaxBedrooms ,textMinBathroom ,
                    textMaxBathrooms ,textMinArea , textMaxArea;
    private ScrollView sc ;



    List <String> urlimage = new ArrayList<>() ;

    ArrayList<ModelObjectsOfProjectDetails>list = new ArrayList<>();
    

    

    fragment_home getId = new fragment_home() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);


        see_more_btn =(Button)findViewById(R.id.see_more_btn);
        description=(TextView)findViewById(R.id.description);
        like_btn =(Button)findViewById(R.id.like);
        share_btn = findViewById(R.id.share);
        location_btn = findViewById(R.id.go_location);
        struct_btn=(Button)findViewById(R.id.structure);
        call_btn = findViewById(R.id.call);
        textprice = findViewById(R.id.price);
       send_email_btn=findViewById(R.id.send_email);

        project_name=findViewById(R.id.project_name);

        devolepor=findViewById(R.id.devolepor);

        texttype =findViewById(R.id.type);

        textMinBedrooms = findViewById(R.id.bedroom);
        textmaxBedrooms = findViewById(R.id.maxBedroom);

        textMinBathroom = findViewById(R.id.bathroom);
        textMaxBathrooms = findViewById(R.id.maxBathroom);


        textMinArea = findViewById(R.id.area);
        textMaxArea = findViewById(R.id.maxArea);

         // here get id of item from home for get its detalis
        fragment_home k = new fragment_home();
        int x =   k.id ;
         //----------------------------------------


        reciveDate(x);


       send_email_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendEmail();

           }
       });


        go_youtube = (Button)findViewById(R.id.go_youtube);

        go_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=F2xYkytHNxY&list=RDF2xYkytHNxY&start_radio=1"));
                startActivity(intent);

            }
        });
        struct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Projectdetails.this,struct_activity.class);
                startActivity(i);
            }
        });


        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/details?id=com.youssef.maggy.aqartest");
                share.setType("textDes/plain");
                startActivity(share);
            }
        });

        location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri = "http://maps.google.com/maps?q=loc:" + 30.145305 + "," + 31.630784 + " ("+"Shourok"+")";
                Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(map);
            }
        });
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contact = new Intent(Intent.ACTION_DIAL);
                contact.setData(Uri.parse("tel:01095488883"));
                startActivity(contact);

            }
        });

        see_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show_all_description();
              //  see_more_btn.setVisibility(View.INVISIBLE);
            }
        });

        Product_Slider = (SliderLayout)findViewById(R.id.Product_Slider);



    }






    private void show_all_description()
    {
        String x =see_more_btn.getText().toString();
        if (x.equals("See more")) {
            String s = (String) description.getText();
            s += getResources().getString(R.string.more_description);
            description.setText(s);
            see_more_btn.setText("See Less");
        }
        else
        {
            String s = getResources().getString(R.string.some_description);
            description.setText(s);
            see_more_btn.setText("See more");

        }

    }



    private void sendEmail() {

        String[] TO = {"someone@gmail.com"};
        String[] CC = {"xyz@gmail.com"};
        String recepientEmail = ""; // either set to destination email or leave empty
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        startActivity(emailIntent);


    }


    private void reciveDate(int idValue) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", idValue);

        } catch (JSONException e) {
            e.getStackTrace();
        }

        AndroidNetworking.post(ConstantsUrl.SingleProject)
                .addJSONObjectBody(object)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ArrayModelOfProjectsDetails array = gson.fromJson(response.toString(), ArrayModelOfProjectsDetails.class);

                        list = array.getProject();

                       //loop for image of slider
                        for (int i = 0 ;i<list.get(0).getSlider_images().size() ; i++) {
                            String x =   list.get(0).getSlider_images().get(i).getImage_url() ;
                             urlimage.add(x) ;
                        }

                        DataOfSlider(urlimage);

                       // for set all texts of details
                        setTdevoleporandproject(   list.get(0).getDescription()  ,list.get(0).getDeveloper(),list.get(0).getProject(),
                                list.get(0).getMin_price()
                                ,list.get(0).getType() ,list.get(0).getMin_rooms(), list.get(0).getMax_rooms() ,
                                list.get(0).getMin_bathsrooms()
                                ,list.get(0).getMax_bathsrooms() , list.get(0).getMin_area(), list.get(0).getMax_area()

                        );

                         // for like button
                        liked_projects(Boolean.valueOf(list.get(0).getFavorite()));

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });



    }

    private void DataOfSlider(List imageUrl) {
        HashMap<String, String> file_maps = new HashMap<String,String>();

        for(int i=0;i<urlimage.size();i++) {
        file_maps.put("Phase"+i+1, imageUrl.get(i).toString());

}
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image( file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            Product_Slider.addSlider(textSliderView);
        }
        Product_Slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        Product_Slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        Product_Slider.setCustomAnimation(new DescriptionAnimation());
        Product_Slider.setDuration(4000);
    }


    private void setTdevoleporandproject(String dec ,   String developer,String project , int price ,
                                         String type , int minrooms , int maxrooms
             ,int minBathrooms , int maxBathrooms  , int minArea , int maxArea  )
    {

        description.setText(dec);

        devolepor.setText(developer);
        project_name.setText(project);

        textprice.setText(price+"");

        texttype.setText(type);

        textMinBedrooms.setText(minrooms+"");
        textmaxBedrooms.setText(maxrooms+"");

        textMinBathroom.setText(minBathrooms+"");
        textMaxBathrooms.setText(maxBathrooms+"");

        textMinArea.setText(minArea+"");
        textMaxArea.setText(maxArea+"");

    }

    private void liked_projects (Boolean like)
    {

        if (!like)
            like_btn.setBackgroundResource(R.drawable.like);
        else
            like_btn.setBackgroundResource(R.drawable.liked);
    }

}


