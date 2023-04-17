package com.mygdx.spacechoppers.data.networking;

import com.google.gson.Gson;

public class MessageFactory {
    private String action;
    private String username;
    private int lobbyID;
    private int score;

    public static MessageFactory create() {
        return new MessageFactory();
    }

    public MessageFactory action(String action) {
        this.action = action;
        return this;
    }

    public MessageFactory username(String username) {
        this.username = username;
        return this;
    }

    public MessageFactory lobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
        return this;
    }

    public MessageFactory score(int score) {
        this.score = score;
        return this;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this.build());
    }

    public Message build() {
        return new Message(action.toString(), username, lobbyID, score);
    }
}

