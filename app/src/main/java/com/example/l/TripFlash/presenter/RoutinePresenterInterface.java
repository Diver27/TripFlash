package com.example.l.TripFlash.presenter;

import android.content.Context;

import com.example.l.TripFlash.model.AttractionModel;

import java.util.List;

public interface RoutinePresenterInterface {
    void LoadRoutineList(Context context);
    void addDestSpot(List<AttractionModel.AttractionSpot> selectedAttractionList);
    void deleteDestSpot(int id);
}
