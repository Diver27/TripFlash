package com.example.l.TripFlash.presenter;

import com.example.l.TripFlash.model.GalleryModel;
import com.example.l.TripFlash.model.RoutineModel;

import java.util.List;

public interface GalleryPresenterInterface {
    void getPlanList();
    void deletePlan(GalleryModel.Blueprint toDelete);
    void addPlan(String cityName, String createDate, List<RoutineModel.DestSpot> destList);
}
