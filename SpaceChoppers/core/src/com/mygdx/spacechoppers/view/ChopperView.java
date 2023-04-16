package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.textures.ChopperTextures;

public class ChopperView {

    private Sprite sprite;
    private ChopperTextures textures;
    private ChopperModel model;

    public ChopperView(ChopperModel model, ChopperTextures textures){
        this.model = model;
        this.textures = textures;
    }

    public void draw(SpriteBatch sb) {
        sprite = new Sprite(textures.getChopperTexture());

        sb.draw(sprite, model.getLocation().x, model.getLocation().y,
                model.getLocation().x + sprite.getWidth() / 2,
                model.getLocation().y + sprite.getHeight() / 2,
                SpaceChoppersGame.Companion.getWidth(),
                SpaceChoppersGame.Companion.getHeight(),
                (float) 0.2, (float) 0.1,
                model.getCurrentAngle());
    }

    public Vector2 getTextureSize() {
        return new Vector2(sprite.getWidth(), sprite.getHeight());
    }
}
