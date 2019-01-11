package com.example.l.TripFlash.presenter;

import com.example.l.TripFlash.model.GalleryModel;

import java.util.List;

public interface GalleryPresenterInterface {
    void getPlanList();
    void deletePlan(GalleryModel.Blueprint toDelete);
}
