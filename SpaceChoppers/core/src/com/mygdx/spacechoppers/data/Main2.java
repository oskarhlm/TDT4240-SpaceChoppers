package com.mygdx.spacechoppers.data;

import com.mygdx.spacechoppers.networking.NetworkClient;
import com.mygdx.spacechoppers.networking.ScoreHandler;

import java.net.URISyntaxException;

public class Main2 {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        NetworkClient vetle = new NetworkClient();
        vetle.createLobby("vetle");

    }

}
