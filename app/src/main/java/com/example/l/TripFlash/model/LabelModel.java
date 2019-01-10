package com.example.l.TripFlash.model;

import java.util.ArrayList;
import java.util.List;

public class LabelModel {
    public class Label{
        int id;
        private String description;
        private String preferedType;

        public Label(int id,String des,String pref){
            this.id=id;
            description=des;
            preferedType=pref;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public String getPreferedType() {
            return preferedType;
        }
    }

    private List<Label> labelList;

    public LabelModel(){
        labelList=new ArrayList<>();
        labelList.add(new Label(1,"你偏好辛辣的食品吗？","050102"));
        labelList.add(new Label(2,"你喜爱欣赏表演和演出吗？","080600"));
        labelList.add(new Label(3,"你是基督教徒吗？","080600"));
    }

    public List<Label> getLabelList() {
        return labelList;
    }
}
