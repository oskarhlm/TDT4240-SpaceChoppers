package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.model.AsteroidModel;
import com.mygdx.spacechoppers.model.AsteroidTextures;

public class AsteroidView implements Disposable {

    private final TextureRegion texture;
    private final Sprite sprite;

    public AsteroidView(TextureRegion texture){
        this.texture = texture;
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch sb, Vector3 location, float width, float height){
        sb.draw(sprite, location.x, location.y, width, height);
    }

    @Override
    public void dispose() {
        //System.out.println("dispose asteroids");
        //texture.getTexture().dispose();
    }
}
