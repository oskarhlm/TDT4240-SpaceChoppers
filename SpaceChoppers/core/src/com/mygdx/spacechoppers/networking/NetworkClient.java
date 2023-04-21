package com.mygdx.spacechoppers.networking;

import com.mygdx.spacechoppers.data.networking.Message;
import com.mygdx.spacechoppers.data.networking.MessageAction;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;


public class NetworkClient {

    private static NetworkClient instance = null;
    private final NetworkHandler handler;
    private final CountDownLatch latch;
    private URI websocketURI;

    private NetworkClient() throws URISyntaxException {
        latch = new CountDownLatch(1);
        websocketURI = new URI("ws://sc.hjelmtvedt.io:6968");
        handler = new NetworkHandler(websocketURI, latch);
        handler.connect();
    }

    public static NetworkClient getInstance() throws URISyntaxException {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    public void createLobby(String username) throws InterruptedException {
        String createLobby = new Message.Builder()
                .action(MessageAction.CREATE_LOBBY)
                .username(username)
                .toJsonString();

        // wait for the WebSocket connection to be established
        latch.await();
        handler.send(createLobby);
    }

    public void joinLobby(int lobbyID, String username) throws InterruptedException {
        String joinLobby = new Message.Builder()
                .action(MessageAction.JOIN_LOBBY)
                .username(username)
                .lobbyID(lobbyID)
                .toJsonString();

        // wait for the WebSocket connection to be established
        latch.await();
        handler.send(joinLobby);
    }

    public void leaveLobby(int lobbyID, String username) throws InterruptedException {
        String leaveLobby = new Message.Builder()
                .action(MessageAction.LEAVE_LOBBY)
                .username(username)
                .lobbyID(lobbyID)
                .toJsonString();

        // wait for the WebSocket connection to be established
        latch.await();
        handler.send(leaveLobby);
        //handler.close();
    }

    public void sendScore(int lobbyID, String username, int score) throws InterruptedException {
        String sendScore = new Message.Builder()
                .action(MessageAction.SEND_SCORE)
                .username(username)
                .lobbyID(lobbyID)
                .score(score)
                .toJsonString();

        // wait for the WebSocket connection to be established
        latch.await();
        handler.send(sendScore);
    }

    public void getHighscores() throws InterruptedException {
        String getHighscores = new Message.Builder()
                .action(MessageAction.GET_HIGHSCORES)
                .toJsonString();

        // wait for the WebSocket connection to be established
        latch.await();
        handler.send(getHighscores);
    }
}
