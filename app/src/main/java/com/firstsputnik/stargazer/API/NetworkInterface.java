package com.firstsputnik.stargazer.API;

import com.firstsputnik.stargazer.Model.Apod;
import com.firstsputnik.stargazer.Model.CurrentLocation;
import com.firstsputnik.stargazer.Model.ISSPasses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ibalashov on 3/17/2016.
 */
public interface NetworkInterface {

    @GET("/planetary/apod")
    Call<Apod> getApod(@Query("api_key") String apiKey);

    @GET("/iss-now")
    Call<CurrentLocation> getCurrentLocation();

    @GET("/iss-pass.json")
    Call<ISSPasses> getPasses(@Query("lat") double latitude, @Query("lon") double longitude);
}
