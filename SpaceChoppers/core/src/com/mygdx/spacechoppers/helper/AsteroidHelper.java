package com.mygdx.spacechoppers.helper;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import java.util.Random;
import kotlin.Pair;

public class AsteroidHelper {

    private static final Random random = new Random();
    private static final int MAP_WIDTH = SpaceChoppersGame.Companion.getMapWidth();
    private static final int MAP_HEIGHT = SpaceChoppersGame.Companion.getMapHeight();
    private static final int MAP_OFFSET = 1500;
    private static final int CAMERA_OFFSET = 300;

    public static Pair<Vector3, Vector2> getPositionAndVelocity(OrthographicCamera cam) {

        // Create position
        Vector3 position = new Vector3(0,0,0);
        // Create velocity
        float velocityX = random.nextInt( 10);
        float velocityY = random.nextInt(10);
        Vector2 velocity = new Vector2(0,0);

        float randomVelocity = random.nextInt(21) - 10;

        int direction  = random.nextInt(4);

        switch (direction) {
            // BOTTOM
            case 0:
                // x = {camX - viewPortwidth, camX + viewportWidth}
                float lowerBound = cam.position.x - cam.viewportWidth;
                float upperBound = cam.position.x + cam.viewportWidth;
                float xPosition = random.nextInt((int) (upperBound-lowerBound)) + lowerBound;
                float yPosition = cam.position.y - cam.viewportWidth - CAMERA_OFFSET;
                position.add(xPosition, yPosition, 0);
                velocity.add(randomVelocity, velocityY);
                break;

            // LEFT
            case 1:
                lowerBound = cam.position.y - cam.viewportHeight;
                upperBound = cam.position.y + cam.viewportHeight;
                xPosition = cam.position.x - cam.viewportWidth - CAMERA_OFFSET;
                yPosition = random.nextInt((int) (upperBound-lowerBound)) + lowerBound;

                position.add(xPosition, yPosition, 0);
                velocity.add(randomVelocity, velocityY);
                break;

            // TOP
            case 2:
                lowerBound = cam.position.x - cam.viewportWidth;
                upperBound = cam.position.x + cam.viewportWidth;
                xPosition = random.nextInt((int) (upperBound-lowerBound)) + lowerBound;
                yPosition = cam.position.y + cam.viewportWidth + CAMERA_OFFSET;
                position.add(xPosition, yPosition, 0);
                velocity.add(randomVelocity, -velocityY);
                break;

            // RIGHT
            case 3:
                lowerBound = cam.position.y - cam.viewportHeight;
                upperBound = cam.position.y + cam.viewportHeight;
                xPosition = cam.position.x + cam.viewportWidth + CAMERA_OFFSET;
                yPosition = random.nextInt((int) (upperBound-lowerBound)) + lowerBound;

                position.add(xPosition, yPosition, 0);
                velocity.add(-velocityX, randomVelocity);
                break;

        }
        return new Pair<>(position, velocity);
    }

    public static Vector2 getSize() {
        int size = random.nextInt(200) + 125;
        return new Vector2(size, size);
    }
}
