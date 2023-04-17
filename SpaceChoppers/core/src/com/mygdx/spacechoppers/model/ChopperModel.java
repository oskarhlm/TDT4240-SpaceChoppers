package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.helper.ContactType;

public class ChopperModel extends Actor{
    public static final String TEXTURE_PATH = "chopper/1.png";

    private Vector2 currentVector;
    private Touchpad touchpad;
    private float movementVector;
    private final int speedScaler = 5;

    public ChopperModel(int HP, Vector3 Location, Touchpad touchpad, Vector2 textureSize, World world) {
        super(HP, LocationtextureSize.x, textureSize.y, world, ContactType.CHOPPER);
        currentVector = new Vector2(0,0);
        this.touchpad = touchpad;
        this.movementVector = 0;
    }

    public void moveChopper() {
        float deltaX = touchpad.getKnobPercentX();
        float deltaY = touchpad.getKnobPercentY();
        movementVector = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(2, deltaY)) * speedScaler;

        if (deltaX != 0 && deltaY != 0)
            currentVector = new Vector2(deltaX, deltaY);
        getLocation().x += deltaX * movementVector;
        getLocation().y += deltaY * movementVector;
    }

    public float getCurrentAngle(){
        return currentVector.angleDeg();
    }
}