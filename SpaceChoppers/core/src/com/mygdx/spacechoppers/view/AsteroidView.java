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

    public AsteroidView(AsteroidTextures textures){
        this.texture = textures.getRandomAsteroidTexture();
        this.sprite = new Sprite(texture);
        this.sprite.setSize(this.sprite.getWidth() * 0.25f, this.sprite.getHeight() * 0.04f);
        this.sprite.setOriginCenter();
    }

    public void draw(SpriteBatch sb, Vector3 location) {
        sprite.setPosition(location.x, location.y);
        sprite.draw(sb);
    }



    @Override
    public void dispose() {
        //texture.dispose();
    }
}
