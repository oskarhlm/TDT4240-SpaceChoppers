package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.model.ChopperTextures;
import com.mygdx.spacechoppers.view.ChopperView;

public class ChopperController {

    private final ChopperModel model;
    private final ChopperView view;
    private SpriteBatch sb;


    public ChopperController(SpriteBatch sb, Touchpad touchpad, ChopperTextures textures, World world){
        this.model = new ChopperModel(100, new Vector3(300, 300, 0), touchpad, textures.getTextureSize(), world);
        this.view = new ChopperView(textures);
        this.sb = sb;
    }

    public void moveChopper(){
        model.moveChopper();
    }

    public void updatePosition() {
        model.updatePosition();
    }

    public void draw(){
        view.draw(sb, model.getLocation(), model.getCurrentAngle());
    }

    public ChopperModel getModel() {
        return model;
    }

}
