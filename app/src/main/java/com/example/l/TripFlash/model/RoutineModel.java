package com.example.l.TripFlash.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.android.volley.toolbox.JsonRequest;
import com.bumptech.glide.Glide;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.network.VolleyCallback;
import com.example.l.TripFlash.network.VolleyRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RoutineModel implements RoutineAutoPlanUtility.AutoPlanUtilityCallBack {
    private List<DestSpot> destList;
    private Long[][] distanceList;
    private Context context;
    //private RoutineAutoPlanUtility getDestinationsDistancesUtility;
    private int distanceUpdateCounter=0;

    public RoutineModel(){
        destList =new ArrayList<>();
        //getDestinationsDistancesUtility =new RoutineAutoPlanUtility();
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


    public String exportRoute(Context context){
        String url=context.getString(R.string.static_map_url) + "key=" + context.getString(R.string.Autonavi_api_key) + "&labels=" + getLocationString() + "&paths=" + getPathString();
        return url;
    }


    public void getAllDistance(LoadDataCallBack callBack, Context context){
        this.context=context;
        distanceList=new Long[destList.size()][destList.size()];
        RoutineAutoPlanUtility util=new RoutineAutoPlanUtility();

        for(int i=0;i<distanceList.length;i++){
            for(int j=0;j<distanceList[i].length;j++){
                distanceList[i][j]=Long.MAX_VALUE;
            }
        }
        for(int i=0;i<destList.size()-1;i++) {
            for(int j=i+1;j<destList.size();j++){
                util.getDistance(destList.get(i),destList.get(j),context,this,i,j);
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
        autoDestination();
    }

    private void autoDestination(){

        //记录时间表的空缺位置
        class EmptySpot {
            public String timeSlot;
            public int previousSpot;
            public String type;

            public EmptySpot(String timeSlot, int preIndex,String type){
                this.timeSlot=timeSlot;
                this.previousSpot=preIndex;
                this.type=type;
            }
        }
        List<EmptySpot> emptySpotList=new ArrayList<>();

        //分配时间表
        int destListCounter = 0;
        for (int i = 0;destListCounter < destList.size() ; i++) {
            for (int j = 0; j < 4; j++) {
                String temp;
                switch (j) {
                    case 0:
                        temp = "上午";
                        break;
                    case 1:
                        temp = "中午";
                        break;
                    case 2:
                        temp = "下午";
                        break;
                    case 3:
                        temp = "晚间";
                        break;
                    default:
                        temp = "ERROR";
                }

                if (((j == 0 || j == 2) && destList.get(destListCounter).getType().startsWith("餐饮"))) {
                    /*
                    if(i==0&&j==0){
                        emptySpotList.add(new EmptySpot("第" + String.valueOf(i + 1) + "日" + temp, destListCounter + 1, "110000|080300"));
                    }else {
                    */
                        emptySpotList.add(new EmptySpot("第" + String.valueOf(i + 1) + "日" + temp, destListCounter - 1, "110000|080000"));
                        continue;

                } else if (((j == 1 || j == 3) && !destList.get(destListCounter).getType().startsWith("餐饮"))) {
                    emptySpotList.add(new EmptySpot("第" + String.valueOf(i + 1) + "日" + temp, destListCounter - 1, "050000"));
                    continue;
                } else {
                    destList.get(destListCounter).setTimeSlot("第" + String.valueOf(i + 1) + "日" + temp);
                    destListCounter++;
                    if(destListCounter >= destList.size()){
                        break;
                    }
                }
            }
        }

        RoutineAutoPlanUtility util=new RoutineAutoPlanUtility();
        for(int i=0;i<emptySpotList.size();i++){
            util.getNearbyAttraction(this,context,destList.get(emptySpotList.get(i).previousSpot).location,emptySpotList.get(i).type,emptySpotList.get(i).previousSpot+1,emptySpotList.get(i).timeSlot);
        }
    }


    private String getLocationString(){
        String completeLocation=destList.get(0).getName()+",2,0,16,0xFFFFFF,0x008000:"+destList.get(0).getLocation();
        for(int i=1;i<destList.size();i++){
            completeLocation=completeLocation+"|"+destList.get(i).getName()+",2,0,16,0xFFFFFF,0x008000:"+destList.get(i).getLocation();
        }
        return completeLocation;
    }

    private String getPathString(){
        String completePath="10,0x0000ff,1,,:"+destList.get(0).getLocation();
        for(int i=1;i<destList.size();i++){
            completePath=completePath+";"+destList.get(i).getLocation();
        }
        return completePath;
    }

    public interface LoadDataCallBack{
        void onLoadListSuccess(List<DestSpot> DestList);
        void onGetStaticMapSuccess(JSONObject jsonObject);
        void onFailure();
    }

    public static class DestSpot {
        private String id;
        private String name;
        private String location;
        private String type;
        private String timeSlot;

        public DestSpot(String id, String name, String location,String type){
            this.id=id;
            this.name=name;
            this.location=location;
            this.type=type;
            this.timeSlot="";
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

        public String getTimeSlot() {
            return timeSlot;
        }

        public void setTimeSlot(String timeSlot) {
            this.timeSlot = timeSlot;
        }
    }

    @Override
    public void onGetDistanceSuccess(int i, int j, Long distance){
        distanceList[i][j]=distance;
        distanceList[j][i]=distance;
        distanceUpdateCounter+=2;
        if(distanceUpdateCounter==destList.size()*destList.size()-destList.size()){
            sortList();
            distanceUpdateCounter=0;
        }
    }

    @Override
    public void onGetDistanceFailure(Context context){
        Toast.makeText(context,"网络请求失败，距离获取错误",Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onGetNearAttractionSuccess(List<AttractionModel.AttractionSpot> nearAttractionList,int index,String timeSlot){
        for(int i=0;i<nearAttractionList.size();i++){
            Boolean isIn=false;
            for(int j=0;j<destList.size();j++){
                if(destList.get(j).getId()==nearAttractionList.get(i).getId()){
                    isIn=true;
                }
            }
            if(isIn==false){
                AttractionModel.AttractionSpot temp=nearAttractionList.get(i);
                DestSpot newDest=new DestSpot(temp.getId(),temp.getName(),temp.getLocation(),temp.getType());
                newDest.setTimeSlot(timeSlot);
                destList.add(index,newDest);
                break;
            }
        }
    }
}
