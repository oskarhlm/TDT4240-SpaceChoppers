package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.helper.ContactType;

public class AsteroidModel extends Actor {

    private final float size;
    private int speed;
    private final Vector2 direction;

    public AsteroidModel(int HP, Vector3 location, Vector2 direction, Float size, Vector2 textureSize, World world) {
        super(HP, location, textureSize.x, textureSize.y, world, ContactType.ASTEROID);
        this.direction = direction;
        this.size = size;

    }

    public void moveAsteroid(){
        body.setLinearVelocity(direction.x, direction.y);
        updatePosition();
    }

    public void updatePosition() {
        location.x = body.getPosition().x / Const.PIXELS_TO_METERS - size / 2;
        location.y = body.getPosition().y / Const.PIXELS_TO_METERS - size / 2;
    }

    public float getSize() {
        return size;
    }
}

