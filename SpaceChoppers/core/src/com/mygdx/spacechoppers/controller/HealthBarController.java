package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.spacechoppers.interfaces.IController;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.model.HealthBarModel;
import com.mygdx.spacechoppers.view.HealthBarView;

import org.jetbrains.annotations.NotNull;

public class HealthBarController implements IController<HealthBarModel, HealthBarView> {

    HealthBarView view;
    ChopperModel chopperModel;

    public HealthBarController(OrthographicCamera cam, ChopperModel chopperModel) {
        this.view = new HealthBarView(cam);
        this.chopperModel = chopperModel;
    }

    @Override
    public void updateModel(float dt) {
    }

    @Override
    public void updateView(@NotNull SpriteBatch sb) {
        view.draw(sb, chopperModel.hitPoints);
    }
}
