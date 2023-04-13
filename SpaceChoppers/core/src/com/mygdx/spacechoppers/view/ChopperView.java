package com.mygdx.spacechoppers.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.mygdx.spacechoppers.model.ChopperModel;

public class ChopperView implements Disposable {

    private Sprite sprite;
    private Texture texture;
    private ChopperModel model;

    public ChopperView(ChopperModel model){
        this.model = model;
        this.texture = new Texture("helicopter_1.png");
        this.sprite = new Sprite(texture);
    }

    public void draw(SpriteBatch sb){

        //sb.draw(sprite, model.getLocation().x, model.getLocation().y, 0,0, SpaceChoppersGame.Companion.getWidth(), SpaceChoppersGame.Companion.getHeight(), 0,0,model.getCurrentAngle());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
