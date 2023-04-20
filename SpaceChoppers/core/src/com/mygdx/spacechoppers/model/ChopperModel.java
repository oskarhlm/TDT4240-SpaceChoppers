package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.helper.ContactType;

public class ChopperModel extends Actor{
    private final int speedScaler = 5;
    private Vector2 velocityVector;

    public ChopperModel(int HP, Vector3 Location, Vector2 textureSize, World world) {
        super(HP, Location, textureSize, world, ContactType.CHOPPER);
        velocityVector = new Vector2(0,0);
    }

    public Body getBody() {
        return body;
    }

    public float getCurrentAngle() {
        return velocityVector.angleDeg();
    }

    public void setVelocityVector(Vector2 value) { this.velocityVector = value; }
}
