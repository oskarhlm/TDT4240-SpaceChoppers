package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.spacechoppers.AssetManager;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.model.ChopperModel;

import java.util.List;

public class ChopperView implements Disposable {
    private final Sprite sprite;
    private int spriteIndex = 0;
    private final List<Texture> textureList;

    private final int SCALE_FACTOR = 1;


    public ChopperView() {
        this.textureList = AssetManager.INSTANCE.getHeliTextures();
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


    public void draw(SpriteBatch sb, ChopperModel model, Body chopperBody) {

        Vector3 location = model.getLocation();
        float drawX = location.x - sprite.getTexture().getWidth() / 2.0f;
        float drawY = location.y - sprite.getTexture().getHeight() / 2.0f;


        float angle = model.getCurrentAngle();
        sb.draw(sprite, drawX, drawY,
                sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),
                sprite.getHeight(),
                (float) SCALE_FACTOR, (float) SCALE_FACTOR, angle-90);

        // Convert pixels to meters
        float adjustedX = location.x * Const.PIXELS_TO_METERS;
        float adjustedY = location.y * Const.PIXELS_TO_METERS;
        chopperBody.setTransform(adjustedX, adjustedY, 0);
    }



    public Sprite getSprite(){
        return sprite;
    }

    public Vector2 getTextureSize() {
        float width = this.textureList.get(0).getWidth() * SCALE_FACTOR;
        float height = this.textureList.get(0).getHeight() * SCALE_FACTOR;
        return new Vector2(width, height);
    }

    @Override
    public void dispose() {}
}
