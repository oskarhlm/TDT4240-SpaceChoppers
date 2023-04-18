package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.spacechoppers.data.networking.Score;
import com.mygdx.spacechoppers.networking.MessageReceiver;
import java.util.List;

public class LiveScoresModel {

    private Vector2 position;

    public LiveScoresModel(Vector2 position) {
        this.position = position;
    }

    public List<Score> getScores() {
        MessageReceiver messageReceiver = MessageReceiver.getInstance();
        return messageReceiver.getLiveScores();
    }

    public Vector2 getPosition() {
        return this.position;
    }
}
