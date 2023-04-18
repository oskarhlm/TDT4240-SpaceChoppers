package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class Joystick {
    private final float width = 300;
    private final float height = 300;
    private final float x;
    private final float y;
    private final Touchpad touchpad;
    private final Skin skin;

    public Joystick(float camWidth){
        this.x = camWidth / 2 - width / 2;
        this.y = 100;
        this.skin = new Skin(Gdx.files.internal("neon/skin/neon-ui.json"));
        this.touchpad = new Touchpad(20, skin);
        touchpad.setBounds(x, y, width, height);
    }

    public Touchpad getTouchpad(){
        return touchpad;
    }

    public void dispose() {
        skin.dispose();
    }
}
