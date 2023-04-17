package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.SpaceChoppersGame;

public class ChopperView implements Disposable {

    private final Sprite sprite;
    private final Texture texture;

    public ChopperView(){
        this.texture = new Texture("helicopter_1.png");
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch sb, Vector3 location, float angle){

        sb.draw(sprite, location.x, location.y,
                location.x + sprite.getWidth() / 2,
                location.y + sprite.getHeight() / 2,
                SpaceChoppersGame.Companion.getWidth(),
                SpaceChoppersGame.Companion.getHeight(),
                (float) 0.25,(float) 0.04, angle);

    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
