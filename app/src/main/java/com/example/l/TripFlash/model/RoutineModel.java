package com.example.l.TripFlash.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RoutineModel implements GetDestinationsDistancesUtility.DistanceCallBack {
    private List<DestSpot> destList;
    private Long[][] distanceList;
    //private GetDestinationsDistancesUtility getDestinationsDistancesUtility;
    private int distanceUpdateCounter=0;

    public RoutineModel(){
        destList =new ArrayList<>();
        //getDestinationsDistancesUtility =new GetDestinationsDistancesUtility();
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

    /*
    public void saveRoute(Context context,LoadDataCallBack callBack){
        VolleyRequest.getJSONObject(
                JsonRequest.Method.GET,
                context.getString(R.string.static_map_url) + "key=" + context.getString(R.string.Autonavi_api_key) + "&labels=" + getLocationString() + "&paths=" + getPathString(),
                null, context, new VolleyCallback() {
                    @Override
                    public void onGetDistanceSuccess(JSONObject jsonObject, Context context) {

                    }

                    @Override
                    public void onGetDistanceFailure() {

                    }
                }
        );
    }
    */

    public void getAllDistance(LoadDataCallBack callBack, Context context){
        distanceList=new Long[destList.size()][destList.size()];
        GetDestinationsDistancesUtility util=new GetDestinationsDistancesUtility();

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
        //callBack.onLoadListSuccess(destList);
    }

    private void autoDestination(){
        int tripDaysNum=0; //计划行程天数
        int restaurantNum=0;
        int recreationNum=0;
        for(int i=0;i<destList.size();i++){
            if(destList.get(i).getType().startsWith("餐饮")){
                restaurantNum++;
            }else{
                recreationNum++;
            }
        }
        if(restaurantNum>recreationNum){
            tripDaysNum=restaurantNum/2+restaurantNum%2;
        }else{
            tripDaysNum=recreationNum/2+recreationNum%2;
        }

        //记录时间表的空缺位置
        class emptySpot{
            public int i;
            public int j;
            public emptySpot(int i,int j){
                this.i=i;
                this.j=j;
            }
        }
        List<emptySpot> emptySpotList=new ArrayList<>();

        //分配时间表
        String[][] timeTable=new String[tripDaysNum][4];
        int destListCounter=0;
        for (int i = 0; i < tripDaysNum; i++) {
            for (int j = 0; j < 4; j++) {
                if (((j == 0 || j == 2) && destList.get(destListCounter).getType().startsWith("餐饮"))
                        || ((j == 1 || j == 3) && !destList.get(destListCounter).getType().startsWith("餐饮"))) {
                    emptySpotList.add(new emptySpot(i,j));
                    continue;
                }else{
                    timeTable[i][j]=destList.get(destListCounter).getId();
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
                    destList.get(destListCounter).setTimeSlot("第"+String.valueOf(i+1)+"日"+temp);
                    destListCounter++;
                }
            }
        }

        //
    }

    /*
    private void setTimeSlot(){
        int timeSlot;
        if(destList.get(0).getType().startsWith("餐饮")){
            timeSlot=1;
        }else{
            timeSlot=0;
        }
        for(int i=0;i<destList.size();i++){
            String timeSlot;
            if(timeSlot%4==0){
                timeSlot="上午";
            }else if(timeSlot%4==1){
                timeSlot="中午";
            }else if(timeSlot%4==2){
                timeSlot="下午";
            }else{
                timeSlot="夜晚";
            }
            destList.get(i).setTimeSlot(timeSlot);
            timeSlot++;
        }
    }
    */

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
        //void onGetStaticMapSuccess(JSONObject jsonObject);
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
}
