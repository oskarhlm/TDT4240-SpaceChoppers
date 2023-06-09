package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.utils.AssetManager;
import com.mygdx.spacechoppers.interfaces.IView;
import com.mygdx.spacechoppers.model.ExplosionModel;

import org.jetbrains.annotations.NotNull;

public class ExplosionView implements IView {

    private static final float FRAME_LENGTH = 0.2f;
    private final int FRAME_SIZE = 32;
    private Animation<TextureRegion> anim = null;
    private boolean isAnimationDone = false;
    private float stateTime;
    private final int explosionSize;

    public ExplosionView(int explosionSize) {
        this.explosionSize = explosionSize;
        stateTime = 0f;

        // Init textures
        TextureRegion[][] texture = TextureRegion.split(AssetManager.INSTANCE.getExplosion(), FRAME_SIZE, FRAME_SIZE);
        anim = new Animation<>(FRAME_LENGTH, texture[0]);
    }

    public boolean isAnimationDone() {
        return this.isAnimationDone;
    }

    public void update(float dt) {
        stateTime += dt;
        if (anim.isAnimationFinished(stateTime)) {
            isAnimationDone = true;
        }
    }

    public void draw(@NotNull SpriteBatch sb, Vector3 position) {
        sb.draw(anim.getKeyFrame(stateTime), position.x, position.y, explosionSize, explosionSize);
    }
}
