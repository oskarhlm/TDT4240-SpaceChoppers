package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Laser {

    private Vector2 position;
    private Vector2 velocity;
    private float initialRotation;
    private final float BASE_VELOCITY = 10;

    public Laser(Vector2 position, float chopperRotation, float rotation) {

        this.position = position;
        this.initialRotation = rotation;
        this.velocity = getVelocityFromRotation(chopperRotation, BASE_VELOCITY);
    }

    private Vector2 getVelocityFromRotation(float chopperRotation, float baseVelocity) {
        double angleInRadians = Math.toRadians(chopperRotation);
        float xComponent = (float) (baseVelocity * Math.cos(angleInRadians));
        float yComponent = (float) (baseVelocity * Math.sin(angleInRadians));

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
        // TODO: outside camera, not width
        if (position.x < 0 || position.x > Gdx.graphics.getWidth()) {
            return true;
        } else if (position.y < 0 || position.y > Gdx.graphics.getHeight()) {
            return true;
        }
        return false;
    }
}
