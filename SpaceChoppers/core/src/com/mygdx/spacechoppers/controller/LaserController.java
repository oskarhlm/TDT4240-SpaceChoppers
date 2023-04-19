package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.AssetManager;
import com.mygdx.spacechoppers.model.Laser;
import com.mygdx.spacechoppers.view.LaserView;

import java.util.ArrayList;
import java.util.HashMap;

public class LaserController {

    private final HashMap<Laser, LaserView> laserAndViews;
    private float dt;


    public LaserController() {
        laserAndViews = new HashMap<>();
    }

    public void fireLasers(float dt, Vector3 chopperPos, float chopperRotation) {
        this.dt += dt;
        // Create new laser if certain time has passed
        if (this.dt > 1) {
            this.dt = 0;

            Laser laser = new Laser(new Vector3(chopperPos.x, chopperPos.y, 0), chopperRotation, chopperRotation);
            LaserView view = new LaserView();
            laserAndViews.put(laser, view);
            // Play laser sound here
            AssetManager.INSTANCE.playLaserSound();

        }
        // We want to move the laser anyways
        moveAllLasers();
    }

    private void moveAllLasers() {
        ArrayList<Laser> lasersToDispose = new ArrayList<>();
        for (Laser laser : laserAndViews.keySet()) {
            laser.moveLaser();
            // Remove if outside screen

            if (laser.isLaserOutsideOfCamera()) {
                laserAndViews.get(laser).dispose();
                lasersToDispose.add(laser);
            }
        }
        disposeLasers(lasersToDispose);
    }

    private void disposeLasers(ArrayList<Laser> lasers) {
        for (Laser laser : lasers) {
            laserAndViews.remove(laser);
        }
    }

    public void draw(SpriteBatch sb) {
        for (Laser laser : laserAndViews.keySet()) {
            LaserView correspondingView = laserAndViews.get(laser);
            correspondingView.draw(sb, laser.getPosition(), laser.getInitialRotation());
        }
    }
}
