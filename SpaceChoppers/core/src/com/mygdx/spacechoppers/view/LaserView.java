package com.mygdx.spacechoppers.view;

import static java.lang.Math.PI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.utils.AssetManager;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.interfaces.IView;
import com.mygdx.spacechoppers.model.LaserModel;

import org.jetbrains.annotations.NotNull;

public class LaserView implements IView, Disposable {

    private final Texture texture;
    private final Sprite sprite;

    private final float SCALE_FACTOR = 0.5f;

    public LaserView() {
        this.texture = AssetManager.INSTANCE.getLaserTexture();
        this.sprite = new Sprite(texture);
    }

    public void draw(@NotNull SpriteBatch sb, Vector3 position, float angle, Body laserBody) {
        sb.draw(sprite, position.x, position.y,
                sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),
                sprite.getHeight(),
                (float) SCALE_FACTOR, (float) SCALE_FACTOR, angle);

        // Now draw body
        float adjustedX = (position.x + texture.getWidth() / 2f) * Const.PIXELS_TO_METERS;
        float adjustedY = (position.y + texture.getHeight() / 2f) * Const.PIXELS_TO_METERS;
        laserBody.setTransform(adjustedX, adjustedY, degreesToRadians(angle));
    }

    private float degreesToRadians(float angleInDegrees) {
        return (float) (angleInDegrees * (PI / 180));
    }

    public Vector2 getTextureSize() {
        float width = this.texture.getWidth() * SCALE_FACTOR;
        float height = this.texture.getHeight() * SCALE_FACTOR;
        return new Vector2(width, height);
    }

    @Override
    public void dispose() {}
}
