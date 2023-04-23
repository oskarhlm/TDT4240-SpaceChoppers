package com.mygdx.spacechoppers.model;

import com.mygdx.spacechoppers.interfaces.IModel;

public class HealthBarModel implements IModel {

    ChopperModel model;

    public HealthBarModel(ChopperModel model){
        this.model = model;
    }

    public int getHitPoints(){
        return model.getHitPoints();
    }

}
