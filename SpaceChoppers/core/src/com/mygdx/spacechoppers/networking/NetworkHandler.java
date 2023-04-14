package com.mygdx.spacechoppers.networking;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.util.concurrent.CountDownLatch;


public class NetworkHandler extends WebSocketClient {

    private boolean connected = false;
    private CountDownLatch latch;

    public NetworkHandler(java.net.URI serverUri, CountDownLatch latch) {
        super(serverUri);
        this.latch = latch;
    }

    public boolean isConnected() {
        return this.connected;
    }

    @java.lang.Override
    public void onOpen(ServerHandshake handshakedata) {
        this.connected = true;
        latch.countDown();
        System.out.println("--- WEBSOCKET OPENED");
    }

    @java.lang.Override
    public void onMessage(java.lang.String message) {
        System.out.println("--- RECEIVED MESSAGE BELOW");
        System.out.println(message);
    }

    @java.lang.Override
    public void onClose(int code, java.lang.String reason, boolean remote) {
        System.out.println("--- WEBSOCKET CLOSED");
    }

    @java.lang.Override
    public void onError(java.lang.Exception ex) {
        System.out.println("--- WEBSOCKET ERRORED");
    }
}
