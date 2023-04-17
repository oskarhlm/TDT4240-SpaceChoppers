package com.mygdx.spacechoppers.data.networking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Score {

    @JsonProperty
    private String username;

    @JsonProperty
    private int score;


    public Score() {}

    public Score(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return this.username;
    }

    public int getScore() {
        return this.score;
    }

}
