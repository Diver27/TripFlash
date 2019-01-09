package com.example.l.TripFlash;

import android.app.Application;

import com.example.l.TripFlash.model.AttractionModel;
import com.example.l.TripFlash.model.dto.User;

import java.util.ArrayList;
import java.util.List;

public class GlobalData extends Application {
    private User primaryUser;
    private String city;
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
}
