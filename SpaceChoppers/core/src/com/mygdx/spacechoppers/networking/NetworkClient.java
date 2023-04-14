package com.mygdx.spacechoppers.networking;

import com.mygdx.spacechoppers.networking.messages.MessageAction;
import com.mygdx.spacechoppers.networking.messages.MessageFactory;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;


public class NetworkClient {

    private final NetworkHandler handler;
    private final CountDownLatch latch;
    private URI websocketURI;

    public NetworkClient() throws URISyntaxException {
        latch = new CountDownLatch(1);
        websocketURI = new URI("ws://192.168.50.92:6969");
        handler = new NetworkHandler(websocketURI, latch);
        handler.connect();
    }

    public void createLobby(String username) throws InterruptedException {
        String createLobby = MessageFactory.create()
                .action(MessageAction.CREATE_LOBBY.getAction())
                .username(username)
                .toJson();
        // wait for the WebSocket connection to be established
        latch.await();
        handler.send(createLobby);
    }

    public void joinLobby(int lobbyID, String username) throws InterruptedException {
        String joinLobby = MessageFactory.create()
                .action(MessageAction.JOIN_LOBBY.getAction())
                .username(username)
                .lobbyID(lobbyID)
                .toJson();
        latch.await();
        System.out.println(joinLobby);
        handler.send(joinLobby);
    }

    public void leaveLobby(int lobbyID, String username) throws InterruptedException {
        String leaveLobby = MessageFactory.create()
                .action(MessageAction.LEAVE_LOBBY.getAction())
                .username(username)
                .lobbyID(lobbyID)
                .toJson();
        latch.await();
        handler.send(leaveLobby);
        handler.close();
    }

    public void sendScore(int lobbyID, String username, int score) throws InterruptedException {
        String sendScore = MessageFactory.create()
                .action(MessageAction.SEND_SCORE.getAction())
                .username(username)
                .lobbyID(lobbyID)
                .score(score)
                .toJson();
        latch.await();
        handler.send(sendScore);
    }

    public void getScores(int lobbyID) throws InterruptedException {
        String getScores = MessageFactory.create()
                .action(MessageAction.GET_SCORES.getAction())
                .lobbyID(lobbyID)
                .toJson();

        latch.await();
        handler.send(getScores);
    }
}
