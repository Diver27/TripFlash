package com.example.l.TripFlash.model;

import android.content.Context;

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

    public void deleteDest(String id){
        for(int i=0;i<DestList.size();i++){
            if(DestList.get(i).getId().equals(id)){
                DestList.remove(i);
                return;
            }
        }
    }

    public void autoPlan(LoadDataCallBack callBack, Context context){

        callBack.onSuccess(DestList);
    }

    public interface LoadDataCallBack{
        void onSuccess(List<DestSpot> DestList);
        void onFailure();
    }

    public static class DestSpot {
        private String id;
        private String name;
        private String location;

        public DestSpot(String id, String name, String location){
            this.id=id;
            this.name=name;
            this.location=location;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
