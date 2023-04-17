package com.mygdx.spacechoppers.networking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.spacechoppers.data.networking.Message;
import com.mygdx.spacechoppers.data.networking.MessageAction;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.concurrent.CountDownLatch;


public class NetworkHandler extends WebSocketClient {
    private CountDownLatch latch;



    public NetworkHandler(URI serverUri, CountDownLatch latch) {
        super(serverUri);
        this.latch = latch;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        latch.countDown();
        System.out.println("--- WEBSOCKET OPENED");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("--- RECEIVED MESSAGE BELOW");
        System.out.println(message);
        // Parse message
        MessageReceiver messageReceiver = MessageReceiver.getInstance();
        ObjectMapper mapper = new ObjectMapper();
        Message receivedMsg;
        try {
            receivedMsg = mapper.readValue(message, Message.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (receivedMsg.getAction() == MessageAction.RECEIVE_SCORES) {
            messageReceiver.updateLiveScores(receivedMsg.getLiveScores());
        } else if (receivedMsg.getAction() == MessageAction.RECEIVE_HIGHSCORES) {
            messageReceiver.updateHighscores(receivedMsg.getHighScores());
        } else if (receivedMsg.getAction() == MessageAction.CREATE_LOBBY) {
            messageReceiver.setLobbyID(receivedMsg.getLobbyID());
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("--- WEBSOCKET CLOSED");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("--- WEBSOCKET ERRORED");
        System.out.println(ex);
    }
}
