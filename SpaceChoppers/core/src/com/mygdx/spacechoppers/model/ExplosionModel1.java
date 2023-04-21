package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.spacechoppers.AssetManager;

public class ExplosionModel {
    private float statetime;
    private boolean remove = false;
    private static final float FRAME_LENGTH = 0.2f;
    private static final int SIZE = 32;
    private static Animation<TextureRegion> anim = null;

    public ExplosionModel(float x, float y, float asteroidSize) {
        this.x = x;
        this.y = y;
        statetime = 0f;
        if (anim == null) {
            TextureRegion[][] texture = TextureRegion.split(AssetManager.INSTANCE.getExplosion(), SIZE, SIZE);
            anim = new Animation<TextureRegion>(FRAME_LENGTH, texture[0]);
        }
    }

    public void update(float deltaTime) {
        statetime += deltaTime;
        if (anim.isAnimationFinished(statetime)) {
            remove = true;
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(anim.getKeyFrame(statetime), x, y, 500f, 500f);
    }
}

