package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.model.AsteroidConfiguration;
import com.mygdx.spacechoppers.model.AsteroidModel;
import com.mygdx.spacechoppers.model.AsteroidTextures;
import com.mygdx.spacechoppers.view.AsteroidView;

public class AsteroidController {

    private final AsteroidModel model;
    private final AsteroidView view;
    private final SpriteBatch sb;

    public AsteroidController(SpriteBatch sb, AsteroidConfiguration config) {
        this.sb = sb;
        this.model = new AsteroidModel(config.HP, config.location);
        this.view = new AsteroidView(config.texture);
    }

    public void moveAsteroid(){
        model.moveAsteroid();
    }

    public void draw(){
        view.draw(sb, model.getLocation());
    }

}
