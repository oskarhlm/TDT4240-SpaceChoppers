package com.mygdx.spacechoppers.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.controller.AsteroidController;
import com.mygdx.spacechoppers.model.AsteroidConfiguration;
import com.mygdx.spacechoppers.model.AsteroidTextures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class AsteroidFactory {

    private SpriteBatch sb;

    private AsteroidTextures asteroidTextures;

    private Random rand;

    private int speedMultiplier;

    private int speed;

    public AsteroidFactory(SpriteBatch sb, AsteroidTextures asteroidTextures){
        this.sb = sb;
        this.asteroidTextures = asteroidTextures;
        rand = new Random();
        speed = 3;
        speedMultiplier = 1;

    }

    public AsteroidController create(){
        return new AsteroidController(sb, generateRandomConfiguration());
    }

    public AsteroidConfiguration generateRandomConfiguration(){
        TextureRegion texture = asteroidTextures.getRandomAsteroidTexture();
        int HP = texture.getRegionHeight() + texture.getRegionWidth();

        int height = SpaceChoppersGame.Companion.getHeight();
        int width = SpaceChoppersGame.Companion.getWidth();

        Vector3 location = new Vector3();
        Vector2 direction = new Vector2();

        switch (rand.nextInt(8)){
            case 0:
                location.add(0, rand.nextInt(height / 2), 0);
                direction.add(rand.nextInt(speed) * speedMultiplier, rand.nextInt(speed) * speedMultiplier);
                break;
            case 1:
                location.add(0, rand.nextInt(height / 2) + height, 0);
                direction.add(rand.nextInt(speed) * speedMultiplier, -rand.nextInt(speed) * speedMultiplier);
                break;
            case 2:
                location.add(rand.nextInt(width/2), height, 0);
                direction.add(rand.nextInt(speed) * speedMultiplier, -rand.nextInt(speed) * speedMultiplier);
                break;
            case 3:
                location.add(rand.nextInt(width / 2) + width, height, 0);
                direction.add(-rand.nextInt(speed) * speedMultiplier, -rand.nextInt(speed) * speedMultiplier);
                break;
            case 4:
                location.add(width, rand.nextInt(height / 2) + height, 0);
                direction.add(-rand.nextInt(speed) * speedMultiplier, -rand.nextInt(speed) * speedMultiplier);
            case 5:
                location.add(width, rand.nextInt(height / 2), 0);
                direction.add(-rand.nextInt(speed) * speedMultiplier, rand.nextInt(speed) * speedMultiplier);
                break;
            case 6:
                location.add(rand.nextInt(width / 2) + width, 0, 0);
                direction.add(-rand.nextInt(speed) * speedMultiplier, rand.nextInt(speed) * speedMultiplier);
                break;
            case 7:
                location.add(rand.nextInt(width /2), 0, 0);
                direction.add(rand.nextInt(speed) * speedMultiplier, rand.nextInt(speed) * speedMultiplier);

        }

        return new AsteroidConfiguration(texture, HP, location, direction);
    }


}
