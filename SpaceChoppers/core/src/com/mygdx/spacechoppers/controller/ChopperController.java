package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.view.ChopperView;
import kotlin.Pair;

public class ChopperController {

    private final ChopperModel model;
    private final ChopperView view;
    private Touchpad touchpad;
    private final int speedScaler = 5;


    public ChopperController(Touchpad touchpad, Vector2 textureSize, World world) {
        this.view = new ChopperView();
        this.touchpad = touchpad;
        this.model = new ChopperModel(100, new Vector3(
                SpaceChoppersGame.Companion.getWidth() / 2 - view.getSprite().getWidth() / 2,
                SpaceChoppersGame.Companion.getHeight() / 2 - view.getSprite().getHeight() / 2,
                100) , textureSize, world);
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

}
