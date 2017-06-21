package com.getfreerecharge.instantnews.utilitiess;


import com.getfreerecharge.instantnews.pojos.AdsOfferPojo;
import com.getfreerecharge.instantnews.pojos.NewsPojo;
import com.getfreerecharge.instantnews.pojos.Sources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Amit Kumar Tiwar on 25/07/16.
 */
public interface RestInterface {

    @GET("sources")
    Call<Sources> getAllNews(@Query("category") String category);

    @GET("articles")
    Call<NewsPojo> getNews(@Query("source") String source, @Query("apiKey") String apiKey);

    @GET("sources")
    Call<Sources> getNewsSource(@Query("language") String language);

    @POST("totalOffer")
    Call<AdsOfferPojo> getMoreApps();
}
