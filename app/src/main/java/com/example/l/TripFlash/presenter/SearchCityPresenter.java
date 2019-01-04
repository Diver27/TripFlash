package com.example.l.TripFlash.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.l.TripFlash.model.WalletModel;
import com.example.l.TripFlash.network.JSONParser;
import com.example.l.TripFlash.view.SearchCityViewInterface;

import org.json.JSONObject;

import java.math.RoundingMode;

public class SearchCityPresenter implements SearchCityPresenterInterface{
    private SearchCityViewInterface searchCityViewInterface;


    public SearchCityPresenter(SearchCityViewInterface searchCityFragment){
        this.searchCityViewInterface = searchCityFragment;
    }

    @Override
    public void getCityInterest(String cityName){

    }
}
