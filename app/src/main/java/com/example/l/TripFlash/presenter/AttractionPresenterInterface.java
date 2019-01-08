package com.example.l.TripFlash.presenter;

import android.content.Context;

import com.example.l.TripFlash.model.AttractionModel;

public interface AttractionPresenterInterface {
    void getAttractionList(Context context,String type);
    void selectAttraction(AttractionModel.AttractionSpot attractionSpot);
}
