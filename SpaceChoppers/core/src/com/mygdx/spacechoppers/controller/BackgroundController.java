package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class BackgroundController extends Actor {

    private final Texture texture;
    private final TextureRegion textureRegion;

    private final int bgWidth;
    private final int bgHeight;
    private final int repetitionsX;
    private final int repetitionsY;

    public BackgroundController() {
        texture = new Texture("starfield_dark_bg.png");
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        textureRegion = new TextureRegion(texture);

        // Set the size of the background image.
        bgWidth = texture.getWidth();
        bgHeight = texture.getHeight();

        // Set the number of repetitions for each direction.
        repetitionsX = 4;
        repetitionsY = 4;
    }

    public void draw(SpriteBatch sb) {
        for (int i = -2; i < repetitionsX + 2; i++) {
            for (int j = -2; j < repetitionsY + 2; j++) {
                float x = i * bgWidth;
                float y = j * bgHeight;
                sb.draw(textureRegion, x, y, bgWidth, bgHeight);
            }
        }
    }

    public int getMapWidth() {
        return texture.getWidth() * repetitionsX;
    }

    public int getMapHeight() {
        return texture.getHeight() * repetitionsY;
    }
}
