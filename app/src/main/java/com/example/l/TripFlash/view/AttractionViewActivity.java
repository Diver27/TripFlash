package com.example.l.TripFlash.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.l.TripFlash.R;
import com.example.l.TripFlash.model.AttractionModel;
import com.example.l.TripFlash.presenter.AttractionPresenter;
import com.example.l.TripFlash.presenter.AttractionPresenterInterface;

import java.util.ArrayList;
import java.util.List;

public class AttractionViewActivity extends AppCompatActivity implements AttractionViewInterface {
    AttractionPresenterInterface attractionPresenter;
    RecyclerView attractionListView;
    String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_view);

        attractionPresenter=new AttractionPresenter(this,city);

        attractionListView=findViewById(R.id.attraction_recycler);
        attractionListView.setLayoutManager(new LinearLayoutManager(this));
        attractionListView.setAdapter(new AttractionAdapter(initDefaultAttractionList()));

        update();
    }

    private void update(){
        attractionPresenter.getAttractionList(this,null);
    }

    @Override
    public void showAttractionList(List<AttractionModel.AttractionSpot> attractionSpotList){
        attractionListView.setAdapter(new AttractionAdapter(attractionSpotList));
    }

    private class AttractionHolder extends RecyclerView.ViewHolder{
        private AttractionModel.AttractionSpot attractionSpot;
        private TextView nameView;
        private TextView locationView;
        private Button selectButton;

        private AttractionHolder(View itemView){
            super(itemView);
            nameView=findViewById(R.id.name);
            locationView=findViewById(R.id.location);
            selectButton=findViewById(R.id.select_attr_buton);
        }

        private void bindAttraction(AttractionModel.AttractionSpot attractionSpot){
            this.attractionSpot=attractionSpot;
            nameView.setText(attractionSpot.getName());
            locationView.setText(attractionSpot.getLocation());
            selectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attractionPresenter.selectAttraction(attractionSpot);
                }
            });
        }
    }

    private class AttractionAdapter extends RecyclerView.Adapter<AttractionHolder>{
        private List<AttractionModel.AttractionSpot> attractionSpotList;

        public AttractionAdapter(List<AttractionModel.AttractionSpot> attractionSpotList){
            this.attractionSpotList=attractionSpotList;
        }

        @Override
        public AttractionHolder onCreateViewHolder(ViewGroup viewGroup,int i){
            LayoutInflater layoutInflater=LayoutInflater.from(AttractionViewActivity.this);
            View view=layoutInflater.inflate(R.layout.attraction_item,viewGroup,false);
            return new AttractionHolder(view);
        }

        @Override
        public void onBindViewHolder(AttractionHolder attractionHolder,int i){
            AttractionModel.AttractionSpot attractionSpot=attractionSpotList.get(i);
            attractionHolder.bindAttraction(attractionSpot);
        }

        @Override
        public int getItemCount(){
            return attractionSpotList.size();
        }
    }

    private List<AttractionModel.AttractionSpot> initDefaultAttractionList(){
        List<AttractionModel.AttractionSpot> defaultList=new ArrayList<>();
        return defaultList;
    }
}
