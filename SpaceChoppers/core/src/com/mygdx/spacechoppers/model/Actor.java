package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.SpaceChoppersGame;

public abstract class Actor implements IActor{

    protected int hitPoints;
    protected Vector3 location;

    public Actor(int hitPoints, Vector3 location) {
        this.hitPoints = hitPoints;
        this.location = location;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Vector3 getLocation() {
        return location;
    }

    @Override
    public void takeDamage() {
        this.hitPoints--;
    }

    public boolean isOutOfBounds() {
        return (location.x < 0 ||
                location.y < 0 ||
                location.x >= SpaceChoppersGame.Companion.getWidth() ||
                location.y >= SpaceChoppersGame.Companion.getHeight()
        );
    }
}
