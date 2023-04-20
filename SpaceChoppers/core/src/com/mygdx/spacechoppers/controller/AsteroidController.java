package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.model.AsteroidHelper;
import com.mygdx.spacechoppers.model.AsteroidModel;
import com.mygdx.spacechoppers.networking.NetworkClient;
import com.mygdx.spacechoppers.view.AsteroidView;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import kotlin.Pair;


public class AsteroidController {

    private static AsteroidController INSTANCE = null;
    private final HashMap<AsteroidModel, AsteroidView> modelAndViews;
    public Set<AsteroidModel> toDispose;
    private float dt;
    private World world;


    public static AsteroidController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AsteroidController();
        }
        return INSTANCE;
    }

    private AsteroidController() {
        modelAndViews = new HashMap<>();
        toDispose = new HashSet<>();
    }

    public void spawnAndMoveAsteroids(float dt, World world, OrthographicCamera cam) {
        this.world = world;
        this.dt += dt;

        // Create new asteroids if certain time has passed
        if (this.dt > 0.4) {
            // Reset timer
            this.dt = 0;

            // Create number of new asteroid views and models

            // Get size
            Vector2 size = AsteroidHelper.getSize();

            // Get position and velocity
            Pair<Vector3, Vector2> posAndVelocity = AsteroidHelper.getPositionAndVelocity(cam);

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
        moveAllAsteroids();
        disposeAsteroidList();
    }

    private void moveAllAsteroids() {
        for (AsteroidModel asteroid : modelAndViews.keySet()) {
            moveAsteroid(asteroid);

            // Check for disposable asteroids
            if (asteroid.isOutOfBounds()) {
                toDispose.add(asteroid);
            }
        }
    }

    private void moveAsteroid(AsteroidModel asteroidModel) {
        asteroidModel.updatePosition();
    }

    private void disposeAsteroidList() {
        for (AsteroidModel asteroid : toDispose) {
            world.destroyBody(asteroid.getBody());
            AsteroidView view = modelAndViews.remove(asteroid);
            view.dispose();
        }
        toDispose.clear();
    }


    public void draw(SpriteBatch sb) {
        for (AsteroidModel asteroid : modelAndViews.keySet()) {
            AsteroidView correspondingView = modelAndViews.get(asteroid);
            correspondingView.draw(sb, asteroid.getLocation(), asteroid.getBody());
        }
    }
}
