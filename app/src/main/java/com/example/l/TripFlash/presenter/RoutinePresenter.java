package com.example.l.TripFlash.presenter;

import android.content.Context;

import com.example.l.TripFlash.model.AttractionModel;
import com.example.l.TripFlash.model.DestSpot;
import com.example.l.TripFlash.model.RoutineModel;
import com.example.l.TripFlash.view.RoutineViewInterface;

import java.util.List;

public class RoutinePresenter implements RoutinePresenterInterface,RoutineModel.LoadDataCallBack {
    private RoutineViewInterface routineView;
    private RoutineModel routineModel;
    public RoutinePresenter(RoutineViewInterface routineViewInterface){
        this.routineView=routineViewInterface;
        this.routineModel=new RoutineModel();
    }

    @Override
    public void LoadRoutineList(Context context){
        routineModel.getDestList(this);
    }

    @Override
    public void addDestSpot(List<AttractionModel.AttractionSpot> selectedAttractionList) {
        DestSpot destSpot=new DestSpot(1,null,null);
        //TODO AttractionSpot对象转化到DestSpot对象
        routineModel.addDest(destSpot);
    }

    @Override
    public void deleteDestSpot(int id) {
        //TODO 根据id删除某一个destSpot
    }

    @Override
    public void onSuccess(List<DestSpot> routineList){
        routineView.showRoutineList(routineList);
    }

    @Override
    public void onFailure(){

    }
}
