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

        if(a == null || b == null) return;
        if(a.getUserData() == null || b.getUserData() == null) return;

        if(a.getUserData() == ContactType.ASTEROID || b.getUserData() == ContactType.ASTEROID){
            if(a.getUserData() == ContactType.CHOPPER || b.getUserData() == ContactType.CHOPPER){
                // do something
            }
            if(a.getUserData() == ContactType.LASER || b.getUserData() == ContactType.LASER){
                // do something else
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
