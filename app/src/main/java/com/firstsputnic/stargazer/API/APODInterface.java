package com.firstsputnic.stargazer.API;

import com.firstsputnic.stargazer.Model.Apod;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ibalashov on 3/17/2016.
 */
public interface APODInterface {

    @GET("/planetary/apod")
    Call<Apod> getApod(@Query("api_key") String apiKey);
}
