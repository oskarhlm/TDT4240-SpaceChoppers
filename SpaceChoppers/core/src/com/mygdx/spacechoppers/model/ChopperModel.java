package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.helper.ContactType;

public class ChopperModel extends Actor{
    public static final String TEXTURE_PATH = "chopper/1.png";

    private Vector2 currentVector;


    public ChopperModel(int HP, Vector3 Location, Vector2 textureSize, World world) {
        super(HP, Location, textureSize.x, textureSize.y, world, ContactType.CHOPPER);
        currentVector = new Vector2(0,0);
    }

    public void setCurrentVector(Vector2 currentVector) {
        this.currentVector = currentVector;
    }

    public float getCurrentAngle(){
        return currentVector.angleDeg();
    }
}