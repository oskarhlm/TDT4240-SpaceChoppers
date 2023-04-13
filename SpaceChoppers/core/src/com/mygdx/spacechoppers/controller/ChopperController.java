package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.model.ChopperModel;

public class ChopperController {

    private final ChopperModel model;
    private Touchpad touchpad;
    private float movementVector;
    private final int speedScaler = 5;

    public ChopperController(ChopperModel model, Touchpad touchpad){
        this.model = model;
        this.touchpad = touchpad;
        this.movementVector = 0;
    }

    public void moveChopper() {
        float deltaX = touchpad.getKnobPercentX();
        float deltaY = touchpad.getKnobPercentY();
        movementVector = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(2, deltaY)) * speedScaler;

        model.setCurrentVector(new Vector2(deltaX, deltaY));
        model.getLocation().x += deltaX * movementVector;
        model.getLocation().y += deltaY * movementVector;
    }

}
