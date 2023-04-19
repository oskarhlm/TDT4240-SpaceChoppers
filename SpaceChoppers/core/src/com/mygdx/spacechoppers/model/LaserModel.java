package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.helper.ContactType;

public class LaserModel extends Actor {

    private Vector3 position;
    private Vector2 velocity;
    private float initialRotation;
    private final float BASE_VELOCITY = 35;

    public LaserModel(Vector3 position, Vector2 textureSize, float chopperRotation, float rotation, World world) {
        super(1, position, textureSize, world, ContactType.LASER);
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

    public Vector3 getPosition() {
        return this.position;
    }

    public void moveLaser() {
        position.add(velocity.x, velocity.y, 0);
        moveBody(position.x, position.y, initialRotation);
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
