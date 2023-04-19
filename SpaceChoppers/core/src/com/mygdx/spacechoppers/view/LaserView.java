package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.SpaceChoppersGame;

public class LaserView implements Disposable {

    private final Texture texture;
    private final Sprite sprite;

    private final float SCALE_FACTOR = 0.5f;

    public LaserView() {
        this.texture = new Texture(Gdx.files.internal("green_laser.png"));
        // Create a new sprite from the texture
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch sb, Vector3 position, float angle) {
        sb.draw(sprite, position.x, position.y,
                sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),
                sprite.getHeight(),
                (float) SCALE_FACTOR, (float) SCALE_FACTOR, angle);

    }

    public Vector2 getTextureSize() {
        float width = this.texture.getWidth() * SCALE_FACTOR;
        float height = this.texture.getHeight() * SCALE_FACTOR;
        return new Vector2(width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
