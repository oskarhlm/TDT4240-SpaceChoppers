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
        this.textureList = new ArrayList<>();
        // TODO: This can be cleaned up
        this.textureList.add(AssetManager.INSTANCE.getManager().get("heli_img/Chopper_1.png", Texture.class));
        this.textureList.add(AssetManager.INSTANCE.getManager().get("heli_img/Chopper_2.png", Texture.class));
        this.textureList.add(AssetManager.INSTANCE.getManager().get("heli_img/Chopper_3.png", Texture.class));
        this.textureList.add(AssetManager.INSTANCE.getManager().get("heli_img/Chopper_4.png", Texture.class));

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
                location.x + sprite.getWidth() / 2,
                location.y + sprite.getHeight() / 2,
                SpaceChoppersGame.Companion.getWidth(),
                SpaceChoppersGame.Companion.getHeight(),
                (float) 0.25, (float) 0.04, angle);
    }

    @Override
    public void dispose() {}
}
