package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.SpaceChoppersGame;

import java.util.Random;

import kotlin.Pair;

public class AsteroidHelper {

    private static final Random random = new Random();
    private static final int MAP_WIDTH = SpaceChoppersGame.Companion.getMapWidth() + 1000;
    private static final int MAP_HEIGHT = SpaceChoppersGame.Companion.getMapHeight() + 1000;

    public static Pair<Vector3, Vector2> getPositionAndVelocity() {
        // Create position
        Vector3 position = new Vector3(0,0,0);
        // Create velocity
        float velocityX = random.nextInt(10);
        float velocityY = random.nextInt(10);
        Vector2 velocity = new Vector2(0,0);

        int direction  = random.nextInt(8);
        System.out.println("--- DIRECTION ---");
        System.out.println(direction);

        switch (direction){

            case 0: // LEFT-BOTTOM
                position.add(-1000, random.nextInt(MAP_HEIGHT / 2), 0);
                velocity.add(velocityX, velocityY);
                break;
            case 1: // LEFT-TOP
                position.add(-1000, random.nextInt(MAP_HEIGHT / 2) + MAP_HEIGHT / 2, 0);
                velocity.add(velocityX, -velocityY);
                break;
            case 2: // TOP-LEFT
                position.add(random.nextInt(MAP_WIDTH/2), MAP_HEIGHT, 0);
                velocity.add(velocityX, -velocityY);
                break;
            case 3: // TOP-RIGHT
                position.add(random.nextInt(MAP_WIDTH / 2) + MAP_WIDTH / 2, MAP_HEIGHT, 0);
                velocity.add(-(velocityX), -(velocityY));
                break;
            case 4: // RIGHT-TOP
                position.add(MAP_WIDTH, random.nextInt(MAP_HEIGHT / 2) + MAP_HEIGHT / 2, 0);
                velocity.add(-velocityX, -velocityY);
            case 5: // RIGHT-BOTTOM
                position.add(MAP_WIDTH, random.nextInt(MAP_HEIGHT / 2), 0);
                velocity.add(-velocityX, velocityY);
                break;
            case 6: // BOTTOM-RIGHT
                position.add(random.nextInt(MAP_WIDTH / 2) + MAP_WIDTH / 2, 0, 0);
                velocity.add(-velocityX, velocityY);
                break;
            case 7: // BOTTOM-LEFT
                position.add(random.nextInt(MAP_WIDTH /2), -1000, 0);
                velocity.add(velocityX, velocityY);
        }
        return new Pair<>(position, velocity);
    }

    public static Vector2 getSize() {
        int size = random.nextInt(200) + 125;
        return new Vector2(size, size);
    }
}
