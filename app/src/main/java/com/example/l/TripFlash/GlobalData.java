package com.example.l.TripFlash;

import android.app.Application;

import com.example.l.TripFlash.model.AttractionModel;
import com.example.l.TripFlash.model.RoutineModel;
import com.example.l.TripFlash.model.dto.User;

import java.util.ArrayList;
import java.util.List;

public class GlobalData extends Application {
    private User primaryUser;
    private String city;
    private String preferedRestarautType;
    private String preferedEntertainmentType;
    private String preferedTourismType;
    private List<AttractionModel.AttractionSpot> selectedList=new ArrayList<>();

    public User getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(User primaryUser) {
        this.primaryUser = primaryUser;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<AttractionModel.AttractionSpot> getSelectedList() {
        return selectedList;
    }

    public void addSelectAttraction(AttractionModel.AttractionSpot attractionSpot){
        selectedList.add(attractionSpot);
    }

    public void selectedListInitializer(){
        selectedList=new ArrayList<>();
    }

    public String getPreferedRestarautType() {
        return preferedRestarautType;
    }

    public void setPreferedRestarautType(String preferedRestarautType) {
        this.preferedRestarautType = preferedRestarautType;
    }

    public String getPreferedEntertainmentType() {
        return preferedEntertainmentType;
    }

    public void setPreferedEntertainmentType(String preferedEntertainmentType) {
        this.preferedEntertainmentType = preferedEntertainmentType;
    }

    public String getPreferedTourismType() {
        return preferedTourismType;
    }

    public void setPreferedTourismType(String preferedTourismType) {
        this.preferedTourismType = preferedTourismType;
    }

    private List<RoutineModel.DestSpot> destSpotList;
    public boolean ifChanged;

    public List<RoutineModel.DestSpot> getDestSpotList() {
        return destSpotList;
    }

    public void setDestSpotList(List<RoutineModel.DestSpot> destSpotList) {
        this.destSpotList = destSpotList;
    }
}
