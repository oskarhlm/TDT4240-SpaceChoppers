package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.view.ChopperView;

public class ChopperController {

    private final ChopperModel model;
    private final ChopperView view;
    private SpriteBatch sb;
    private Touchpad touchpad;
    private final int speedScaler = 5;

    public ChopperController(SpriteBatch sb, Touchpad touchpad) {
        this.touchpad = touchpad;
        this.model = new ChopperModel(100, new Vector3(100, 100, 0));
        this.view = new ChopperView();
        this.sb = sb;
    }

    public void moveChopper(float dt) {
        float deltaX = touchpad.getKnobPercentX();
        float deltaY = touchpad.getKnobPercentY();
        float movementMagnitude = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) * speedScaler;

        if (movementMagnitude > 0) {
            float movementNormalizedX = deltaX / movementMagnitude;
            float movementNormalizedY = deltaY / movementMagnitude;
            float movementSpeed = movementMagnitude * speedScaler;
            model.setVelocityVector(new Vector2(deltaX * movementSpeed, deltaY * movementSpeed));
            model.getLocation().x += movementNormalizedX * movementSpeed;
            model.getLocation().y += movementNormalizedY * movementSpeed;
        }

        view.incrementSpriteChangeTime(dt);
    }

    public ChopperModel getModel() {
        return model;
    }

    public void draw() {
        view.draw(sb, model);
    }

}
