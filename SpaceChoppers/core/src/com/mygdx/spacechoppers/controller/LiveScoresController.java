package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.data.networking.Score;
import com.mygdx.spacechoppers.interfaces.IController;
import com.mygdx.spacechoppers.model.LiveScoresModel;
import com.mygdx.spacechoppers.view.LiveScoresView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class LiveScoresController implements IController<LiveScoresModel, LiveScoresView> {

    private LiveScoresModel model;
    private LiveScoresView view;

    public LiveScoresController(OrthographicCamera cam) {
        this.model = new LiveScoresModel();
        this.view = new LiveScoresView(cam);
    }

    @Override
    public void updateModel(float dt) {
        model.updateScores();
    }

    @Override
    public void updateView(@NotNull SpriteBatch sb) {
        view.draw(sb, model.getScores());
    }
}
