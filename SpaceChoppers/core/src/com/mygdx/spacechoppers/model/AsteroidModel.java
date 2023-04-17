package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.helper.ContactType;

import java.util.Random;

public class AsteroidModel extends Actor {
    public static final String TEXTURE_PATH = "asteroid.png";
    private int speed;
    private int directionY, directionX;

    public AsteroidModel(int HP, Vector3 Location, Vector2 textureSize, World world) {
        super(HP, Location, textureSize.x, textureSize.y, world, ContactType.ASTEROID);
        this.speed = 5;
        Random r = new Random();
    }

    public void moveAsteroid(){
        //TODO: Fix this
        location.add((float)0.4, 1, 0);
    }
}

