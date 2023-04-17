package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.view.ChopperView;

public class ChopperController {

    private final ChopperModel model;
    private final ChopperView view;
    private SpriteBatch sb;


    public ChopperController(SpriteBatch sb, Touchpad touchpad){
        this.model = new ChopperModel(100, new Vector3(100, 100, 0), touchpad);
        this.view = new ChopperView();
        this.sb = sb;
    }

    public void moveChopper(){
        model.moveChopper();
    }

    public Vector3 getLocation(){
        return model.getLocation();
    }

    public void draw(){
        view.draw(sb, model.getLocation(), model.getCurrentAngle());
    }

}
