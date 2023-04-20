package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.view.ChopperView;

import java.util.TimerTask;

public class ChopperController {

    private final ChopperModel model;
    private final ChopperView view;
    private final Touchpad touchpad;
    private final float initialSpeedScaler = 10;
    private float speedScaler = initialSpeedScaler;
    private boolean boostIsAvailable = true;
    private final float boostMultiplier = 3;
    private float boostDurationSeconds = 0;
    private final Timer timer;

    public ChopperController(Touchpad touchpad) {
        this.view = new ChopperView();
        this.touchpad = touchpad;
        this.timer = new Timer();
        this.model = new ChopperModel(100, new Vector3(
                SpaceChoppersGame.Companion.getWidth() / 2 - view.getSprite().getWidth() / 2,
                SpaceChoppersGame.Companion.getHeight() / 2 - view.getSprite().getHeight() / 2,
                100));
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

            float nextXPosition = model.getLocation().x + movementNormalizedX * movementSpeed;
            float nextYPosition = model.getLocation().y + movementNormalizedY * movementSpeed;

            if (nextXPosition >= 0 && nextXPosition <= SpaceChoppersGame.Companion.getMapWidth() - 150) {
                model.getLocation().x = nextXPosition;
            }
            if (nextYPosition >= 0 && nextYPosition <= SpaceChoppersGame.Companion.getMapHeight() - 150) {
                model.getLocation().y = nextYPosition;
            }
        }
    }

    public ChopperModel getModel() {
        return model;
    }

    public void draw(SpriteBatch sb) {
        view.draw(sb, model);
    }

    public void boost() {
        boostIsAvailable = false;
        this.speedScaler *= boostMultiplier;
        Timer.Task task = new Timer.Task() {
            @Override
            public void run() {
                speedScaler -= 0.2;
                if (speedScaler < initialSpeedScaler) {
                    speedScaler = initialSpeedScaler;
                    boostIsAvailable = true; // TODO: Fix this into own method
                    cancel();
                }
            }
        };

        timer.scheduleTask(task, 0, 0.1F);
    }


    public boolean isBoostIsAvailable() {
        return boostIsAvailable;
    }
}
