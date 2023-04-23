package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.interfaces.IModel;

public class ExplosionModel implements IModel {

    private Vector3 position;

    public ExplosionModel(Vector3 position, float asteroidSize) {
        this.position = position;
    }
    public Vector3 getPosition() {
        return this.position;
    }
}

