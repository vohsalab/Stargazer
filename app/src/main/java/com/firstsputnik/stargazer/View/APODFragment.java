package com.firstsputnik.stargazer.View;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firstsputnik.stargazer.API.NetworkFactory;
import com.firstsputnik.stargazer.Model.Apod;
import com.firstsputnik.stargazer.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class APODFragment extends Fragment {


    @Bind(R.id.apod_image)
    ImageView ApodImageView;
    @Bind(R.id.apod_desc)
    TextView ApodDescTextView;
    @Bind(R.id.apod_adview)
    AdView apodAdView;

    public APODFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_apod, container, false);
        ButterKnife.bind(this, v);
        AppBarLayout appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appBar);
        appBarLayout.setExpanded(true);
        AdRequest adRequest = new AdRequest.Builder().build();
        apodAdView.loadAd(adRequest);
        NetworkFactory.get().getApod(this);
        return v;
    }

    public void populateData(Apod apodObject) {
        Uri uri = Uri.parse(apodObject.getUrl());
        Context context = ApodImageView.getContext();
        Picasso.with(context).load(uri).into(ApodImageView);
        ApodDescTextView.setText(apodObject.getExplanation());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
