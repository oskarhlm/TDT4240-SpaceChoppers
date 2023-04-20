package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.view.ChopperView;

public class ChopperController {

    private final ChopperModel model;
    private final ChopperView view;
    private Touchpad touchpad;
    private final float initialSpeedScaler = 5;
    private float speedScaler = initialSpeedScaler;
    private boolean boostIsAvailible = true;
    private boolean boostEnabled = false;
    private final float boostMutliplier = 3;
    private float boostDurationSeconds = 0;
    private final float maxBoostDurationSeconds = 10;

    public ChopperController(Touchpad touchpad) {
        this.view = new ChopperView();
        this.touchpad = touchpad;
        this.model = new ChopperModel(100, new Vector3(
                SpaceChoppersGame.Companion.getWidth() / 2 - view.getSprite().getWidth() / 2,
                SpaceChoppersGame.Companion.getHeight() / 2 - view.getSprite().getHeight() / 2,
                100));
    }

    public void moveChopper(float dt) {
        if (boostEnabled) addSpeedBoost(dt);
        System.out.println(speedScaler);
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


    private void addSpeedBoost(float dt) {
        speedScaler *= (1 - boostDurationSeconds / maxBoostDurationSeconds) * boostMutliplier;
        boostDurationSeconds += dt;
        if (boostDurationSeconds > 10) {
            boostDurationSeconds = 0;
            boostEnabled = false;
            speedScaler = initialSpeedScaler;
        }
    }

    public void enableBoost() {
        boostEnabled = true;
        boostIsAvailible = false;
    }

    public void makeBoostAvailible() {
        boostIsAvailible = true;
    }

    public boolean isBoostIsAvailible() {
        return boostIsAvailible;
    }
}
