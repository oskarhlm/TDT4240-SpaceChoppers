package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.model.ExplosionModel;

import java.util.ArrayList;

public class ExplosionsController {

    private static ExplosionsController INSTANCE = null;
    private final ArrayList<ExplosionModel> explosionModelList;

    private ExplosionsController() {
        explosionModelList = new ArrayList<>();
    }

    public static ExplosionsController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExplosionsController();
        }
        return INSTANCE;
    }

    public void addExplosion(Vector3 position, float size) {
        ExplosionModel explosionModel = new ExplosionModel(position.x, position.y, size);
        explosionModelList.add(explosionModel);
    }

    public void cleanUpExplosions() {
        ArrayList<ExplosionModel> toRemove = new ArrayList<>();
        for (ExplosionModel explosionModel : explosionModelList) {
            if (explosionModel.getRemove()) {
                toRemove.add(explosionModel);
            }
        }
        explosionModelList.removeAll(toRemove);
    }

    public void drawExplosions(SpriteBatch sb) {
        for (ExplosionModel explosionModel : explosionModelList) {
            explosionModel.draw(sb);
        }
    }

    public void update(float dt) {
        for (ExplosionModel explosionModel : explosionModelList) {
            explosionModel.update(dt);
        }
        // Also clean up any explosions that are expired
        cleanUpExplosions();
    }
}
