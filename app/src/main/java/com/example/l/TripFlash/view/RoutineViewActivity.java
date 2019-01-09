package com.example.l.TripFlash.view;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.l.TripFlash.model.RoutineModel;
import com.example.l.TripFlash.presenter.RoutinePresenter;
import com.example.l.TripFlash.presenter.RoutinePresenterInterface;

import java.util.ArrayList;
import java.util.List;

public class RoutineViewActivity extends AppCompatActivity implements RoutineViewInterface {
    private GlobalData globalData;
    private RecyclerView routineListView;
    //private DestSpotAdapter destSpotAdapter;
    private RoutinePresenterInterface routinePresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView city;
    private Button addDestSpotButton;
    private ImageView cityImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_view);
        globalData = (GlobalData) getApplication();

        city=findViewById(R.id.city);
        city.setText(globalData.getCity());
        addDestSpotButton=findViewById(R.id.add_dest_spot);
        cityImageView=findViewById(R.id.city_image_view);

        addDestSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RoutineViewActivity.this,AttractionViewActivity.class);
                startActivity(intent);
            }
        });

        routinePresenter = new RoutinePresenter(this);
        swipeRefreshLayout = findViewById(R.id.routine_slide_refresh);

        routineListView = findViewById(R.id.routine_recycler);
        routineListView.setLayoutManager(new LinearLayoutManager(this));
        //destSpotAdapter=new DestSpotAdapter(initDefaultRoutineList());
        routineListView.setAdapter(new DestSpotAdapter(initDefaultRoutineList()));

        /**
         * Swipe to refresh
         */
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(() -> update());
        update();
    }

    private void update() {
        List<AttractionModel.AttractionSpot> list = globalData.getSelectedList();
        if (!list.isEmpty()) {
            routinePresenter.addDestSpot(list);
            globalData.selectedListInitializer();
        }
        routinePresenter.LoadRoutineList(this);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRoutineList(List<RoutineModel.DestSpot> routineList) {
        routineListView.setAdapter(new DestSpotAdapter(routineList));
    }

    /**
     * Recycler view for routine list
     */
    private class DestHolder extends RecyclerView.ViewHolder {
        private RoutineModel.DestSpot destSpot;
        private TextView nameView;
        private Button deleteButton;

        private DestHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            deleteButton = itemView.findViewById(R.id.delete_dest_buton);
        }

        private void bindDestSpot(RoutineModel.DestSpot dSpot) {
            destSpot = dSpot;
            nameView.setText(destSpot.getName());
            /**
             * 删除路径点按钮监听
             */
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    routinePresenter.deleteDestSpot(destSpot.getId());
                    update();
                }
            });
        }
    }

    private class DestSpotAdapter extends RecyclerView.Adapter<DestHolder> {
        private List<RoutineModel.DestSpot> destSpotList;

        public DestSpotAdapter(List<RoutineModel.DestSpot> destSpotsList) {
            this.destSpotList = destSpotsList;
        }

        @Override
        public DestHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(RoutineViewActivity.this);
            View view = layoutInflater.inflate(R.layout.routine_item, viewGroup, false);
            return new DestHolder(view);
        }

        @Override
        public void onBindViewHolder(DestHolder destHolder, int i) {
            RoutineModel.DestSpot destSpot = destSpotList.get(i);
            destHolder.bindDestSpot(destSpot);
        }

        @Override
        public int getItemCount() {
            return destSpotList.size();
        }
    }

    private List<RoutineModel.DestSpot> initDefaultRoutineList() {
        List<RoutineModel.DestSpot> defaultList = new ArrayList<>();
        return defaultList;
    }
}
