package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.helper.ContactType;

import java.util.Random;

public class AsteroidModel extends Actor {
    private int speed;
    private int directionY, directionX;
    private Vector2 textureSize;

    public AsteroidModel(int HP, Vector3 Location,Vector2 textureSize, World world) {
        super(HP, Location, textureSize.x, textureSize.y, world, ContactType.ASTEROID);
        this.speed = 5;
        this.textureSize = textureSize;
        Random r = new Random();
    }

    public void moveAsteroid() {
        // Update the Box2D body's velocity based on your game logic
        float velocityX = (float) 0.4;
        float velocityY = 1;
        body.setLinearVelocity(velocityX, velocityY);

        // Call the updatePosition method to update the location based on the Box2D body
        updatePosition();
    }


    public void updatePosition() {
        location.x = body.getPosition().x * Const.PIXELS_TO_METERS - textureSize.x / 2;
        location.y = body.getPosition().y * Const.PIXELS_TO_METERS - textureSize.y / 2;
    }
}

