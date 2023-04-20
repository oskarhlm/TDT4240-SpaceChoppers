package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.spacechoppers.AssetManager;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperModel;

import java.util.ArrayList;
import java.util.List;

public class ChopperView implements Disposable {
    private final Sprite sprite;
    private int spriteIndex = 0;
    private final List<Texture> textureList;


    public ChopperView() {
        this.textureList = AssetManager.INSTANCE.getHeliSprites();
        Texture currentTexture = textureList.get(spriteIndex);
        this.sprite = new Sprite(currentTexture);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                spriteIndex = ++spriteIndex % textureList.size();
                sprite.setTexture(textureList.get(spriteIndex));
            }
        }, 0, 0.1f);
    }


    public void draw(SpriteBatch sb, ChopperModel model) {

        Vector3 location = model.getLocation();
        float angle = model.getCurrentAngle();
        sb.draw(sprite, location.x, location.y,
                sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),
                sprite.getHeight(),
                (float) 1, (float) 1, angle-90);
    }

    public Sprite getSprite(){
        return sprite;
    }
    @Override
    public void dispose() {}
}
