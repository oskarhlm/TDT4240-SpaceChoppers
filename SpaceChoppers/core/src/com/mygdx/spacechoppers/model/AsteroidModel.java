package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class AsteroidModel extends Actor {

    private int speed;
    private int directionY, directionX;

    public AsteroidModel(int HP, Vector3 location) {
        super(HP, location);
        this.speed = 5;
        Random r = new Random();
    }

    public void moveAsteroid(){

    }
}

