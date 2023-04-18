package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

public class BackgroundController extends Actor {

    private Texture texture;
    private TextureRegion textureRegion;

    private int bgWidth;
    private int bgHeight;
    private int repetitionsX;
    private int repetitionsY;


    public BackgroundController(Stage stage) {
        texture = new Texture("menu_bg_clean.png");
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
        for (int i = 0; i < repetitionsX; i++) {
            for (int j = 0; j < repetitionsY; j++) {
                float x = i * bgWidth;
                float y = j * bgHeight;
                sb.draw(textureRegion, x, y, bgWidth, bgHeight);
            }
        }
    }
}
