package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class AsteroidModel extends Actor {

    private final float size;
    private int speed;
    private final Vector2 direction;

    public AsteroidModel(int HP, Vector3 location, Vector2 direction, float size) {
        super(HP, location);
        this.direction = direction;
        this.size = size;
    }

    public void moveAsteroid(){
        location.add(direction.x, direction.y, 0);
    }

    public float getSize() {
        return size;
    }
}

