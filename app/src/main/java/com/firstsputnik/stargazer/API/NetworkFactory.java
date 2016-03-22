package com.firstsputnik.stargazer.API;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.firstsputnik.stargazer.Model.Apod;
import com.firstsputnik.stargazer.Model.CurrentLocation;
import com.firstsputnik.stargazer.Model.ISSPasses;
import com.firstsputnik.stargazer.Model.ISSPassesResponse;
import com.firstsputnik.stargazer.Provider.ISSMeetSQLiteOpenHelper;
import com.firstsputnik.stargazer.Provider.meet.MeetColumns;
import com.firstsputnik.stargazer.Provider.meet.MeetContentValues;
import com.firstsputnik.stargazer.Provider.meet.MeetCursor;
import com.firstsputnik.stargazer.Provider.meet.MeetSelection;
import com.firstsputnik.stargazer.View.APODFragment;
import com.firstsputnik.stargazer.View.ISSNowFragment;
import com.firstsputnik.stargazer.View.MeetISSFragment;

import java.io.IOException;
import java.util.List;

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
    private static final String NASA_BASE_URL = "https://api.nasa.gov";
    private static final String OPEN_NOTIFY_BASE_URL = "http://api.open-notify.org";
    private static final String NASA_API_KEY = "sEUMYQTRAnS0eSmM4YFVcak2cipkTckjR1jssorx";

    public static NetworkFactory get() {
        if (sNetworkFactory == null) {
            sNetworkFactory = new NetworkFactory();
            return sNetworkFactory;
        }
        else return sNetworkFactory;
    }

    public void getApod(final APODFragment fragment) {
        Retrofit client = getRetrofitClient(NASA_BASE_URL);
        NetworkInterface service = client.create(NetworkInterface.class);
        Call<Apod> call = service.getApod(NASA_API_KEY);

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

    public void getCurrentCoords(final ISSNowFragment fragment) {
        Retrofit client = getRetrofitClient(OPEN_NOTIFY_BASE_URL);
        NetworkInterface service = client.create(NetworkInterface.class);
        Call<CurrentLocation> call = service.getCurrentLocation();

        call.enqueue(new Callback<CurrentLocation>() {
            @Override
            public void onResponse(Call<CurrentLocation> call, retrofit2.Response<CurrentLocation> response) {
                CurrentLocation cl = response.body();
                fragment.setMapLocation(cl.getIssPosition());
            }

            @Override
            public void onFailure(Call<CurrentLocation> call, Throwable t) {

            }
        });

    }

    public void getMeetTimes(Double lat, double lon, final MeetISSFragment fragment) {
        Retrofit client = getRetrofitClient(OPEN_NOTIFY_BASE_URL);
        NetworkInterface service = client.create(NetworkInterface.class);
        Call<ISSPasses> call = service.getPasses(lat, lon);
        call.enqueue(new Callback<ISSPasses>() {
            @Override
            public void onResponse(Call<ISSPasses> call, retrofit2.Response<ISSPasses> response) {
                ISSPasses passes = response.body();
                List<ISSPassesResponse> meets = passes.getResponse();
                Cursor c = fragment.getActivity().getContentResolver().query(MeetColumns.CONTENT_URI,null,null,null,null);
                if (c != null && c.getCount() > 0) {
                    MeetSelection ms = new MeetSelection();
                    ms.delete(fragment.getActivity().getContentResolver());
                }
                for (ISSPassesResponse meet: meets) {
                    MeetContentValues values = new MeetContentValues();
                    values.putDatetime(meet.getRisetime());
                    fragment.getActivity().getContentResolver().insert(MeetColumns.CONTENT_URI, values.values());

                }
                fragment.populateMeetTimesView();
            }

            @Override
            public void onFailure(Call<ISSPasses> call, Throwable t) {

            }
        });
    }

    @NonNull
    private Retrofit getRetrofitClient(String baseUrl) {
            OkHttpClient okClient = new OkHttpClient
                    .Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            }).build();

            sRetrofitClient = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return sRetrofitClient;
        }


}
