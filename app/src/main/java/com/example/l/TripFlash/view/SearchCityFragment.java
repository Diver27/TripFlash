package com.example.l.TripFlash.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.l.TripFlash.GlobalData;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.presenter.SearchCityPresenter;
import com.example.l.TripFlash.presenter.SearchCityPresenterInterface;


public class SearchCityFragment extends Fragment implements SearchCityViewInterface {

    private SearchCityPresenterInterface searchCityPresenter;
    private GlobalData globalData;
    private TextView searchBar;
    private Button searchButton;
    private ImageView hotSpotView;
    private ImageView nearSpotView;

    public static SearchCityFragment newInstance() {
        SearchCityFragment f = new SearchCityFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_city_layout, container, false);
        globalData = (GlobalData) getActivity().getApplication();

        searchCityPresenter = new SearchCityPresenter(this);

        searchBar=rootView.findViewById(R.id.search_bar);
        searchButton=rootView.findViewById(R.id.search_button);
        hotSpotView=rootView.findViewById(R.id.hot_spot_image);
        nearSpotView=rootView.findViewById(R.id.near_spot_image);
        Glide.with(getActivity())
                .load("https://www.aljazeera.com/mritems/imagecache/mbdxxlarge/mritems/Images/2017/12/8/dc548b95cb49488191872963c3f67e0e_18.jpg")
                .into(hotSpotView);
        Glide.with(getActivity())
                .load("https://cdn.britannica.com/s:500x350/08/187508-004-DFD40BD6.jpg")
                .into(nearSpotView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCityPresenter.searchCity(getActivity(),searchBar.getText().toString());
            }
        });
        return rootView;
    }

    @Override
    public void onSearchCityResult(String cityName) {
        if (cityName!=null) {
            globalData.setCity(cityName);
            Intent intent = new Intent(getActivity(), RoutineViewActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "地区名称无效",
                    Toast.LENGTH_SHORT).show();
        }
    }
}