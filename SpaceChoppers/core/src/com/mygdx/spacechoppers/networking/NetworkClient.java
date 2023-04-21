package com.mygdx.spacechoppers.networking;

import com.mygdx.spacechoppers.data.networking.Message;
import com.mygdx.spacechoppers.data.networking.MessageAction;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class NetworkClient {

    private static NetworkClient instance = null;
    private final NetworkHandler handler;
    private final CountDownLatch latch;
    private URI websocketURI;

    private NetworkClient() throws URISyntaxException, InterruptedException {
        latch = new CountDownLatch(1);
        websocketURI = getBestServerURI();
        handler = new NetworkHandler(websocketURI, latch);
        System.out.println("Connected to: " + websocketURI);
        handler.connect();
    }

    public static NetworkClient getInstance() throws URISyntaxException, InterruptedException {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    // Method to measure latency and return the best server URI
    private URI getBestServerURI() throws URISyntaxException, InterruptedException {
        List<URI> serverURIs = Arrays.asList(
                new URI("ws://sc.holter.tech:6968"),
                new URI("ws://sc.hjelmtvedt.io:6968")
        );

        URI bestServerURI = null;
        long bestLatency = Long.MAX_VALUE;

        for (URI serverURI : serverURIs) {
            long latency = getServerLatency(serverURI);

            if (latency < bestLatency) {
                bestServerURI = serverURI;
                bestLatency = latency;
            }
        }

        return bestServerURI;
    }

    private long getServerLatency(URI serverURI) throws InterruptedException {
        NetworkHandler tempHandler = new NetworkHandler(serverURI, new CountDownLatch(1));

        long startTime = System.nanoTime();
        boolean isConnected = tempHandler.connectBlocking();

        if (!isConnected) {
            return Long.MAX_VALUE;
        }
        long endTime = System.nanoTime();

        tempHandler.close();

        return TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
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
