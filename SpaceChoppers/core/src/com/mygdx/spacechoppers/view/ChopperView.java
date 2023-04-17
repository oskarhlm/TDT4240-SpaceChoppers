package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperTextures;

public class ChopperView implements Disposable {

    private final Sprite sprite;
    private final Texture texture;

    public ChopperView(ChopperTextures textures){
        this.texture = textures.getChopperTextures();
        this.sprite = new Sprite(texture);
        this.sprite.setSize(this.sprite.getWidth() * 0.25f, this.sprite.getHeight() * 0.04f);
        this.sprite.setOriginCenter();
    }

    public void draw(SpriteBatch sb, Vector3 location, float angle) {
        sprite.setPosition(location.x, location.y);
        sprite.setRotation(angle);
        sprite.draw(sb);
    }



    public Vector2 getTextureSize() {
        return new Vector2(sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
