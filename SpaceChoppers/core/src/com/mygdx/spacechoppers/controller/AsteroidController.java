package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.model.AsteroidHelper;
import com.mygdx.spacechoppers.model.AsteroidModel;
import com.mygdx.spacechoppers.view.AsteroidView;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.Pair;


public class AsteroidController {

    private final HashMap<AsteroidModel, AsteroidView> modelAndViews;
    private float dt;
    private final int NUM_ASTEROIDS_TO_SPAWN = 1;

    public AsteroidController() {
        modelAndViews = new HashMap<>();
    }

    public void spawnAndMoveAsteroids(float dt, World world) {
        this.dt += dt;

        // Create new asteroids if certain time has passed
        if (this.dt > 0.1) {
            // Reset timer
            this.dt = 0;

            // Create number of new asteroid views and models

            // Get size
            Vector2 size = AsteroidHelper.getSize();

            // Get position and velocity
            Pair<Vector3, Vector2> posAndVelocity = AsteroidHelper.getPositionAndVelocity();

            // Get initial spawning position
            Vector3 spawnPosition = posAndVelocity.component1();

            // Get velocity
            Vector2 velocity = posAndVelocity.component2();

            // Create model of the asteroid
            AsteroidModel model = new AsteroidModel(1, spawnPosition, velocity, size, world);

            // Create view for asteroid
            AsteroidView view = new AsteroidView(size);

            // Add both to list
            modelAndViews.put(model, view);

        }
        // Move all asteroids
        moveAllAsteroids(world);
    }

    private void moveAllAsteroids(World world) {
        ArrayList<AsteroidModel> asteroidsToDispose = new ArrayList<>();
        for (AsteroidModel asteroid : modelAndViews.keySet()) {
            moveAsteroid(asteroid);

            // Remove if outside screen
            if (asteroid.isOutOfBounds()) {
                modelAndViews.get(asteroid).dispose();
                asteroidsToDispose.add(asteroid);
            }
        }
        disposeAsteroids(asteroidsToDispose, world);
    }

    private void moveAsteroid(AsteroidModel asteroidModel) {
        asteroidModel.updatePosition();
    }

    private void disposeAsteroids(ArrayList<AsteroidModel> asteroidModels, World world) {
        for (AsteroidModel asteroid : asteroidModels) {
            AsteroidView view = modelAndViews.remove(asteroid);
            view.dispose();
            world.destroyBody(asteroid.getBody());
        }
    }

    public void draw(SpriteBatch sb) {
        for (AsteroidModel asteroid : modelAndViews.keySet()) {
            AsteroidView correspondingView = modelAndViews.get(asteroid);
            correspondingView.draw(sb, asteroid.getLocation(), asteroid.getBody());
        }
    }
}
