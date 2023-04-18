package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.spacechoppers.AssetManager;

import java.util.ArrayList;
import java.util.List;

public class Explosion {
    public static final float FRAME_LENGTH = .3f;
    public static final int OFFSET = 8; // TODO: 1/2 of heli width
    public static final int SIZE = 32;
    private static Animation anim = null;
    float x, y;
    float statetime;
    public boolean remove = false;

    public Explosion(float x, float y, float asteroidSize){
        this.x = x - OFFSET;
        this.y = y - OFFSET;
        statetime = 0;

        if (anim == null){
            Texture texture = AssetManager.INSTANCE.getManager().get("explosion.png", Texture.class);
            anim = new Animation(FRAME_LENGTH, TextureRegion.split(texture, SIZE, SIZE)[0]);
        }
    }

    public void update(float deltaTime){
        statetime += deltaTime;
        if (anim.isAnimationFinished(statetime)){
            remove = true;
            statetime = 0; // TODO: Remove (only used in testing)
        }
    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(
                (TextureRegion) anim.getKeyFrame(statetime),
                x,
                y,
                500,
                500);
    }

}
