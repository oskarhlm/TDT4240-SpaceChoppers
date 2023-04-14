package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidTextures {
    private Texture allAsteroidsTexture;
    private List<TextureRegion> asteroids;
    private final int numRows = 4;
    private final int numColumns = 4;
    private Random random;

    public AsteroidTextures() {
        this.allAsteroidsTexture = new Texture("asteroid-sheet.png");
        this.asteroids = new ArrayList<>(numRows * numColumns);
        this.random = new Random();
        initalize();
    }

    private void initalize() {
        int textureWidth = allAsteroidsTexture.getWidth() / numColumns;
        int textureHeight = allAsteroidsTexture.getHeight() / numRows;

        for(int row = 0; row < numRows; row++){
            for(int column = 0; column < numColumns; column++){
                TextureRegion texture = new TextureRegion(allAsteroidsTexture, column * textureWidth, row * textureHeight, textureWidth, textureHeight);
                asteroids.add(texture);
            }
        }
    }

    public TextureRegion getRandomAsteroidTexture(){
        int numAsteroids = numColumns * numRows;
        int randomId = random.nextInt(numAsteroids);
        return asteroids.get(randomId);
    }


}
