package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class Actor implements IActor{

    protected int HP;
    protected Vector3 location;
    protected Direction direction;
    public Touchpad tp;

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
