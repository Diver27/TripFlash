package com.example.l.TripFlash.presenter;

import android.content.Context;


import com.example.l.TripFlash.model.SearchCityModel;
import com.example.l.TripFlash.network.JSONParser;
import com.example.l.TripFlash.view.SearchCityViewInterface;

import org.json.JSONObject;


public class SearchCityPresenter implements SearchCityPresenterInterface,SearchCityModel.ValidateCityCallBack{
    private SearchCityViewInterface searchCityViewInterface;
    private SearchCityModel searchCityModel;

    public SearchCityPresenter(SearchCityViewInterface searchCityFragment){
        this.searchCityViewInterface = searchCityFragment;
        searchCityModel=new SearchCityModel();
    }

    @Override
    public void searchCity(Context context, String cityName){
        searchCityModel.searchCity(this,context,cityName);
    }

    @Override
    public void onSuccess(JSONObject jsonObject){
        searchCityViewInterface.onSearchCityResult(JSONParser.parseJsonToCityName(jsonObject));
    }

    @Override
    public void onFailure(){

    }
}
