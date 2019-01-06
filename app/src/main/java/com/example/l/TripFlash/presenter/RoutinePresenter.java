package com.example.l.TripFlash.presenter;

import android.content.Context;

import com.example.l.TripFlash.model.RoutineInterface;
import com.example.l.TripFlash.model.RoutineModel;
import com.example.l.TripFlash.view.RoutineViewInterface;

import java.util.Map;

public class RoutinePresenter implements RoutinePresenterInterface {
    private RoutineViewInterface routineView;
    private RoutineInterface routineModel;
    public RoutinePresenter(RoutineViewInterface routineViewInterface){
        this.routineView=routineViewInterface;
        this.routineModel=new RoutineModel();
    }

    @Override
    public void LoadRoutineList(Context context){

    }

    @Override
    public void addRoutine(Context context, Map<String, Object> map) {

    }

    @Override
    public void deleteRoutine(Context context, String location) {

    }
}
