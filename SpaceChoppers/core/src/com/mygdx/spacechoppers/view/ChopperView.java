package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperModel;

public class ChopperView implements Disposable {

    private Sprite sprite;
    private Texture texture;
    private ChopperModel model;

    public ChopperView(ChopperModel model, Texture texture){
        this.model = model;
        this.texture = texture;
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch sb){

        sb.draw(sprite, model.getLocation().x, model.getLocation().y,
                model.getLocation().x + sprite.getWidth() / 2,
                model.getLocation().y + sprite.getHeight() / 2,
                SpaceChoppersGame.Companion.getWidth(),
                SpaceChoppersGame.Companion.getHeight(),
                (float) 0.25,(float) 0.04,
                model.getCurrentAngle());

    }

    public Vector2 getTextureSize() {
        return new Vector2(sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
