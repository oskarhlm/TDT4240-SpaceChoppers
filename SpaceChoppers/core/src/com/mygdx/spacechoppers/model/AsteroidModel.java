package com.mygdx.spacechoppers.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.spacechoppers.helper.Const;
import com.mygdx.spacechoppers.helper.ContactType;
import com.mygdx.spacechoppers.interfaces.IModel;

public class AsteroidModel extends Actor implements IModel {

    private Vector3 position;
    private Vector2 velocity;
    private Vector2 size;

    public AsteroidModel(int HP, Vector3 position, Vector2 velocity, Vector2 textureSize, World world) {
        super(HP, position, textureSize, world);
        this.position = position;
        this.velocity = velocity;
        this.size = textureSize;
    }

    public Vector2 getSize() {
        return this.size;
    }

    public void updatePosition() {
        position.add(velocity.x, velocity.y, 0);
    }

    public Body getBody() {
        return this.body;
    }
}

