package com.mygdx.spacechoppers.model;

public class HealthBarModel {

    ChopperModel model;

    public HealthBarModel(ChopperModel model){
        this.model = model;
    }

    public int getHitPoints(){
        return model.getHitPoints();
    }

}
