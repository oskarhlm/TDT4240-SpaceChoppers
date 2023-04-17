package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.model.Asteroid;
import com.mygdx.spacechoppers.model.AsteroidTextures;

public class AsteroidView implements Disposable {
    private final Asteroid model;
    private final TextureRegion texture;
    private final Sprite sprite;

    public AsteroidView(Asteroid model, AsteroidTextures textures){
        this.model = model;
        this.texture = textures.getRandomAsteroidTexture();
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch sb){
        sb.draw(sprite, model.getLocation().x, model.getLocation().y);
    }

    @Override
    public void dispose() {
        //texture.dispose();
    }
}
