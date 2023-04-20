package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.AssetManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidTextures implements Disposable {
    private final Texture allAsteroidsTexture;
    private final List<TextureRegion> asteroids;
    private final int numRows = 4;
    private final int numColumns = 4;
    private final Random random;

    public AsteroidTextures() {
        //this.allAsteroidsTexture = AssetManager.INSTANCE.getManager().get("asteroid-sheet.png", Texture.class);
        this.allAsteroidsTexture = AssetManager.INSTANCE.getAsteroidSheet();
        this.asteroids = new ArrayList(numRows * numColumns);
        this.random = new Random();
        initialize();
    }

    private void initialize() {
        int singleTextureWidth = allAsteroidsTexture.getWidth() / numColumns;
        int singleTextureHeight = allAsteroidsTexture.getHeight() / numRows;

        for(int row = 0; row < numRows; row++){
            for(int column = 0; column < numColumns; column++){
                TextureRegion texture = new TextureRegion(allAsteroidsTexture, column * singleTextureWidth, row * singleTextureHeight, singleTextureWidth, singleTextureHeight);
                asteroids.add(texture);
            }
        }
    }

    public TextureRegion getRandomAsteroidTexture(){
        int randomId = random.nextInt(numColumns * numRows);
        return asteroids.get(randomId);
    }

    @Override
    public void dispose() {
        for(TextureRegion tr : asteroids){
            tr.getTexture().dispose();
        }
    }
}
