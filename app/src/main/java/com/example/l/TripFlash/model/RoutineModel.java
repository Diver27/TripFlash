package com.example.l.TripFlash.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RoutineModel implements AutoPlanCallBackAssistant.DistanceCallBack {
    private List<DestSpot> destList;
    private Long[][] distanceList;
    private AutoPlanCallBackAssistant autoPlanCallBackAssistant;
    private int distanceUpdateCounter=0;
    public RoutineModel(){
        destList =DestListInitializer();
        autoPlanCallBackAssistant =new AutoPlanCallBackAssistant();
    }

    private List<DestSpot> DestListInitializer(){
        List<DestSpot> list=new ArrayList<>();
        return list;
    }

    public void getDestList(LoadDataCallBack callBack){
        callBack.onLoadListSuccess(destList);
    }

    public void addDest(DestSpot destSpot){
        destList.add(destSpot);
    }

    public void deleteDest(String id){
        for(int i = 0; i< destList.size(); i++){
            if(destList.get(i).getId().equals(id)){
                destList.remove(i);
                return;
            }
        }
    }

    public void autoPlan(LoadDataCallBack callBack, Context context){
        distanceList=new Long[destList.size()][destList.size()];

        for(int i=0;i<distanceList.length;i++){
            for(int j=0;j<distanceList[i].length;j++){
                distanceList[i][j]=Long.MAX_VALUE;
            }
        }
        for(int i=0;i<destList.size()-1;i++) {
            for(int j=i+1;j<destList.size();j++){
                autoPlanCallBackAssistant.getDistance(destList.get(i),destList.get(j),context,this,i,j);
            }
        }
    }

    private void sortList(){
        for(int i=0;i<destList.size()-1;i++) {
            Long minDistance=Long.MAX_VALUE;
            int index=-1;
            for (int j = i+1; j < destList.size(); j++) {
                if (distanceList[i][j] < minDistance) {
                    index = j;
                    minDistance = distanceList[i][j];
                }
            }
            if(index==-1){
                continue;
            }
            DestSpot tempSpot = destList.get(i+1);
            destList.set(i+1, destList.get(index));
            destList.set(index, tempSpot);
            Long tempNum=distanceList[i][i+1];
            distanceList[i][i+1]=minDistance;
            distanceList[i][index]=tempNum;
        }
        //callBack.onLoadListSuccess(destList);
    }

    private void setTimeSlot(){
        int timeSlot;
        if(destList.get(0).getType().startsWith("餐饮")){
            timeSlot=1;
        }else{
            timeSlot=0;
        }
        for(int i=0;i<destList.size();i++){
            String time;
            if(timeSlot%4==0){
                time="上午";
            }else if(timeSlot%4==1){
                time="中午";
            }else if(timeSlot%4==2){
                time="下午";
            }else{
                time="夜晚";
            }
            destList.get(i).setTime(time);
            timeSlot++;
        }
    }

    public interface LoadDataCallBack{
        void onLoadListSuccess(List<DestSpot> DestList);
        void onFailure();
    }

    public static class DestSpot {
        private String id;
        private String name;
        private String location;
        private String type;
        private String time;

        public DestSpot(String id, String name, String location,String type){
            this.id=id;
            this.name=name;
            this.location=location;
            this.type=type;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    @Override
    public void onSuccess(int i,int j,Long distance){
        distanceList[i][j]=distance;
        distanceList[j][i]=distance;
        distanceUpdateCounter+=2;
        if(distanceUpdateCounter==destList.size()*destList.size()-destList.size()){
            sortList();
            distanceUpdateCounter=0;
        }
    }

    @Override
    public void onFailure(){}
}
