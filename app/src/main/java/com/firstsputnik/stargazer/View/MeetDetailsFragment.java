package com.firstsputnik.stargazer.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firstsputnik.stargazer.R;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetDetailsFragment extends Fragment {

    @Bind(R.id.details_date)
    TextView detailsDateView;

    public MeetDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_meet_details, container, false);
        ButterKnife.bind(this, v);
        Bundle arguments = this.getArguments();
        Long dateLong = arguments.getLong("Date");
        Date date = new Date(dateLong * 1000);
        detailsDateView.setText(date.toString());

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
