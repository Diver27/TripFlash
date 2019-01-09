package com.example.l.TripFlash.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.l.TripFlash.GlobalData;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.model.AttractionModel;
import com.example.l.TripFlash.presenter.AttractionPresenter;
import com.example.l.TripFlash.presenter.AttractionPresenterInterface;

import java.util.ArrayList;
import java.util.List;

public class AttractionViewActivity extends AppCompatActivity implements AttractionViewInterface {
    AttractionPresenterInterface attractionPresenter;
    RecyclerView attractionListView;
    GlobalData globalData;
    String type;
    Button foodButton;
    Button entertainButton;
    Button tourismButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_view);
        globalData = (GlobalData) getApplication();
        type=getString(R.string.food_type_code);

        attractionPresenter = new AttractionPresenter(this, globalData);

        attractionListView = findViewById(R.id.attraction_recycler);
        attractionListView.setLayoutManager(new LinearLayoutManager(this));
        attractionListView.setAdapter(new AttractionAdapter(initDefaultAttractionList()));

        foodButton=findViewById(R.id.food_type_button);
        entertainButton=findViewById(R.id.entertainment_type_button);
        tourismButton=findViewById(R.id.tourism_type_button);

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=getString(R.string.food_type_code);
                update();
            }
        });

        entertainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=getString(R.string.entertain_type_code);
                update();
            }
        });

        tourismButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=getString(R.string.tourism_type_code);
                update();
            }
        });

        update();
    }

    private void update() {
        attractionPresenter.getAttractionList(this, type);
    }

    @Override
    public void showAttractionList(List<AttractionModel.AttractionSpot> attractionSpotList) {
        attractionListView.setAdapter(new AttractionAdapter(attractionSpotList));
    }


    private class AttractionHolder extends RecyclerView.ViewHolder {
        private AttractionModel.AttractionSpot attractionSpot;
        private TextView nameView;
        private TextView AddressView;
        private Button selectButton;
        private ImageView photoView;

        private AttractionHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            AddressView = itemView.findViewById(R.id.address);
            selectButton = itemView.findViewById(R.id.select_attr_button);
            photoView=itemView.findViewById(R.id.attraction_photo);
        }

        private void bindAttraction(AttractionModel.AttractionSpot _attractionSpot) {
            this.attractionSpot = _attractionSpot;
            nameView.setText(attractionSpot.getName());
            AddressView.setText(attractionSpot.getAddress());
            //Log.i("imageUrl",attractionSpot.getPictureUrl());
            Glide.with(AttractionViewActivity.this)
                    .load(attractionSpot.getPictureUrl())
                    .into(photoView);
            selectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attractionPresenter.selectAttraction(attractionSpot);
                    selectButton.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    private class AttractionAdapter extends RecyclerView.Adapter<AttractionHolder> {
        private List<AttractionModel.AttractionSpot> attractionSpotList;

        public AttractionAdapter(List<AttractionModel.AttractionSpot> attractionSpotList) {
            this.attractionSpotList = attractionSpotList;
        }

        @Override
        public AttractionHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(AttractionViewActivity.this);
            View view = layoutInflater.inflate(R.layout.attraction_item, viewGroup, false);
            return new AttractionHolder(view);
        }

        @Override
        public void onBindViewHolder(AttractionHolder attractionHolder, int i) {
            AttractionModel.AttractionSpot attractionSpot = attractionSpotList.get(i);
            attractionHolder.bindAttraction(attractionSpot);
        }

        @Override
        public int getItemCount() {
            return attractionSpotList.size();
        }
    }

    private List<AttractionModel.AttractionSpot> initDefaultAttractionList() {
        List<AttractionModel.AttractionSpot> defaultList = new ArrayList<>();
        defaultList.add(new AttractionModel.AttractionSpot("B","肯德基","曹安公路4799","135，268",2,"快餐连锁","\n" +
                "http://store.is.autonavi.com/showpic/4639f862efb952bc55bc64442b32be03"));
        return defaultList;
    }
}
