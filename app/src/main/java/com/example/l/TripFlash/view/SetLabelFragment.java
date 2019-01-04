package com.example.l.TripFlash.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.l.TripFlash.GlobalData;
import com.example.l.TripFlash.R;


public class SetLabelFragment extends android.support.v4.app.Fragment implements SetLabelViewInterface {
    private GlobalData globalData;

    public static SetLabelFragment newInstance() {
        SetLabelFragment f = new SetLabelFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_set_label, container, false);
        globalData = (GlobalData) getActivity().getApplication();

        return rootView;
    }
}
