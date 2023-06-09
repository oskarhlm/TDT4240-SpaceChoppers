package com.mygdx.spacechoppers.model;

import static com.mygdx.spacechoppers.helper.Const.PIXELS_TO_METERS;

import static java.lang.Math.PI;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.helper.BodyHelper;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.helper.ContactType;

public abstract class Actor {

    public int hitPoints;
    public Vector3 location;
    public Body body;

    public Actor(int hitPoints, Vector3 location, Vector2 textureSize, World world) {
        this.hitPoints = hitPoints;
        this.location = location;
        this.body = BodyHelper.createBody(location.x * PIXELS_TO_METERS, location.y * PIXELS_TO_METERS, textureSize, 1, world, this);
    }

    public int getHitPoints() {
        return hitPoints;
    }
    public void takeDamage() {
        this.hitPoints -= 10;
    }

    public Vector3 getLocation() {
        return location;
    }

    public boolean isOutOfBounds() {
        return (location.x < -SpaceChoppersGame.Companion.getMapWidth() ||
                location.y < -SpaceChoppersGame.Companion.getMapHeight() ||
                location.x >= SpaceChoppersGame.Companion.getMapWidth() * 2 ||
                location.y >= SpaceChoppersGame.Companion.getMapHeight() * 2
        );
    }
}
