package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.spacechoppers.AssetManager;
import com.mygdx.spacechoppers.model.LaserModel;
import com.mygdx.spacechoppers.view.LaserView;

import java.util.ArrayList;
import java.util.HashMap;

public class LaserController {

    private final HashMap<LaserModel, LaserView> laserAndViews;
    private float dt;
    private float initialFireRate = 1;
    private float fireRate = initialFireRate;
    private float maxFireRate = 8;

    public LaserController() {
        laserAndViews = new HashMap<>();
    }

    public void fireLasers(float dt, Vector3 camPos, float chopperRotation, World world) {
        this.dt += dt;
        // Create new laser if certain time has passed
        if (this.dt > 1 / fireRate) {
            this.dt = 0;
            LaserView view = new LaserView();
            float xPosition = camPos.x - view.getTextureSize().x;
            float yPosition = camPos.y - view.getTextureSize().y;
            LaserModel laserModel = new LaserModel(new Vector3(xPosition, yPosition, 0), view.getTextureSize(), chopperRotation, chopperRotation, world);
            laserAndViews.put(laserModel, view);
            // Play laser sound here
            AssetManager.INSTANCE.playLaserSound();

        }
        // We want to move the laser anyways
        moveAllLasers(world);
    }

    private void moveAllLasers(World world) {
        ArrayList<LaserModel> lasersToDispose = new ArrayList<>();
        for (LaserModel laserModel : laserAndViews.keySet()) {
            laserModel.moveLaser();
            // Remove if outside screen

            if (laserModel.isLaserOutsideOfCamera()) {
                laserAndViews.get(laserModel).dispose();
                lasersToDispose.add(laserModel);
            }
        }
        disposeLasers(lasersToDispose, world);
    }

    private void disposeLasers(ArrayList<LaserModel> laserModels, World world) {
        for (LaserModel laserModel : laserModels) {
            laserAndViews.remove(laserModel);
            world.destroyBody(laserModel.getBody());
        }
    }

    public void draw(SpriteBatch sb) {
        for (LaserModel laserModel : laserAndViews.keySet()) {
            LaserView correspondingView = laserAndViews.get(laserModel);
            correspondingView.draw(sb, laserModel);
        }
    }

    public void rapidFire() {
        fireRate = maxFireRate;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                fireRate = initialFireRate;
            }
        }, 10);
    }
}
