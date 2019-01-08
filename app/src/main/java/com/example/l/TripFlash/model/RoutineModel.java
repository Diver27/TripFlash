package com.example.l.TripFlash.model;

import java.util.ArrayList;
import java.util.List;

public class RoutineModel {
    private List<DestSpot> DestList;
    public RoutineModel(){
        DestList=DestListInitializer();
    }

    private List<DestSpot> DestListInitializer(){
        List<DestSpot> list=new ArrayList<>();
        return list;
    }

    public void getDestList(LoadDataCallBack callBack){
        callBack.onSuccess(DestList);
    }

    public void addDest(DestSpot destSpot){
        DestList.add(destSpot);
    }

    public void deleteDest(int id){
        //TODO
    }

    public interface LoadDataCallBack{
        void onSuccess(List<DestSpot> DestList);
        void onFailure();
    }
}
