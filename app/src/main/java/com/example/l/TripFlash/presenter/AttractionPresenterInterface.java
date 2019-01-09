package com.example.l.TripFlash.presenter;

import android.content.Context;
import android.os.Parcelable;

import com.example.l.TripFlash.model.AttractionModel;

import java.util.List;

public interface AttractionPresenterInterface {
    void getAttractionList(Context context,String type);
    void selectAttraction(AttractionModel.AttractionSpot attractionSpot);
}
