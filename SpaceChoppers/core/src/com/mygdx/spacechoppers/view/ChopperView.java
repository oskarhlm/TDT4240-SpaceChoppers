package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.AssetManager;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperModel;

public class ChopperView implements Disposable {

    private float timeSinceLastSpriteChange = 0;
    private final Sprite sprite;
    private final Texture texture;
    private int spriteIndex = 0;

    public ChopperView() {
        this.texture = AssetManager.INSTANCE.getManager().get("heli_img/Chopper_1.png", Texture.class);
        this.sprite = new Sprite(texture);
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

    public void incrementSpriteChangeTime(float dt) {
        this.timeSinceLastSpriteChange += dt;
    }

    @Override
    public void dispose() {}
}
