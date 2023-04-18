package com.mygdx.spacechoppers.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.controller.AsteroidController;
import com.mygdx.spacechoppers.model.AsteroidConfiguration;
import com.mygdx.spacechoppers.model.AsteroidTextures;

import java.util.Random;

public class AsteroidFactory {

    private final SpriteBatch sb;
    private final AsteroidTextures asteroidTextures;
    private final Random rand;
    private final int speedMultiplier;
    private final int velocityX;
    private final int velocityY;

    public AsteroidFactory(SpriteBatch sb, AsteroidTextures asteroidTextures){
        this.sb = sb;
        this.asteroidTextures = asteroidTextures;
        this.rand = new Random();
        this.velocityX = rand.nextInt(10);
        this.velocityY = rand.nextInt(10);
        this.speedMultiplier = 1;
    }

    public AsteroidController create(){
        AsteroidConfiguration config = generateRandomConfiguration();
        return new AsteroidController(sb, config);
    }

    public AsteroidConfiguration generateRandomConfiguration(){
        // util
        int height = SpaceChoppersGame.Companion.getHeight();
        int width = SpaceChoppersGame.Companion.getWidth();
        int changeX = velocityX * speedMultiplier;
        int changeY = velocityY * speedMultiplier;

        // Data
        int size = rand.nextInt(200) + 125;  // random number between 125 and 250
        TextureRegion texture = asteroidTextures.getRandomAsteroidTexture();
        int hitPoints = size / 10;
        Vector3 location = new Vector3();
        Vector2 direction = new Vector2();

        int n = rand.nextInt(8);
        switch (n){
            case 0: // LEFT-BOTTOM
                location.add(0, rand.nextInt(height / 2), 0);
                direction.add(changeX, changeY);
                break;
            case 1: // LEFT-TOP
                location.add(0, rand.nextInt(height / 2) + height / 2, 0);
                direction.add(changeX, -changeY);
                break;
            case 2: // TOP-LEFT
                location.add(rand.nextInt(width/2), height, 0);
                direction.add(changeX, -changeY);
                break;
            case 3: // TOP-RIGHT
                location.add(rand.nextInt(width / 2) + width / 2, height, 0);
                direction.add(-(changeX), -(changeY));
                break;
            case 4: // RIGHT-TOP
                location.add(width, rand.nextInt(height / 2) + height / 2, 0);
                direction.add(-changeX, -changeY);
            case 5: // RIGHT-BOTTOM
                location.add(width, rand.nextInt(height / 2), 0);
                direction.add(-changeX, changeY);
                break;
            case 6: // BOTTOM-RIGHT
                location.add(rand.nextInt(width / 2) + width / 2, 0, 0);
                direction.add(-changeX, changeY);
                break;
            case 7: // BOTTOM-LEFT
                location.add(rand.nextInt(width /2), 0, 0);
                direction.add(changeX, changeY);
        }

        return new AsteroidConfiguration(texture, hitPoints, location, direction, size);
    }


}