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

    public LaserView() {
        this.texture = new Texture(Gdx.files.internal("green_laser.png"));
        // Create a new sprite from the texture
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch sb, Vector3 position, float angle) {
        sb.draw(sprite, position.x - 100, position.y + 50,
                sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),
                sprite.getHeight(),
                (float) 0.2, (float) 0.2, angle);

    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
