package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class ChopperModel extends Actor{

    private Sprite chopperSprite;

    public ChopperModel(int HP, Vector3 Location) {
        super(HP, Location);
        chopperSprite = new Sprite(new Texture("helicopter_1.png"));
    }

    public Sprite getChopperSprite() {
        return chopperSprite;
    }

}