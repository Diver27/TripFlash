package com.example.l.TripFlash.view;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.l.TripFlash.GlobalData;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.model.LabelModel;
import com.example.l.TripFlash.presenter.SetLabelPresenter;
import com.example.l.TripFlash.presenter.SetLabelPresenterInterface;

import java.util.ArrayList;
import java.util.List;


public class SetLabelFragment extends android.support.v4.app.Fragment implements SetLabelViewInterface {
    private GlobalData globalData;
    private SetLabelPresenterInterface setLabelPresenter;
    private RecyclerView labelRecyclerView;

    public static SetLabelFragment newInstance() {
        SetLabelFragment f = new SetLabelFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_set_label, container, false);
        globalData = (GlobalData) getActivity().getApplication();
        setLabelPresenter=new SetLabelPresenter(this);
        labelRecyclerView=rootView.findViewById(R.id.label_recycler);
        labelRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        labelRecyclerView.setAdapter(new LabelAdapter(setLabelPresenter.getLabelList()));

        return rootView;
    }

    private class LabelHolder extends RecyclerView.ViewHolder {
        private LabelModel.Label label;
        private TextView description;
        private CheckBox checkBox;

        private LabelHolder(View itemView) {
            super(itemView);
            description=itemView.findViewById(R.id.label_description);
            checkBox=itemView.findViewById(R.id.checkBox);
        }

        private void bindLabel(LabelModel.Label _label) {
            this.label=_label;
            description.setText(label.getDescription());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        if(label.getId()==1){
                            globalData.setPreferedRestarautType(label.getPreferedType());
                        }else if(label.getId()==2){
                            globalData.setPreferedEntertainmentType(label.getPreferedType());
                        }else{
                            globalData.setPreferedTourismType(label.getDescription());
                        }
                    }
                }
            });
        }
    }

    private class LabelAdapter extends RecyclerView.Adapter<LabelHolder> {
        private List<LabelModel.Label> labelList;

        public LabelAdapter(List<LabelModel.Label> labelList) {
            this.labelList=labelList;
        }

        @Override
        public LabelHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.label_item, viewGroup, false);
            return new LabelHolder(view);
        }

        @Override
        public void onBindViewHolder(LabelHolder labelHolder, int i) {
            LabelModel.Label label = labelList.get(i);
            labelHolder.bindLabel(label);
        }

        @Override
        public int getItemCount() {
            return labelList.size();
        }
    }
}
