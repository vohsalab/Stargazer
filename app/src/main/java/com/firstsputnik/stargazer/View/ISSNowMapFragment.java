package com.firstsputnik.stargazer.View;


import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.firstsputnik.stargazer.API.NetworkFactory;
import com.firstsputnik.stargazer.Model.IssPosition;
import com.firstsputnik.stargazer.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ISSNowMapFragment extends Fragment implements OnMapReadyCallback{

    @Bind(R.id.iss_video2)
    WebView videoView;
    @Bind(R.id.iss_map2)
    MapView mapView;

    private GoogleMap map = null;

    ISSNowMapFragment fragment;
    final Handler h = new Handler();
    Runnable r;
    public ISSNowMapFragment() {
        // Required empty public constructor
    }


    @Override
    public final void onLowMemory()
    {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public final void onPause()
    {
        mapView.onPause();
        super.onPause();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_issnow, container, false);
        ButterKnife.bind(this, v);
        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.loadUrl("http://www.ustream.tv/embed/17074538");
        fragment = this;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        r = new Runnable() {
            @Override
            public void run() {
                NetworkFactory.get().getCurrentCoords(fragment);
                h.postDelayed(this, 10000);
            }
        };
        h.postDelayed(r, 1000);



        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }






    public void setMapLocation(IssPosition issPosition) {
                map.clear();
                MarkerOptions options = new MarkerOptions();
                LatLng iss = new LatLng(issPosition.getLatitude(), issPosition.getLongitude());
                options.position(iss);
                options.title("ISS");
                map.addMarker(options);
                map.moveCamera(CameraUpdateFactory.newLatLng(iss));

    }

        @Override
        public void onMapReady(GoogleMap map) {
            this.map = map;
            NetworkFactory.get().getCurrentCoords(this);
        }



    @Override
         public void onDestroy() {
                mapView.onDestroy();
                   super.onDestroy();
                    ButterKnife.unbind(this);
                  h.removeCallbacks(r);
        }
    }


