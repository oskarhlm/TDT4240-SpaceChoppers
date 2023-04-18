package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.spacechoppers.SpaceChoppersGame;

public class Laser {

    private Vector2 position;
    private Vector2 velocity;
    private float initialRotation;
    private final float BASE_VELOCITY = 35;

    public Laser(Vector2 position, float chopperRotation, float rotation) {
        this.position = position;
        this.initialRotation = rotation;
        this.velocity = getVelocityFromRotation(chopperRotation);
    }

    private Vector2 getVelocityFromRotation(float chopperRotation) {
        double angleInRadians = Math.toRadians(chopperRotation);
        float xComponent = (float) (BASE_VELOCITY * Math.cos(angleInRadians));
        float yComponent = (float) (BASE_VELOCITY * Math.sin(angleInRadians));

        return new Vector2(xComponent, yComponent);
    }

    public float getInitialRotation() {
        return this.initialRotation;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void moveLaser() {
        position.add(velocity.x, velocity.y);
    }

    public boolean isLaserOutsideOfCamera() {
        if (position.x < -1000 || position.x > SpaceChoppersGame.Companion.getMapWidth() + 1000) {
            return true;
        } else if (position.y < -1000 || position.y > SpaceChoppersGame.Companion.getMapHeight() + 1000) {
            return true;
        }
        return false;
    }
}
