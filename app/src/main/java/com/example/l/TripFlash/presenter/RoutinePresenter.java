package com.example.l.TripFlash.presenter;

import android.content.Context;

import com.example.l.TripFlash.model.AttractionModel;
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
    public void LoadRoutineList(){
        routineModel.getDestList(this);
    }

    @Override
    public void addDestSpot(List<AttractionModel.AttractionSpot> selectedAttractionList) {
        for(int i=0;i<selectedAttractionList.size();i++){
            AttractionModel.AttractionSpot temp=selectedAttractionList.get(i);
            routineModel.addDest(new RoutineModel.DestSpot(temp.getId(),temp.getName(),temp.getLocation()));
        }
    }

    @Override
    public void deleteDestSpot(String id) {
        routineModel.deleteDest(id);
    }

    @Override
    public void autoPlan(Context context){
        routineModel.autoPlan(this,context);
    }

    @Override
    public void onLoadListSuccess(List<RoutineModel.DestSpot> routineList){
        routineView.showRoutineList(routineList);
    }

    @Override
    public void onFailure(){

    }
}
