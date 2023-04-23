package com.mygdx.spacechoppers.model;

import com.mygdx.spacechoppers.data.networking.Score;
import com.mygdx.spacechoppers.interfaces.IModel;
import com.mygdx.spacechoppers.networking.MessageReceiver;

import java.util.ArrayList;
import java.util.List;

public class LiveScoresModel implements IModel {

    private List<Score> scores = new ArrayList<>();
    private final MessageReceiver messageReceiver = MessageReceiver.getInstance();

    public List<Score> getScores() {
        return scores;
    }

    public void updateScores() {
        scores = messageReceiver.getLiveScores();
    }
}
