package com.mygdx.spacechoppers.networking;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mygdx.spacechoppers.gamestates.menu.utils.Preferences;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ScoreHandler {
    private static ScoreHandler instance = null;
    private HashMap<String, Integer> liveScores;
    private HashMap<String, Integer> highScores;

    private ScoreHandler() {
        liveScores = new HashMap<>();
        highScores = new HashMap<>();


        liveScores.put("oskar", 69);
        liveScores.put("vetle", 14);
        liveScores.put("lars", 55);
        liveScores.put("mathias", 21);
        liveScores.put("arild", 231);
        liveScores.put("andré", 544);
        liveScores.put("shurabs", 78);
    }

    public static synchronized ScoreHandler getInstance() {
        if (instance == null) {
            instance = new ScoreHandler();
        }
        return instance;
    }

    public void updateLiveScores(String jsonString) {
        Gson gson = new Gson();

        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
        HashMap<String, String> map = gson.fromJson(jsonString, type);

        String payloadString = map.get("payload");
        Type payloadType = new TypeToken<HashMap<String, Integer>>() {}.getType();
        HashMap<String, Integer> payload = gson.fromJson(payloadString, payloadType);

        this.liveScores = (HashMap<String, Integer>) payload;
    }

    public HashMap<String, Integer> getLiveScores() {

        return sortLiveScores(this.liveScores);
    }

    /**
     * Sort the scores of the players from high to low and puts the current user first. Capped to 5 scores including the current user.
     * @param liveScores
     * @return Sorted HashMap of scores
     */
    private HashMap<String, Integer> sortLiveScores(HashMap<String, Integer> liveScores) {
        HashMap<String, Integer> copiedScores = new HashMap<>(liveScores);

        String username = Preferences.INSTANCE.getUsername();
        Integer score = copiedScores.remove(username);


        HashMap<String, Integer> sorted = new LinkedHashMap<String, Integer>();
        sorted.put(username, score);


        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > currentScores =
                new LinkedList<Map.Entry<String, Integer> >(copiedScores.entrySet());


        // Sort the list
        Collections.sort(currentScores, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        int count = 0;
        for (Map.Entry<String, Integer> scoreEntry : currentScores) {
            sorted.put(scoreEntry.getKey(), scoreEntry.getValue());
            count++;
            if (count == 5 || currentScores.isEmpty()) {
                break;
            }
        }

        return sorted;
    }


    public HashMap<String, Integer> getHighScores() {
        return highScores;
    }
}
