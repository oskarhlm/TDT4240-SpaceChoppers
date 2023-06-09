package com.mygdx.spacechoppers.networking;

import com.mygdx.spacechoppers.data.networking.Score;
import com.mygdx.spacechoppers.utils.Preferences;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


public class MessageReceiver {
    private static MessageReceiver instance = null;
    private List<Score> liveScores;
    private List<Score> highScores;
    private int playerScore = 0;

    private int lobbyID;
    private MessageReceiver() {
        liveScores = new ArrayList<>();
        highScores = new ArrayList<>();
        lobbyID = -1;
    }

    public static synchronized MessageReceiver getInstance() {
        if (instance == null) {
            instance = new MessageReceiver();
        }
        return instance;
    }

    public void updateLiveScores(List<Score> updatedLiveScores) {
        this.liveScores = updatedLiveScores;
        for (Score score : updatedLiveScores) {
            if (score.getUsername().equals(Preferences.INSTANCE.getUsername())) {
                playerScore = score.getScore();
            }
        }
    }

    public void updateHighscores(List<Score> updatedHighscores) {
        this.highScores = updatedHighscores;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }

    public List<Score> getLiveScores() {

        return this.liveScores;
    }

    public List<Score> getHighScores() {
        return this.highScores;
    }

    public int getPlayerScore() {
        return this.playerScore;
    }

    public int getLobbyID() {
        return this.lobbyID;
    }
}
