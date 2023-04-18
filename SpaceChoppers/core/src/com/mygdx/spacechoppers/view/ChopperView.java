package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.AssetManager;
import com.mygdx.spacechoppers.SpaceChoppersGame;

public class ChopperView implements Disposable {

    private final Sprite sprite;
    private final Texture texture;

    public ChopperView() {
        this.texture = AssetManager.INSTANCE.getManager().get("helicopter_1.png", Texture.class);
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch sb, Vector3 location, float angle) {
        sb.draw(sprite, location.x, location.y,
                sprite.getOriginX(),
                sprite.getOriginY(),
                sprite.getWidth(),
                sprite.getHeight(),
                (float) 3, (float) 3, angle);
    }

    public Sprite getSprite(){
        return sprite;
    }
    @Override
    public void dispose() {
//        System.err.println("chopper dispose");
//        texture.dispose();
    }
}
