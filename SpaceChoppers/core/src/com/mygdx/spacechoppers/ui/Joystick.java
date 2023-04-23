package com.mygdx.spacechoppers.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.spacechoppers.utils.AssetManager;

public class Joystick {
    private final Touchpad touchpad;

    public Joystick(float camWidth){
        float width = 300;
        float x = camWidth / 2 - width / 2;
        float y = 100;
        Skin skin = AssetManager.INSTANCE.getJoystickSkin();
        this.touchpad = new Touchpad(20, skin);
        float height = 300;
        touchpad.setBounds(x, y, width, height);
        touchpad.getStyle().knob.setMinWidth(20);
        touchpad.getStyle().knob.setMinHeight(20);
    }

    public Touchpad getTouchpad(){
        return touchpad;
    }
}
