package com.example.l.TripFlash.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.l.TripFlash.GlobalData;
import com.example.l.TripFlash.model.AttractionModel;
import com.example.l.TripFlash.network.JSONParser;
import com.example.l.TripFlash.view.AttractionViewActivity;
import com.example.l.TripFlash.view.AttractionViewInterface;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AttractionPresenter implements AttractionPresenterInterface,AttractionModel.AttractionCallBack {
    private String city;
    private AttractionModel attractionModel;
    private AttractionViewInterface attractionView;
    GlobalData globalData;

    public AttractionPresenter(AttractionViewInterface attractionView,GlobalData globalData){
        this.globalData=globalData;
        this.city=globalData.getCity();
        this.attractionView=attractionView;
        attractionModel=new AttractionModel(this.city);
    }

    @Override
    public void getAttractionList(Context context,String type){
        attractionModel.setType(type);
        attractionModel.getAttractionList(this,context);
    }

    @Override
    public void selectAttraction(AttractionModel.AttractionSpot attractionSpot){
        globalData.addSelectAttraction(attractionSpot);
    }

    @Override
    public void onAttractionCallSuccess(JSONObject jsonObject){
        attractionView.showAttractionList(JSONParser.parseJsonToAttractionList(jsonObject));
    }

    @Override
    public void onAttractionCallFailure(){

    }
}
