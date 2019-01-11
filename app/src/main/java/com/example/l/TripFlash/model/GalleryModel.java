package com.example.l.TripFlash.model;

import java.util.ArrayList;
import java.util.List;

public class GalleryModel {
    private List<Blueprint> planList;

    public GalleryModel(){
        planList=new ArrayList<>();
    }

    public void addPlan(String cityName, String createDate, List<RoutineModel.DestSpot> destList){
        planList.add(new Blueprint(cityName,createDate,destList));
    }

    public void deletePlan(Blueprint toDelete){
        planList.remove(toDelete);
    }

    public List<Blueprint> getPlanList() {
        return planList;
    }

    public class Blueprint {
        private String cityname;
        private String createDate;
        private List<RoutineModel.DestSpot> destList;

        public Blueprint(String cityname, String createDate, List<RoutineModel.DestSpot> destList) {
            this.cityname = cityname;
            this.createDate = createDate;
            this.destList = destList;
        }

        public String getCityname() {
            return cityname;
        }

        public String getCreateDate() {
            return createDate;
        }

        public List<RoutineModel.DestSpot> getDestList() {
            return destList;
        }
    }
}
