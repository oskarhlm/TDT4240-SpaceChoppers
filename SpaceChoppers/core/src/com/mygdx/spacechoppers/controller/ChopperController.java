package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.view.ChopperView;

public class ChopperController {

    private final ChopperModel model;
    private final ChopperView view;
    private SpriteBatch sb;


    public ChopperController(SpriteBatch sb, Touchpad touchpad){
        this.view = new ChopperView();
        this.model = new ChopperModel(100, new Vector3(
                SpaceChoppersGame.Companion.getWidth() / 2 - view.getSprite().getWidth() / 2,
                SpaceChoppersGame.Companion.getHeight() / 2 - view.getSprite().getHeight() / 2,
                0), touchpad);
        this.sb = sb;
    }

    public Vector3 getPosition() {
        return model.getLocation();
    }

    public void moveChopper(){
        model.moveChopper();
    }


    public float getAngle() {
        return model.getCurrentAngle();
    }

    public void draw(){
        view.draw(sb, model.getLocation(), model.getCurrentAngle());
    }

}
