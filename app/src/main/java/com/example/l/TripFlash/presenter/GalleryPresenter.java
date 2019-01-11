package com.example.l.TripFlash.presenter;

import com.example.l.TripFlash.model.GalleryModel;
import com.example.l.TripFlash.model.RoutineModel;
import com.example.l.TripFlash.view.GalleryViewInterface;

import java.util.List;

public class GalleryPresenter implements GalleryPresenterInterface {
    GalleryModel galleryModel;
    GalleryViewInterface galleryView;

    public GalleryPresenter(GalleryViewInterface galleryView){
        galleryModel=new GalleryModel();
        this.galleryView=galleryView;
    }

    @Override
    public void getPlanList(){
        galleryView.showPlanList(galleryModel.getPlanList());
    }

    @Override
    public void deletePlan(GalleryModel.Blueprint toDelete){
        galleryModel.deletePlan(toDelete);
    }

    @Override
    public void addPlan(String cityName, String createDate, List<RoutineModel.DestSpot> destList){
        galleryModel.addPlan(cityName,createDate,destList);
    }
}
