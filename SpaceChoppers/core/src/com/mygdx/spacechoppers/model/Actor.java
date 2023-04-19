package com.mygdx.spacechoppers.model;

import static com.mygdx.spacechoppers.helper.Const.PIXELS_TO_METERS;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.spacechoppers.SpaceChoppersGame;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.helper.BodyHelper;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.helper.ContactType;

public abstract class Actor implements IActor {

    protected int hitPoints;
    protected Vector3 location;
    protected Body body;

    private Vector2 textureSize;

    public Actor(int hitPoints, Vector3 location, Vector2 textureSize, World world, ContactType contactType) {
        this.hitPoints = hitPoints;
        this.location = location;
        this.textureSize = textureSize;
        this.body = BodyHelper.createBody(location.x * PIXELS_TO_METERS, location.y * PIXELS_TO_METERS, textureSize, 1, world, contactType);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Vector3 getLocation() {
        return location;
    }

    public Vector2 pixelsToMeters(float xPosition, float yPosition) {
        float xAdjusted = (xPosition + textureSize.x / 2) * PIXELS_TO_METERS;
        float yAdjusted = (yPosition + textureSize.y / 2) * PIXELS_TO_METERS;
        return new Vector2(xAdjusted, yAdjusted);
    }

    public void moveBody(float xPosition, float yPosition) {
        Vector2 adjustedVector = pixelsToMeters(xPosition, yPosition);
        float xMeters = adjustedVector.x;
        float yMeters = adjustedVector.y;
        body.setTransform(xMeters, yMeters, 0);
    }

    @Override
    public void takeDamage() {
        this.hitPoints--;
    }

    public boolean isOutOfBounds() {
        return (location.x < 0 ||
                location.y < 0 ||
                location.x >= SpaceChoppersGame.Companion.getWidth() ||
                location.y >= SpaceChoppersGame.Companion.getHeight()
        );
    }
}
