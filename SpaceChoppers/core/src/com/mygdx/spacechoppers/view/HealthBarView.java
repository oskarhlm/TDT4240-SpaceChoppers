package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.spacechoppers.AssetManager;
import com.mygdx.spacechoppers.SpaceChoppersGame;

public class HealthBarView {

    OrthographicCamera cam;

    private Texture blank;
    public HealthBarView(OrthographicCamera cam) {
        this.cam = cam;
        this.blank = AssetManager.INSTANCE.getBlank();
    }

    public void draw(SpriteBatch sb, int hitPoints) {

        if (hitPoints > 75){
            sb.setColor(Color.GREEN);
        }
        else if(hitPoints > 25){
            sb.setColor(Color.ORANGE);
        } else {
            sb.setColor(Color.RED);
        }

        sb.draw(blank,
                cam.position.x - cam.viewportWidth / 2,
                cam.position.y + cam.viewportHeight / 2 - 20F,
                SpaceChoppersGame.Companion.getWidth() * (hitPoints / 100f),
                20F);
        sb.setColor(Color.WHITE);

    }
}
