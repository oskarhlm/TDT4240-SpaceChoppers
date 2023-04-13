package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.model.ChopperModel;

public class ChopperView implements Disposable {

    private Sprite chopperSprite;
    private Texture chopperTexture;
    private ChopperModel model;

    public ChopperView(ChopperModel model){
        this.model = model;
        this.chopperTexture = new Texture("helicopter_1.png");
        this.chopperSprite = new Sprite(chopperTexture);
    }

    public Sprite getChopperSprite() {
        return chopperSprite;
    }

    public void draw(SpriteBatch sb){
        sb.draw(chopperSprite, model.getLocation().x, model.getLocation().y);
    }

    @Override
    public void dispose() {
        chopperTexture.dispose();
    }
}
