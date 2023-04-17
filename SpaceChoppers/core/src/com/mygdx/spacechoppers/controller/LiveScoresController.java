package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.spacechoppers.model.LiveScoresModel;
import com.mygdx.spacechoppers.view.LiveScoresView;

import java.util.HashMap;

public class LiveScoresController {

    private LiveScoresModel model;
    private LiveScoresView view;

    public LiveScoresController() {

        Vector2 position = new Vector2(Gdx.graphics.getWidth() - 400, Gdx.graphics.getHeight() - 100);
        this.model = new LiveScoresModel(position);
        this.view = new LiveScoresView();
    }

    public Vector2 getPosition() {
        return model.getPosition();
    }

    public void renderScores(SpriteBatch sb) {
        HashMap<String, Integer> scoreList = model.getScores();
        view.draw(sb, scoreList, model.getPosition());
    }

}
