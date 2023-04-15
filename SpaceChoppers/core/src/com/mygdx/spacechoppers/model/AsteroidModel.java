package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class AsteroidModel extends Actor {

    public static final String TEXTURE_PATH = "asteroid/1.png";
    private int speed;
    private int directionY, directionX;

    public AsteroidModel(int HP, Vector3 location) {
        super(HP, location);
        this.speed = 5;
        Random r = new Random();
    }

    public void moveAsteroid(){
        //TODO: Fix this
        location.add((float)0.4, 1, 0);
    }
}

