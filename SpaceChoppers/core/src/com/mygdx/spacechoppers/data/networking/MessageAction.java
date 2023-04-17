package com.mygdx.spacechoppers.data.networking;

public enum MessageAction {

    CREATE_LOBBY("createLobby"),
    JOIN_LOBBY("joinLobby"),
    LEAVE_LOBBY("leaveLobby"),
    SEND_SCORE("sendScore"),
    GET_SCORES("getScores");

    private final String action;

    MessageAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
