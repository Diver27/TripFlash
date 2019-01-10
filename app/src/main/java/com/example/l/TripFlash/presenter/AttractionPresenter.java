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

    /*
    public List<AttractionModel.AttractionSpot> getDemoAttractionList(){
        List<AttractionModel.AttractionSpot> attractionSpotList=new ArrayList<>();
        attractionSpotList.add(new AttractionModel.AttractionSpot("A","嘉苑","曹安公路4800","123,456",1,"同济大学食堂","\n" +
                "http://store.is.autonavi.com/showpic/4639f862efb952bc55bc64442b32be03"));
        attractionSpotList.add(new AttractionModel.AttractionSpot("B","肯德基","曹安公路4799","135，268",2,"快餐连锁","\n" +
                "http://store.is.autonavi.com/showpic/4639f862efb952bc55bc64442b32be03"));
        attractionSpotList.add(new AttractionModel.AttractionSpot("C","星巴克","曹安公路4800","567,254",1,"咖啡厅","\n" +
                "http://store.is.autonavi.com/showpic/4639f862efb952bc55bc64442b32be03"));
        return attractionSpotList;
    }
    */
    @Override
    public void selectAttraction(AttractionModel.AttractionSpot attractionSpot){
        globalData.addSelectAttraction(attractionSpot);
    }

    @Override
    public void OnSuccess(JSONObject jsonObject){
        /*
        List<AttractionModel.AttractionSpot> temp=JSONParser.parseJsonToAttractionList(jsonObject);
        if(temp.isEmpty()){
            Log.i("AttractionPresenter","parse出列表为空");
        } else if(temp.get(1).getId()==null){
            Log.i("AttractionPresenter","parse出列表项内容为空");
        } else{
            Log.i("AttractionPresenter",temp.get(1).getName());
        }
        */
        attractionView.showAttractionList(JSONParser.parseJsonToAttractionList(jsonObject));
    }

    @Override
    public void OnFailure(){

    }
}
