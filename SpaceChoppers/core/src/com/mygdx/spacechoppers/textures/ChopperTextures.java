package com.mygdx.spacechoppers.textures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.model.ChopperModel;

public class ChopperTextures implements Disposable {

    private Texture chopperTexture;
    private Vector2 chopperTextureSize;
    private int chopperTextureNumber;

    public ChopperTextures() {
        chopperTextureNumber = 1;
        chopperTexture = new Texture(String.format("chopper/%o.png", chopperTextureNumber));
        chopperTextureSize = new Vector2(chopperTexture.getWidth(), chopperTexture.getHeight());
    }

    public Texture getChopperTexture() {
        if (chopperTextureNumber > 4)
            chopperTextureNumber = 1;

        chopperTexture = new Texture(String.format("chopper/%o.png", chopperTextureNumber));

        return chopperTexture;
    }

    public Vector2 getChopperTextureSize() {
        return chopperTextureSize;
    }

    @Override
    public void dispose() {
        chopperTexture.dispose();
    }
}
