package com.example.l.TripFlash.view;

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
import com.example.l.TripFlash.model.GalleryModel;
import com.example.l.TripFlash.presenter.GalleryPresenter;
import com.example.l.TripFlash.presenter.GalleryPresenterInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GalleryViewFragment extends android.support.v4.app.Fragment implements GalleryViewInterface {
    private GlobalData globalData;
    private GalleryPresenterInterface galleryPresenter;
    private RecyclerView blueprintRecyclerView;

    public static GalleryViewFragment newInstance() {
        GalleryViewFragment f = new GalleryViewFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery_view, container, false);
        globalData = (GlobalData) getActivity().getApplication();
        galleryPresenter=new GalleryPresenter(this);
        blueprintRecyclerView=rootView.findViewById(R.id.gallery_recycler);
        blueprintRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        blueprintRecyclerView.setAdapter(new PlanAdapter(initBlueprintList()));

        galleryPresenter.getPlanList();
        return rootView;
    }

    @Override
    public void onResume() {
        if(globalData.ifChanged){
            Calendar calendar = Calendar.getInstance();
            galleryPresenter.addPlan(globalData.getCity(),String.valueOf(calendar.get(Calendar.YEAR))+"."+String.valueOf(calendar.get(Calendar.MONTH)+1),globalData.getDestSpotList());
            globalData.ifChanged=false;
            galleryPresenter.getPlanList();
        }
        super.onResume();
    }

    @Override
    public void showPlanList(List<GalleryModel.Blueprint> planList){
        blueprintRecyclerView.setAdapter(new PlanAdapter(planList));
    }

    private class PlanHolder extends RecyclerView.ViewHolder {
        private GalleryModel.Blueprint plan;
        private TextView cityName;
        private TextView createTime;
        private Button detailButton;
        private Button deleteButton;

        private PlanHolder(View itemView) {
            super(itemView);
            cityName=itemView.findViewById(R.id.destination_city);
            createTime=itemView.findViewById(R.id.create_date);
            detailButton=itemView.findViewById(R.id.check_gallery_detail);
            deleteButton=itemView.findViewById(R.id.delete_gallery);
        }

        private void bindPlan(GalleryModel.Blueprint _plan) {
            this.plan=_plan;
            cityName.setText(plan.getCityname());
            createTime.setText(plan.getCreateDate());

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    galleryPresenter.deletePlan(plan);
                    galleryPresenter.getPlanList();
                }
            });

            detailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private class PlanAdapter extends RecyclerView.Adapter<PlanHolder> {
        private List<GalleryModel.Blueprint> blueprintList;

        public PlanAdapter(List<GalleryModel.Blueprint> blueprintList) {
            this.blueprintList=blueprintList;
        }

        @Override
        public PlanHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.gallery_item, viewGroup, false);
            return new PlanHolder(view);
        }

        @Override
        public void onBindViewHolder(PlanHolder planHolder, int i) {
            GalleryModel.Blueprint blueprint = blueprintList.get(i);
            planHolder.bindPlan(blueprint);
        }

        @Override
        public int getItemCount() {
            return blueprintList.size();
        }
    }

    List<GalleryModel.Blueprint> initBlueprintList(){
        List<GalleryModel.Blueprint> tempList=new ArrayList<>();
        return tempList;
    }
}
