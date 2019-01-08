package com.example.l.TripFlash.presenter;

import android.content.Context;


import com.example.l.TripFlash.view.SearchCityViewInterface;


public class SearchCityPresenter implements SearchCityPresenterInterface{
    private SearchCityViewInterface searchCityViewInterface;


    public SearchCityPresenter(SearchCityViewInterface searchCityFragment){
        this.searchCityViewInterface = searchCityFragment;
    }

    @Override
    public void getCityInterest(String cityName){

    }
}
