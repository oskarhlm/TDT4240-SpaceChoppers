package com.mygdx.spacechoppers.data;

import com.mygdx.spacechoppers.networking.NetworkClient;
import com.mygdx.spacechoppers.networking.ScoreHandler;

import java.net.URISyntaxException;

public class Main {

    public static  void main(String[] args) throws URISyntaxException, InterruptedException {
        int lobbyID = 9669;

        NetworkClient oskar = new NetworkClient();
        NetworkClient lars = new NetworkClient();
//        oskar.joinLobby(lobbyID, "oskar");
//        lars.joinLobby(lobbyID, "lars");
//
        oskar.sendScore(lobbyID, "oskar", 69);
        lars.sendScore(lobbyID, "lars", 420);




//        String jsonString = "[{\"vetle\": 10}, {\"oskar\": 20}, {\"lars\": 30}]";
//
//        Scores s = new Scores(jsonString);
//        System.out.println(s);
//        System.out.println(s.getScoreForUsername("vetle"));
//        System.out.println(s.getScoreForUsername("oskar"));
//        System.out.println(s.getScoreForUsername("lars"));

    }
}
