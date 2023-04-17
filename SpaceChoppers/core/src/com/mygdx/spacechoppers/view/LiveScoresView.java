package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.data.networking.Score;
import com.mygdx.spacechoppers.gamestates.menu.utils.Preferences;

import java.util.HashMap;
import java.util.List;

public class LiveScoresView implements Disposable {

    private BitmapFont font;

    public LiveScoresView() {
        font = new BitmapFont();
        font.setColor(Color.WHITE); // set the font color to white
        float scaleFactor = 4.0f; // set the font scale factor to 2.0 (you can adjust this as needed)
        font.getData().setScale(scaleFactor); // set the font scale using the scale factor

    }

    public void draw(SpriteBatch sb, List<Score> liveScores, Vector2 position){
        // Draw each username and score here
        float x = position.x;
        float y = position.y;
        String username = Preferences.INSTANCE.getUsername();

        for (Score userScore : liveScores) {
            if (userScore.getUsername().equals(username)) {
                font.setColor(Color.LIME);
            } else {
                font.setColor(Color.WHITE);
            }
            String text = userScore.getUsername() + ": " + userScore.getScore();

            font.draw(sb, text, x, y);
            y -= 60;
        }
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
