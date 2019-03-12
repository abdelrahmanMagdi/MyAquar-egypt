package com.aquar.myaquar_egypt.InterFaces;

import com.aquar.myaquar_egypt.KeyAndValueClass.KeyAndValueIfUserIsLogIn;
import com.aquar.myaquar_egypt.KeyAndValueClass.KeyAndValueOfProjects;
import com.aquar.myaquar_egypt.KeyAndValueClass.KeyAndValueOfSearchResult;
import com.aquar.myaquar_egypt.Model.AboutUs.AboutUsModelObject;
import com.aquar.myaquar_egypt.Model.ContactUsModel.ContactUsModelObject;
import com.aquar.myaquar_egypt.Model.EventandNewsDetailsModel.Model_array_of_Eventandnews;
import com.aquar.myaquar_egypt.Model.HomeApi.ModelArray;
import com.aquar.myaquar_egypt.Model.ModelOfNewsAndEvent.ModelArrayOfEventAndNews;
import com.aquar.myaquar_egypt.Model.ModelsOfProjectDetails.ArrayModelOfProjectsDetails;
import com.aquar.myaquar_egypt.Model.Search.SearchModelObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ForRitrofit {


    @GET("allprojects")
    Call<ModelArray> GetHome_Data();

    @GET("about_us")
    Call<AboutUsModelObject> Get_Data_About_Us();

    @GET("contact_us")
    Call<ContactUsModelObject>Get_Data_ContactUS();


    @GET("events_news")
    Call<ModelArrayOfEventAndNews>Get_Data_EventAndNews();


    @GET("terms_policies")
    Call<AboutUsModelObject>Get_Data_TermsAndPolicies();





    @GET("search")
    Call<SearchModelObject> Get_Data_Of_Filter();

    @GET("eventsnewsdetails")
    Call<Model_array_of_Eventandnews> Get_Data_EventsAndNewDetails(@Query(value="id", encoded=true) String value);


    @POST("category_projects")
    Call<ModelArray>GetCategoryData(@Body KeyAndValueOfProjects values);

    @POST("search_result")
    Call<ModelArray>GetSearchResult(@Body KeyAndValueOfSearchResult values);


    @POST("single_project")
    Call<ArrayModelOfProjectsDetails>Get_Data_Of_PrijectDetailsBeforeLogin(@Body KeyAndValueOfProjects values);

    @POST("single_project")
    Call<ArrayModelOfProjectsDetails>Get_Data_Of_PrijectDetailsAfterLogin(@Body KeyAndValueIfUserIsLogIn values);













}
