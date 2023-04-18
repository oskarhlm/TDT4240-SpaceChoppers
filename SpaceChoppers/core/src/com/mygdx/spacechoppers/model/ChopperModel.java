package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class ChopperModel extends Actor{
    private final int speedScaler = 5;
    private Vector2 velocityVector;


    public ChopperModel(int HP, Vector3 Location) {
        super(HP, Location);
        velocityVector = new Vector2(0,0);
    }

    public float getCurrentAngle() {
        return velocityVector.angleDeg();
    }

    public void setVelocityVector(Vector2 value) { this.velocityVector = value; }


}
