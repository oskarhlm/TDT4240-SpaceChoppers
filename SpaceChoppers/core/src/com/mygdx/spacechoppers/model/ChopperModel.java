package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.helper.ContactType;

import java.awt.Point;

public class ChopperModel extends Actor{

    private Vector2 currentVector;
    private Vector2 textureSize;
    private Touchpad touchpad;
    private float movementVector;
    private final int speedScaler = 5;

    public ChopperModel(int HP, Vector3 Location, Touchpad touchpad, Vector2 textureSize, World world) {
        super(HP, Location, textureSize.x, textureSize.y, world, ContactType.CHOPPER);
        this.currentVector = new Vector2(0,0);
        this.textureSize = textureSize;
        this.touchpad = touchpad;
        this.movementVector = 0;
    }

    public void moveChopper() {
        float deltaX = touchpad.getKnobPercentX();
        float deltaY = touchpad.getKnobPercentY();
        movementVector = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(2, deltaY)) * speedScaler;

        if (deltaX != 0 && deltaY != 0)
            currentVector = new Vector2(deltaX, deltaY);
        float newX = body.getPosition().x + deltaX * movementVector / Const.PIXELS_TO_METERS;
        float newY = body.getPosition().y + deltaY * movementVector / Const.PIXELS_TO_METERS;
        body.setTransform(newX, newY, body.getAngle());
    }

    public void updatePosition() {
        location.x = body.getPosition().x * Const.PIXELS_TO_METERS - textureSize.x / 2;
        location.y = body.getPosition().y * Const.PIXELS_TO_METERS - textureSize.y / 2;
    }

    public float getCurrentAngle(){
        return currentVector.angleDeg();
    }
}