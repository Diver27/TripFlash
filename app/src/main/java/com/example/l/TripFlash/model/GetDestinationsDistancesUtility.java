package com.example.l.TripFlash.model;

import android.content.Context;

import com.android.volley.toolbox.JsonRequest;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.network.JSONParser;
import com.example.l.TripFlash.network.VolleyCallback;
import com.example.l.TripFlash.network.VolleyRequest;

import org.json.JSONObject;

public class GetDestinationsDistancesUtility {
    public void getDistance(RoutineModel.DestSpot origin, RoutineModel.DestSpot destination,Context context,DistanceCallBack callBack,int i,int j){
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

    public static interface DistanceCallBack{
        void onGetDistanceSuccess(int i, int j, Long distance);
        void onGetDistanceFailure(Context context);
    }
}
