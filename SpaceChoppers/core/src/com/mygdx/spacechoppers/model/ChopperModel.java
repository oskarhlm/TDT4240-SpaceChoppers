package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class ChopperModel extends Actor {
    private Vector2 velocityVector;

    public ChopperModel(int HP, Vector3 Location, Vector2 textureSize, World world) {
        super(HP, Location, textureSize, world);

        velocityVector = new Vector2(0, 0);
    }

    public Body getBody() {
        return body;
    }

    public float getCurrentAngle() {
        return velocityVector.angleDeg();
    }

    public void setVelocityVector(Vector2 value) {
        this.velocityVector = value;
    }
}
