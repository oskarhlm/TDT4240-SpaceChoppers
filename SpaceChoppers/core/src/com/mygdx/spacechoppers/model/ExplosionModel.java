package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector3;

public class ExplosionModel {

    private Vector3 position;

    public ExplosionModel(Vector3 position, float asteroidSize) {
        this.position = position;
    }
    public Vector3 getPosition() {
        return this.position;
    }
}

