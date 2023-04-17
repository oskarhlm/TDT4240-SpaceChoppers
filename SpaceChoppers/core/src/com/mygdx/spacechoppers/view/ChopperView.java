package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.Chopper;

public class ChopperView implements Disposable {

    private final Sprite sprite;
    private final Texture texture;
    private final Chopper model;

    public ChopperView(Chopper model){
        this.model = model;
        this.texture = new Texture("helicopter_1.png");
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

    @Override
    public void dispose() {
        texture.dispose();
    }
}
