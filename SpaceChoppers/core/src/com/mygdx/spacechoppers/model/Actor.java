package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.helper.BodyHelper;
import com.mygdx.spacechoppers.helper.ContactType;

public abstract class Actor implements IActor{

    protected int hitPoints;
    protected Vector3 location;
    protected Body body;

    public Actor(int hitPoints, Vector3 location, float textureWidth, float textureHeight, World world, ContactType contactType) {
        this.hitPoints = hitPoints;
        this.location = location;
        this.body = BodyHelper.createBody(location.x, location.y, textureWidth, textureHeight, 1, world, contactType);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Vector3 getLocation() {
        return location;
    }
    
    @Override
    public void takeDamage() {
        this.hitPoints--;
    }
}
