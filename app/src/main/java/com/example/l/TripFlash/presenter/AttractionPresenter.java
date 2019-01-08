package com.example.l.TripFlash.presenter;

import android.content.Context;

import com.example.l.TripFlash.model.AttractionModel;
import com.example.l.TripFlash.network.JSONParser;
import com.example.l.TripFlash.view.AttractionViewInterface;

import org.json.JSONObject;

public class AttractionPresenter implements AttractionPresenterInterface,AttractionModel.AttractionCallBack {
    private String city;
    private AttractionModel attractionModel;
    private AttractionViewInterface attractionView;

    public AttractionPresenter(AttractionViewInterface attractionView,String city){
        this.city=city;
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

    }

    @Override
    public void OnSuccess(JSONObject jsonObject){
        attractionView.showAttractionList(JSONParser.parseJsonToAttractionList(jsonObject));
    }

    @Override
    public void OnFailure(){

    }
}
