package com.example.l.TripFlash.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonRequest;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.network.VolleyCallback;
import com.example.l.TripFlash.network.VolleyRequest;

import org.json.JSONObject;

public class SearchCityModel {
    public void searchCity(final ValidateCityCallBack callBack, Context context, String city){
        VolleyRequest.getJSONObject(
                JsonRequest.Method.GET,
                context.getString(R.string.validate_city_url) + "key=" + context.getString(R.string.Autonavi_api_key) + "&keywords=" + city,
                null, context, new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject jsonObject, Context context) {
                        callBack.onSuccess(jsonObject);
                    }

                    @Override
                    public void onFailure() {
                        callBack.onFailure();
                    }
                }
        );
    }

    public interface ValidateCityCallBack{
        void onSuccess(JSONObject jsonObject);
        void onFailure();
    }
}
