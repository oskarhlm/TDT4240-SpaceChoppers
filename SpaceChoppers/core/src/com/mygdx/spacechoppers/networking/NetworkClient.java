package com.mygdx.spacechoppers.networking;

import com.mygdx.spacechoppers.data.networking.Message;
import com.mygdx.spacechoppers.data.networking.MessageAction;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class NetworkClient {

    private static NetworkClient instance = null;
    private final NetworkHandler handler;
    private final CountDownLatch latch;

    private NetworkClient() throws URISyntaxException {
        latch = new CountDownLatch(1);
        URI websocketURI1 = new URI("ws://sc.hjelmtvedt.io:6968");
        URI websocketURI2 = new URI("ws://sc.holter.tech:6968");

        List<URI> uris = Arrays.asList(websocketURI1, websocketURI2);
        URI bestURI = null;
        long bestPingRTT = Long.MAX_VALUE;

        for (URI uri : uris) {
            long currentPingRTT = getPingRTT(uri);
            if (currentPingRTT < bestPingRTT) {
                bestPingRTT = currentPingRTT;
                bestURI = uri;
            }
        }

        if (bestURI == null) {
            throw new RuntimeException("No available URIs");
        }

        handler = new NetworkHandler(bestURI, latch);
        System.out.println("Connected to: " + bestURI);
        handler.connect();
    }



    public static NetworkClient getInstance() throws URISyntaxException {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    private long getPingRTT(URI uri) {
        try {
            InetAddress inetAddress = InetAddress.getByName(uri.getHost());
            long startTime = System.currentTimeMillis();
            if (inetAddress.isReachable(1000)) {
                long endTime = System.currentTimeMillis();
                return endTime - startTime;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Long.MAX_VALUE;
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
