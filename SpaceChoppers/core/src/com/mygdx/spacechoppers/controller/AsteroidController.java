package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.model.AsteroidModel;
import com.mygdx.spacechoppers.model.AsteroidTextures;
import com.mygdx.spacechoppers.view.AsteroidView;

public class AsteroidController {

    private final AsteroidModel model;
    private final AsteroidView view;
    private final SpriteBatch sb;

    public AsteroidController(SpriteBatch sb, AsteroidTextures textures) {
        this.sb = sb;
        this.model = new AsteroidModel(10, new Vector3(500F, 500F, 0F));
        this.view = new AsteroidView(textures);
    }

    public void moveAsteroid(){
        model.moveAsteroid();
    }

    public void draw(){
        view.draw(sb, model.getLocation());
    }

}
