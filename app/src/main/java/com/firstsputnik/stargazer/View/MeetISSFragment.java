package com.firstsputnik.stargazer.View;


import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firstsputnik.stargazer.API.NetworkFactory;
import com.firstsputnik.stargazer.Provider.meet.MeetColumns;
import com.firstsputnik.stargazer.Provider.meet.MeetCursor;
import com.firstsputnik.stargazer.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeetISSFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;
    private static final String PREF_LONGITUDE = "Longitude";
    private static final String PREF_LATITUDE = "Latitude";
    GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private ArrayList<Long> meetTimes = new ArrayList<>();
    @Bind(R.id.meet_times)
    ListView meetTimesView;
    @Bind(R.id.meet_adview)
    AdView mAdView;

    private boolean isOutdated;

    public MeetISSFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_meet_is, container, false);
        ButterKnife.bind(this, v);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        populateMeetTimesView();

        return v;
    }

    public void populateMeetTimesView() {
        Cursor c = getActivity().getContentResolver().query(MeetColumns.CONTENT_URI,null,null,null,null);
        if (c != null && c.getCount() > 0) {
            MeetCursor mc = new MeetCursor(c);
            meetTimes.clear();
            while (mc.moveToNext()) {
                if ((mc.getDatetime() * 1000) < System.currentTimeMillis()) {
                    isOutdated = true;
                    c.close();
                    return;
                }
                meetTimes.add(mc.getDatetime());

            }
            Long[] items = convertLongs(meetTimes);
            Date[] dates = new Date[items.length];
            for (int i =0; i < items.length; i++) {
                dates[i] = new Date(items[i] * 1000);
            }
            ArrayAdapter<Date> adapter = new ArrayAdapter<>(
                    getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    dates);
            meetTimesView.setAdapter(adapter);
            c.close();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        boolean afl = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        boolean acl = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if (afl && acl) {

            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                handleNewLocation(mLastLocation);
            }
            else {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
        }
        else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_ACCESS_FINE_LOCATION);
        }


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onConnected(null);
                }
                else {
                    Toast.makeText(getActivity(), "Unfortunately, this application can't work without access to location services", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), "Connection to location services failed, Please try again later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    private void handleNewLocation(Location location) {

        boolean isChangedLocation;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        double prefLongitude = preferences.getFloat(PREF_LONGITUDE, 200);
        double prefLatitude = preferences.getFloat(PREF_LATITUDE, 200);
        isChangedLocation = (((Math.abs(prefLatitude) - Math.abs(location.getLatitude())) > 0.5) ||
                ((Math.abs(prefLongitude) - Math.abs(location.getLongitude())) > 0.5));
        if (isOutdated || isChangedLocation) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat(PREF_LONGITUDE, (float) location.getLongitude());
            editor.putFloat(PREF_LATITUDE, (float) location.getLatitude());
            editor.apply();

            NetworkFactory.get().getMeetTimes(location.getLatitude(), location.getLongitude(), this);
        }
    }

    @OnItemClick(R.id.meet_times)
    void onItemClick(int position) {
        Bundle arguments = new Bundle();
        arguments.putLong("Date", meetTimes.get(position));
        DetailsDialogFragment fragment = new DetailsDialogFragment();
        fragment.setArguments(arguments);
        fragment.show(getActivity().getSupportFragmentManager(), "DetailsDialog");


    }

    public static Long[] convertLongs(List<Long> longs)
    {
        Long[] ret = new Long[longs.size()];
        Iterator<Long> iterator = longs.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next();
        }
        return ret;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
