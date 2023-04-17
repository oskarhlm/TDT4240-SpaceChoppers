package com.mygdx.spacechoppers.data.networking;
import com.google.gson.Gson;

public class Message {
    private String action;
    private String username;
    private int lobbyID;
    private int score;

    public Message(String action, String username, int lobbyID, int score) {
        this.action = action;
        this.username = username;
        this.lobbyID = lobbyID;
        this.score = score;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    public String getAction() {
        return action;
    }

    public String getUsername() {
        return username;
    }
}

