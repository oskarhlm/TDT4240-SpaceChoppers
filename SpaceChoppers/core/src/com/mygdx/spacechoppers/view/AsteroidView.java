package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.AsteroidModel;
import com.mygdx.spacechoppers.model.AsteroidTextures;

public class AsteroidView implements Disposable {

    private AsteroidModel model;
    private Texture texture;
    private Sprite sprite;

    public AsteroidView(AsteroidModel model, AsteroidTextures textures){
        this.model = model;
        this.texture = textures.getRandomAsteroidTexture().getTexture();
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch sb){
        sb.draw(sprite, model.getLocation().x, model.getLocation().y );
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
