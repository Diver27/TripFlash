package com.example.l.TripFlash.model;

import android.content.Context;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.network.JSONParser;
import com.example.l.TripFlash.network.VolleyCallback;
import com.example.l.TripFlash.network.VolleyRequest;

import org.json.JSONObject;

import java.util.List;

public class RoutineAutoPlanUtility {
    public void getDistance(RoutineModel.DestSpot origin, RoutineModel.DestSpot destination, Context context, AutoPlanUtilityCallBack callBack, int i, int j){
        VolleyRequest.getJSONObject(
                JsonRequest.Method.GET,
                context.getString(R.string.find_path_distance_url) + "key=" + context.getString(R.string.Autonavi_api_key) + "&origin="+origin.getLocation() + "&destination="+destination.getLocation(),
                null, context, new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, Context context) {
                        callBack.onGetDistanceSuccess(i,j,JSONParser.parseJsonToDistance(jsonObject));
                    }

                    @Override
                    public void onFailure() {
                        callBack.onGetDistanceFailure(context);
                    }
                }
        );
    }

    public void getNearbyAttraction(final AutoPlanUtilityCallBack callBack, Context context, String location, String types,int index,String timeSlot){
        VolleyRequest.getJSONObject(
                JsonObjectRequest.Method.GET,
                context.getString(R.string.nearby_attraction_url) + "key=" + context.getString(R.string.Autonavi_api_key) + "&location=" + location + "&type" + types,
                null, context, new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, Context context) {
                        callBack.onGetNearAttractionSuccess(JSONParser.parseJsonToAttractionList(jsonObject),index,timeSlot);
                    }

                    @Override
                    public void onFailure() {

                    }
                }
        );
    }

    public static interface AutoPlanUtilityCallBack {
        void onGetDistanceSuccess(int i, int j, Long distance);
        void onGetNearAttractionSuccess(List<AttractionModel.AttractionSpot> nearAttractionList,int index,String timeSlot);
        void onGetDistanceFailure(Context context);
    }
}
