package com.firstsputnik.stargazer.View;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ISSNowFragment extends Fragment {

    @Bind(R.id.iss_video)
    WebView videoView;
    @Bind(R.id.iss_map)
    ImageView mapView;

    ISSNowFragment fragment;
    final Handler h = new Handler();
    Runnable r;
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
        fragment = this;
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

    public void setMapLocation(IssPosition issPosition) {
        Uri uri = Uri.parse("http://maps.googleapis.com/maps/api/staticmap?markers=color:blue%7Clabel:S%7C" +
                issPosition.getLatitude() + "," + issPosition.getLongitude() + "&scale=2&zoom=1&size=400x300");
        Context context = mapView.getContext();
        Picasso.with(context).load(uri).into(mapView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        h.removeCallbacks(r);
    }
}

