package com.example.l.TripFlash.presenter;

import android.content.Context;

import com.example.l.TripFlash.model.AttractionModel;
import com.example.l.TripFlash.model.RoutineModel;

import java.util.List;

public interface RoutinePresenterInterface {
    void LoadRoutineList();
    void addDestSpot(List<AttractionModel.AttractionSpot> selectedAttractionList);
    void deleteDestSpot(String id);
    void autoPlan(Context context);
    String exportRoute(Context context);
    void saveToGallery(List<RoutineModel.DestSpot> destSpotList);
}
