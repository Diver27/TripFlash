package com.example.l.TripFlash.presenter;

import com.example.l.TripFlash.model.LabelModel;
import com.example.l.TripFlash.view.SetLabelViewInterface;

import java.util.List;

public class SetLabelPresenter implements SetLabelPresenterInterface {
    private SetLabelViewInterface setLabelView;
    private LabelModel labelModel;

    public SetLabelPresenter(SetLabelViewInterface setLabelView){
        this.setLabelView=setLabelView;
        labelModel=new LabelModel();
    }

    @Override
    public List<LabelModel.Label> getLabelList(){
        return labelModel.getLabelList();
    }
}
