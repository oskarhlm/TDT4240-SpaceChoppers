package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.model.ChopperModel;

public class ChopperController {

    private final ChopperModel model;

    private Touchpad touchpad;
    private Skin skin;
    private float scaler;

    public ChopperController(ChopperModel model, Touchpad touchpad){
        this.model = model;
        this.skin = new Skin(Gdx.files.internal("neon/neon-ui.json"));
        this.touchpad = touchpad;
        this.scaler = 0;
    }

    public void moveChopper() {
        scaler = (float) (Math.sqrt((touchpad.getKnobPercentX() * touchpad.getKnobPercentX()) + (touchpad.getKnobPercentY() * touchpad.getKnobPercentY())) * 5);
        //TODO: Fix location update
    }

}
