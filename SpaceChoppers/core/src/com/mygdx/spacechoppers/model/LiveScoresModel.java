package com.mygdx.spacechoppers.model;

import com.mygdx.spacechoppers.data.networking.Score;
import com.mygdx.spacechoppers.networking.MessageReceiver;
import java.util.List;

public class LiveScoresModel {

    public LiveScoresModel() {
    }

    public List<Score> getScores() {
        MessageReceiver messageReceiver = MessageReceiver.getInstance();
        return messageReceiver.getLiveScores();
    }
}
