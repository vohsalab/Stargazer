package com.firstsputnic.stargazer.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.firstsputnic.stargazer.API.NetworkFactory;
import com.firstsputnic.stargazer.Model.IssPosition;
import com.firstsputnic.stargazer.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ISSNowFragment extends Fragment implements OnMapReadyCallback {

    @Bind(R.id.iss_video)
    WebView videoView;
    @Bind(R.id.iss_map)
    MapView mapView;

    GoogleMap map;

    public ISSNowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_ssnow, container, false);
        ButterKnife.bind(this, v);
        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.loadUrl("http://www.ustream.tv/embed/17074538");
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return v;
    }

    public void setMapLocation(IssPosition issPosition) {
        MarkerOptions options = new MarkerOptions();
        LatLng iss = new LatLng(issPosition.getLatitude(), issPosition.getLongitude());
        options.position(iss);
        map.addMarker(options);
        map.moveCamera(CameraUpdateFactory.newLatLng(iss));
    }
    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        NetworkFactory.get().getCurrentCoords(this);
    }
}

