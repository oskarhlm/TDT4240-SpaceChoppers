package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.gamestates.menu.utils.Preferences;

import java.util.HashMap;

public class LiveScoresView implements Disposable {

    private BitmapFont font;

    public LiveScoresView() {
        font = new BitmapFont();
        font.setColor(Color.WHITE); // set the font color to white
        float scaleFactor = 4.0f; // set the font scale factor to 2.0 (you can adjust this as needed)
        font.getData().setScale(scaleFactor); // set the font scale using the scale factor

    }

    public void draw(SpriteBatch sb, HashMap<String, Integer> liveScores, Vector2 position){
        // Draw each username and score here
        float x = position.x;
        float y = position.y;
        String username = Preferences.INSTANCE.getUsername();
        for (HashMap.Entry<String, Integer> entry : liveScores.entrySet()) {
            if (entry.getKey().equals(username)) {
                font.setColor(Color.LIME);
            } else {
                font.setColor(Color.WHITE);
            }
            String text = entry.getKey() + ": " + entry.getValue();

            font.draw(sb, text, x, y);
            y -= 60;
        }
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
