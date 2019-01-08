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

import com.example.l.TripFlash.GlobalData;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.model.DestSpot;
import com.example.l.TripFlash.presenter.RoutinePresenter;
import com.example.l.TripFlash.presenter.RoutinePresenterInterface;

import java.util.ArrayList;
import java.util.List;

public class RoutineViewActivity extends AppCompatActivity implements RoutineViewInterface {
    private GlobalData globalData;
    private RecyclerView routineListView;
    //private DestSpotAdapter destSpotAdapter;
    private RoutinePresenterInterface routinePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_view);
        globalData = (GlobalData) getApplication();

        routinePresenter=new RoutinePresenter(this);

        routineListView=findViewById(R.id.routine_recycler);
        routineListView.setLayoutManager(new LinearLayoutManager(this));
        //destSpotAdapter=new DestSpotAdapter(initDefaultRoutineList());
        routineListView.setAdapter(new DestSpotAdapter(initDefaultRoutineList()));

        update();
    }

    private void update(){
        routinePresenter.LoadRoutineList(this);
    }

    @Override
    public void showRoutineList(List<DestSpot> routineList){
        routineListView.setAdapter(new DestSpotAdapter(routineList));
    }

    /**
     * Recycler view for routine list
     */
    private class DestHolder extends RecyclerView.ViewHolder{
        private DestSpot destSpot;
        private TextView nameView;
        private TextView locationView;
        private Button deleteButton;

        private DestHolder(View itemView){
            super(itemView);
            nameView=itemView.findViewById(R.id.name);
            locationView=itemView.findViewById(R.id.location);
            deleteButton=itemView.findViewById(R.id.delete_dest_buton);
        }

        private void bindDestSpot(DestSpot dSpot){
            destSpot=dSpot;
            nameView.setText(destSpot.getName());
            locationView.setText(destSpot.getLocation());
            /**
             * 删除路径点按钮监听
             */
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    routinePresenter.deleteDestSpot(destSpot.getId());
                }
            });
        }
    }

    private class DestSpotAdapter extends RecyclerView.Adapter<DestHolder>{
        private List<DestSpot> destSpotList;

        public DestSpotAdapter(List<DestSpot> destSpotsList){
            this.destSpotList=destSpotsList;
        }

        @Override
        public DestHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            LayoutInflater layoutInflater=LayoutInflater.from(RoutineViewActivity.this);
            View view=layoutInflater.inflate(R.layout.routine_item,viewGroup,false);
            return new DestHolder(view);
        }

        @Override
        public void onBindViewHolder(DestHolder destHolder,int i){
            DestSpot destSpot=destSpotList.get(i);
            destHolder.bindDestSpot(destSpot);
        }

        @Override
        public int getItemCount(){
            return destSpotList.size();
        }
    }

    private List<DestSpot> initDefaultRoutineList(){
        List<DestSpot> defaultList=new ArrayList<>();
        return defaultList;
    }
}
