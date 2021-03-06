package com.example.l.TripFlash.model;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.network.VolleyCallback;
import com.example.l.TripFlash.network.VolleyRequest;

import org.json.JSONObject;

public class AttractionModel {
    private String city;
    private String type;
    //private List<AttractionSpot > selectedAttractionList;

    public  AttractionModel(String city){
        this.city=city;
    }

    public void setType(String type){
        this.type=type;
    }

    public void getAttractionList(final AttractionCallBack callBack, Context context){
        VolleyRequest.getJSONObject(
                JsonObjectRequest.Method.GET,
                context.getString(R.string.search_poi_url)+"key="+context.getString(R.string.Autonavi_api_key)+"&city="+city+"&types="+type,
                null, context, new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, Context context) {
                        callBack.onAttractionCallSuccess(jsonObject);
                    }

                    @Override
                    public void onFailure(){

                    }
                }
        );
    }

    public static class AttractionSpot{
        private String id;
        private String name;
        private String address;
        private String location;
        private String type;
        private int rating;
        private String description;
        private String pictureUrl;

        public AttractionSpot(String id,String name,String address,String location,int rating,String description,String pictureUrl,String type){
            this.id=id;
            this.name=name;
            this.location=location;
            this.address=address;
            this.rating=rating;
            this.description=description;
            this.pictureUrl=pictureUrl;
            this.type=type;
        }

        public AttractionSpot(){}

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLocation() {
            return location;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public interface AttractionCallBack{
        void onAttractionCallSuccess(JSONObject jsonObject);
        void onAttractionCallFailure();
    }



}
