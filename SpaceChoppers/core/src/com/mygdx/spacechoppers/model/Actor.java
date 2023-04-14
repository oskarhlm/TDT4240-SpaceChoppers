package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public abstract class Actor implements IActor{

    protected int HP;
    protected Vector3 location;

    public int getHP() {
        return HP;
    }

    public Vector3 getLocation() {
        return location;
    }

    public Actor(int HP, Vector3 location) {
        this.HP = HP;
        this.location = location;
    }

    @Override
    public void takeDamage() {
        this.HP--;
    }
}
