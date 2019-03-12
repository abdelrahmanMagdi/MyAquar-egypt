package com.aquar.myaquar_egypt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by magdy on 15/01/2019.
 */

public class RetrofitConnection
{

    private  static Retrofit retrofit ;
    private  static Gson gson ;

    public  static Retrofit  connectWith()
    {

        gson = new GsonBuilder().setLenient().create();

        if(retrofit == null) {
            retrofit = new Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
                    .baseUrl("http://aquar.me/myaquar_eg/api/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit  ;
    }
}
