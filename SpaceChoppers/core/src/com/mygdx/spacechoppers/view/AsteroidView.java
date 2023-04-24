package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.utils.AssetManager;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.interfaces.IView;
import com.mygdx.spacechoppers.model.AsteroidModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidView implements IView, Disposable {

    private final Random random;
    private final TextureRegion currentTexture;
    private final Vector2 textureSize;
    private final Texture allAsteroidsTexture;
    private final List<TextureRegion> asteroids;
    private final int NUM_ROWS = 4;
    private final int NUM_COLS = 4;

    private final Sprite sprite;

    public AsteroidView(Vector2 textureSize) {
        this.random = new Random();
        this.allAsteroidsTexture = AssetManager.INSTANCE.getManager()
                .get("asteroid-sheet.png", Texture.class);
        this.asteroids = new ArrayList(NUM_ROWS * NUM_COLS);
        initialize();

        this.textureSize = textureSize;

        // Generate random texture and sprite
        this.currentTexture = getRandomAsteroidTexture();

        this.sprite = new Sprite(currentTexture);
    }

    private void initialize() {
        int singleTextureWidth = allAsteroidsTexture.getWidth() / NUM_COLS;
        int singleTextureHeight = allAsteroidsTexture.getHeight() / NUM_ROWS;

        for(int row = 0; row < NUM_ROWS; row++){
            for(int column = 0; column < NUM_COLS; column++){
                TextureRegion texture = new TextureRegion(allAsteroidsTexture, column * singleTextureWidth, row * singleTextureHeight, singleTextureWidth, singleTextureHeight);
                asteroids.add(texture);
            }
        }
    }

    public TextureRegion getRandomAsteroidTexture() {
        int randomId = random.nextInt(NUM_COLS * NUM_ROWS);
        TextureRegion region =  asteroids.get(randomId);
        return region;
    }

    public void draw(@NotNull SpriteBatch sb, AsteroidModel asteroidModel) {
        Vector3 position = asteroidModel.getLocation();
        Body asteroidBody = asteroidModel.getBody();
        sb.draw(sprite, position.x, position.y,
                sprite.getOriginX(),
                sprite.getOriginY(),
                textureSize.x,
                textureSize.y,
                (float) 1, (float) 1, 0);
        drawBody(position, asteroidBody);
    }

    private void drawBody(Vector3 position, Body asteroidBody) {
        // Adjust for meters
        float adjustedX = (position.x + textureSize.x / 2f) * Const.PIXELS_TO_METERS;
        float adjustedY = (position.y + textureSize.y / 2f) * Const.PIXELS_TO_METERS;
        asteroidBody.setTransform(adjustedX, adjustedY, 0);
    }



    @Override
    public void dispose() {
//        for (TextureRegion tr : asteroids) {
//            if (!tr.equals(currentTexture)) {
//                tr.getTexture().dispose();
//            }
//        }
    }


}
