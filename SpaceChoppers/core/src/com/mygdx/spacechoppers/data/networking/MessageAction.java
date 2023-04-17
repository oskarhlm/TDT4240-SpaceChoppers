package com.mygdx.spacechoppers.data.networking;

public enum MessageAction {

    CREATE_LOBBY("CREATE_LOBBY"),
    JOIN_LOBBY("JOIN_LOBBY"),
    LEAVE_LOBBY("LEAVE_LOBBY"),
    SEND_SCORE("SEND_SCORE"),
    RECEIVE_SCORES("RECEIVE_SCORES"),

    GET_HIGHSCORES("GET_HIGHSCORES"),
    RECEIVE_HIGHSCORES("RECEIVE_HIGHSCORES");

    private final String action;

    MessageAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
