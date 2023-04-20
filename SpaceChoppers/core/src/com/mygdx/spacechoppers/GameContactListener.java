package com.mygdx.spacechoppers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.spacechoppers.helper.ContactType;

public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (a == null || b == null) return;
        if (a.getBody().getUserData() == null || b.getBody().getUserData() == null) return;

        if (a.getBody().getUserData() == ContactType.ASTEROID || b.getBody().getUserData() == ContactType.ASTEROID) {
            if (a.getBody().getUserData() == ContactType.CHOPPER || b.getBody().getUserData() == ContactType.CHOPPER) {
                System.out.println("scream!");
            }
            if (a.getBody().getUserData() == ContactType.LASER || b.getBody().getUserData() == ContactType.LASER) {
                System.out.println("scream!");
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
