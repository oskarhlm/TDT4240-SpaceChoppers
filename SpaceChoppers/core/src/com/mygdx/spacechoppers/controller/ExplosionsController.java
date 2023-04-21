package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.model.ExplosionModel;
import com.mygdx.spacechoppers.view.ExplosionView;

import java.util.ArrayList;
import java.util.HashMap;

public class ExplosionsController {

    private static ExplosionsController INSTANCE = null;
    private final HashMap<ExplosionModel, ExplosionView> explosionList;

    private ExplosionsController() {
        explosionList = new HashMap<>();
    }

    public static ExplosionsController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExplosionsController();
        }
        return INSTANCE;
    }

    public void addExplosion(Vector3 position, float size) {
        // Create model
        ExplosionModel model = new ExplosionModel(position, size);

        // Create view
        ExplosionView view = new ExplosionView((int) size);

        // Add to list
        explosionList.put(model, view);
    }

    public void cleanUpExplosions() {
        ArrayList<ExplosionModel> toRemove = new ArrayList<>();
        for (ExplosionModel explosionModel : explosionList.keySet()) {
            if (explosionList.get(explosionModel).isAnimationDone()) {
                toRemove.add(explosionModel);
            }
        }

        // Now remove
        for (ExplosionModel explosion : toRemove) {
            ExplosionView view = explosionList.remove(explosion);
        }
        toRemove.clear();
    }

    public void drawExplosions(SpriteBatch sb) {
        for (ExplosionModel explosionModel : explosionList.keySet()) {
            // Draw in the correct view
            explosionList.get(explosionModel).draw(sb, explosionModel.getPosition());
        }
    }

    public void update(float dt) {
        for (ExplosionModel explosionModel : explosionList.keySet()) {
            // Update the view
            explosionList.get(explosionModel).update(dt);
        }
        // Also clean up any explosions that are expired
        cleanUpExplosions();
    }
}
