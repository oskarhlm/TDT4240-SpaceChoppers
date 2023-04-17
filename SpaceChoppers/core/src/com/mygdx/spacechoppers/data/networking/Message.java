package com.mygdx.spacechoppers.data.networking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Message {

    @JsonProperty("action")
    private MessageAction action;

    @JsonProperty
    private String username;

    @JsonProperty
    private int lobbyID;

    @JsonProperty
    private int score;

    @JsonProperty
    private boolean success;

    @JsonProperty("scores")
    private List<Score> liveScores;

    @JsonProperty("highscores")
    private List<Score> highScores;

    public Message() {

    }

    private Message(Builder builder) {
        this.action = builder.action;
        this.username = builder.username;
        this.lobbyID = builder.lobbyID;
        this.score = builder.score;
        this.success = builder.success;
        this.highScores = builder.highScores;
        this.liveScores = builder.liveScores;
    }

    public static class Builder {
        private MessageAction action;
        private String username;
        private int lobbyID;
        private int score;
        private boolean success;
        private List<Score> liveScores;

        private List<Score> highScores;

        public Builder action(MessageAction action) {
            this.action = action;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder lobbyID(int lobbyID) {
            this.lobbyID = lobbyID;
            return this;
        }

        public Builder score(int score) {
            this.score = score;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder liveScores(List<Score> liveScores) {
            this.liveScores = liveScores;
            return this;
        }

        public Builder highScores(List<Score> highScores) {
            this.highScores = highScores;
            return this;
        }

        public Message build() {
            return new Message(this);
        }

        public String toJsonString() {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(build());
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error serializing Message to JSON", e);
            }
        }
    }

    public MessageAction getAction() {
        return action;
    }

    public String getUsername() {
        return username;
    }

    public int getLobbyID() {
        return lobbyID;
    }

    public int getScore() {
        return score;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Score> getLiveScores() {
        return liveScores;
    }

    public List<Score> getHighScores() {
        return highScores;
    }


}
