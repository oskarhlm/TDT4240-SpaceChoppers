package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.model.AsteroidConfiguration;
import com.mygdx.spacechoppers.model.AsteroidModel;
import com.mygdx.spacechoppers.view.AsteroidView;


public class AsteroidController {

    private final AsteroidModel model;
    private final AsteroidView view;
    private final SpriteBatch sb;

    public AsteroidController(SpriteBatch sb, AsteroidConfiguration config, Vector2 textureSize, World world) {
        this.sb = sb;
        this.model = new AsteroidModel(config.HP, config.location, config.direction, config.size, textureSize, world);
        this.view = new AsteroidView(config.texture);
    }

    public AsteroidModel getModel(){
        return model;
    }

    public void moveAsteroid(){
        model.moveAsteroid();
    }

    public void draw(){
        view.draw(sb, model.getLocation(), model.getSize(), model.getSize());
    }

}
