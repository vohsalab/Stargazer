package com.firstsputnic.stargazer.API;

import android.support.annotation.NonNull;

import com.firstsputnic.stargazer.Model.Apod;
import com.firstsputnic.stargazer.View.APODFragment;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ibalashov on 3/17/2016.
 */
public class NetworkFactory {

    private static NetworkFactory sNetworkFactory;
    private static Retrofit sRetrofitClient;
    private static final String BASE_URL = "https://api.nasa.gov";
    private static final String API_KEY = "sEUMYQTRAnS0eSmM4YFVcak2cipkTckjR1jssorx";

    public static NetworkFactory get() {
        if (sNetworkFactory == null) {
            sNetworkFactory = new NetworkFactory();
            return sNetworkFactory;
        }
        else return sNetworkFactory;
    }

    public void getApod(final APODFragment fragment) {
        Retrofit client = getRetrofitClient();
        APODInterface service = client.create(APODInterface.class);
        Call<Apod> call = service.getApod(API_KEY);

        call.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod> call, retrofit2.Response<Apod> response) {
                if (response.isSuccessful()) {
                    Apod apodObject = response.body();
                    fragment.populateData(apodObject);
                }
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });
    }


    @NonNull
    private Retrofit getRetrofitClient() {
        if (sRetrofitClient == null) {
            OkHttpClient okClient = new OkHttpClient
                    .Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            }).build();

            sRetrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return sRetrofitClient;
        }
        else return sRetrofitClient;
    }


}
