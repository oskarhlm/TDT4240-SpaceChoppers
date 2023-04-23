package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.data.networking.Score;
import com.mygdx.spacechoppers.interfaces.IView;
import com.mygdx.spacechoppers.model.LiveScoresModel;
import com.mygdx.spacechoppers.utils.Preferences;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LiveScoresView implements IView<LiveScoresModel>, Disposable {

    private final BitmapFont font;
    private final OrthographicCamera cam;
    private final GlyphLayout glyphLayout;

    public LiveScoresView(OrthographicCamera cam) {
        this.cam = cam;
        glyphLayout = new GlyphLayout();
        font = new BitmapFont();
        font.setColor(Color.WHITE); // set the font color to white
        float scaleFactor = 4.0f; // set the font scale factor to 2.0 (you can adjust this as needed)
        font.getData().setScale(scaleFactor); // set the font scale using the scale factor

    }

    public void draw(@NotNull SpriteBatch sb, LiveScoresModel liveScoresModel) {
        List<Score> liveScores = liveScoresModel.getScores();

        // Iterate through the scores and draw them on the screen
        float x = cam.position.x + cam.viewportWidth / 2 - 60;
        float y = cam.position.y + cam.viewportHeight / 2 - 60;
        String username = Preferences.INSTANCE.getUsername();
        for (Score userScore : liveScores) {
            // Get the score text and calculate its width
            String scoreText = userScore.getUsername() + ": " + userScore.getScore();
            glyphLayout.setText(font, scoreText);
            float scoreTextWidth = glyphLayout.width;

            // Calculate the drawing position based on the width of the scoreText
            float drawX = x - scoreTextWidth;

            // Set the font color based on whether the score belongs to the current user
            if (userScore.getUsername().equals(username)) {
                font.setColor(Color.LIME);
            } else {
                font.setColor(Color.WHITE);
            }

            // Draw the score text on the screen
            font.draw(sb, scoreText, drawX, y);
            y -= 60;
        }
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
