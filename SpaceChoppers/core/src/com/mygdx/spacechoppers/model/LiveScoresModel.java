package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.spacechoppers.networking.ScoreHandler;

import java.util.HashMap;

public class LiveScoresModel {

    private Vector2 position;

    public LiveScoresModel(Vector2 position) {
        this.position = position;
    }

    public HashMap<String, Integer> getScores() {
        ScoreHandler scoreHandler = ScoreHandler.getInstance();
        return scoreHandler.getLiveScores();
    }

    public Vector2 getPosition() {
        return this.position;
    }
}
