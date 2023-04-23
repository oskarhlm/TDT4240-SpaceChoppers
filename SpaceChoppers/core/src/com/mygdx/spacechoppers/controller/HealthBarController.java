package com.mygdx.spacechoppers.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.spacechoppers.model.HealthBarModel;
import com.mygdx.spacechoppers.view.HealthBarView;

public class HealthBarController {

    HealthBarView view;

    public HealthBarController( OrthographicCamera cam) {
        this.view = new HealthBarView(cam);
    }

    public void draw(SpriteBatch sb, int hitPoints){
        view.draw(sb, hitPoints);
    }

}
