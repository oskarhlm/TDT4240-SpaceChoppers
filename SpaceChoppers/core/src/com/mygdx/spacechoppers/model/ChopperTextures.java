package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class ChopperTextures {

    public static final String TEXTURE_PATH = "chopper/1.png";

    private Texture chopperTexture;

    public ChopperTextures(){
        this.chopperTexture = new Texture(TEXTURE_PATH);
    }

    public Texture getChopperTextures(){
        return chopperTexture;
    }

    public Vector2 getTextureSize(){
        return new Vector2(chopperTexture.getWidth(), chopperTexture.getHeight());
    }

}
