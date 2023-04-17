package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class AsteroidConfiguration {

    public TextureRegion texture;
    public int HP;
    public Vector3 location;
    public Vector2 direction;
    public float size;

    public AsteroidConfiguration(TextureRegion texture, int HP, Vector3 location, Vector2 direction, int size){
        this.HP = HP;
        this.texture = texture;
        this.location = location;
        this.direction = direction;
        this.size = size;
    }
}
