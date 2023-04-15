package com.mygdx.spacechoppers.model;

import static com.mygdx.spacechoppers.helper.Const.PIXELS_TO_METERS;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.helper.BodyHelper;
import com.mygdx.spacechoppers.helper.ContactType;

public abstract class Actor implements IActor{

    protected int HP;
    protected Vector3 location;
    protected Body body;

    public Actor(int HP, Vector3 location, float textureWidth, float textureHeight, World world, ContactType contactType) {
        this.HP = HP;
        this.location = location;
        this.body = BodyHelper.createBody(location.x * PIXELS_TO_METERS, location.y * PIXELS_TO_METERS, textureWidth * PIXELS_TO_METERS, textureHeight * PIXELS_TO_METERS, 1, world, contactType);
    }

    @Override
    public void takeDamage() {
        this.HP--;
    }

    public int getHP() {
        return HP;
    }

    public Vector3 getLocation() {
        return location;
    }
}
