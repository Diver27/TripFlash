package com.example.l.TripFlash.model;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.l.TripFlash.network.VolleyCallback;
import com.example.l.TripFlash.network.VolleyRequest;

import org.json.JSONObject;

import java.util.List;

public class AttractionModel {
    private String city;
    private String type;
    private List<AttractionSpot > selectedAttractionList;

    public  AttractionModel(String city){
        this.city=city;
    }

    public void setType(String type){
        this.type=type;
    }

    public void getAttractionList(final AttractionCallBack callBack, Context context){
        VolleyRequest.getJSONObject(
                JsonObjectRequest.Method.GET,
                "urltoget", null, context, new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, Context context) {
                        callBack.OnSuccess(jsonObject);
                    }

                    @Override
                    public void onFailure(){

                    }
                }
        );
    }

    public void selectAttraction(int id){

    }

    public class AttractionSpot{
        private int id;
        private String name;
        private String location;

        public AttractionSpot(int id,String name,String location){
            this.id=id;
            this.name=name;
            this.location=location;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLocation() {
            return location;
        }
    }

    public interface AttractionCallBack{
        void OnSuccess(JSONObject jsonObject);
        void OnFailure();
    }
}
