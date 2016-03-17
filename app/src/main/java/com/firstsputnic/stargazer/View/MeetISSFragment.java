package com.firstsputnic.stargazer.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firstsputnic.stargazer.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeetISSFragment extends Fragment {


    public MeetISSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meet_is, container, false);
    }

}
