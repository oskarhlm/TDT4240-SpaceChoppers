package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.model.AsteroidModel;
import com.mygdx.spacechoppers.model.AsteroidTextures;
import com.mygdx.spacechoppers.view.AsteroidView;

public class AsteroidController {

    private final AsteroidModel model;
    private final AsteroidView view;
    private final SpriteBatch sb;

    public AsteroidController(SpriteBatch sb, AsteroidTextures textures, World world) {
        this.sb = sb;
        this.model = new AsteroidModel(10, new Vector3(200F, 200F, 0F), textures.getTextureSize(), world);
        this.view = new AsteroidView(textures);
    }

    public void moveAsteroid(){
        model.moveAsteroid();
    }

    public void updatePosition() {
        model.updatePosition();
    }

    public void draw(){
        view.draw(sb, model.getLocation());
    }

    public AsteroidModel getModel() {
        return model;
    }

}
