package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ChopperModel extends Actor{
    private Vector2 currentVector;

    public ChopperModel(int HP, Vector3 Location) {
        super(HP, Location);
        currentVector = new Vector2(0,0);
    }

    public void setCurrentVector(Vector2 currentVector) {
        this.currentVector = currentVector;
    }

    public float getCurrentAngle(){
        return currentVector.angleDeg();
    }
}