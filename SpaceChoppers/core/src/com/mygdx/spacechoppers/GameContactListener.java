package com.mygdx.spacechoppers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.spacechoppers.controller.AsteroidController;
import com.mygdx.spacechoppers.controller.ExplosionsController;
import com.mygdx.spacechoppers.helper.ContactType;
import com.mygdx.spacechoppers.model.Actor;
import com.mygdx.spacechoppers.model.AsteroidModel;
import com.mygdx.spacechoppers.model.ChopperModel;
import com.mygdx.spacechoppers.model.LaserModel;
import com.mygdx.spacechoppers.networking.MessageReceiver;
import com.mygdx.spacechoppers.networking.NetworkClient;
import com.mygdx.spacechoppers.networking.NetworkHandler;
import com.mygdx.spacechoppers.utils.Preferences;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if (a == null || b == null) return;
        if (a.getUserData() == null || b.getUserData() == null) return;

        // Cast both bodies
        Class classA = a.getUserData().getClass();
        Class classB = b.getUserData().getClass();

        boolean isAsteroidLaserCollision = classA == AsteroidModel.class && classB == LaserModel.class;
        boolean isLaserAsteroidCollision = classA == LaserModel.class && classB == AsteroidModel.class;

        if (isAsteroidLaserCollision) {
            // Cast models
            AsteroidModel asteroid = (AsteroidModel) a.getBody().getUserData();
            LaserModel laser = (LaserModel) b.getBody().getUserData();

            // Destroy asteroid
            AsteroidController.getInstance().toDispose.add(asteroid);

            // Add explosion
            ExplosionsController.getInstance().addExplosion(asteroid.getLocation(), asteroid.getSize().x);
        } else if (isLaserAsteroidCollision) {
            // Cast models
            AsteroidModel asteroid = (AsteroidModel) b.getBody().getUserData();
            LaserModel laser = (LaserModel) a.getBody().getUserData();

            // Destroy asteroid
            AsteroidController.getInstance().toDispose.add(asteroid);

            // Add explosion
            ExplosionsController.getInstance().addExplosion(asteroid.getLocation(), asteroid.getSize().x);
        }

        if (isAsteroidLaserCollision || isLaserAsteroidCollision) {
            int currentScore = MessageReceiver.getInstance().getPlayerScore();
            try {
                NetworkClient.getInstance().sendScore(Preferences.INSTANCE.getLobbyID(), Preferences.INSTANCE.getUsername(), currentScore+10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        boolean isAsteroidChopperCollision = classA == AsteroidModel.class && classB == ChopperModel.class;
        boolean isChopperAsteroidCollision = classA == ChopperModel.class && classB == AsteroidModel.class;

        if (isAsteroidChopperCollision) {
            // Cast models
            AsteroidModel asteroid = (AsteroidModel) a.getBody().getUserData();
            ChopperModel chopper = (ChopperModel) b.getBody().getUserData();

            // Destroy asteroid
            AsteroidController.getInstance().toDispose.add(asteroid);

            chopper.takeDamage();

            // Create explosion
            ExplosionsController.getInstance().addExplosion(asteroid.getLocation(), asteroid.getSize().x);

        } else if (isChopperAsteroidCollision) {
            // Cast models
            AsteroidModel asteroid = (AsteroidModel) b.getBody().getUserData();
            ChopperModel chopper = (ChopperModel) a.getBody().getUserData();

            // Destroy asteroid
            AsteroidController.getInstance().toDispose.add(asteroid);

            chopper.takeDamage();

            // Add explosion
            ExplosionsController.getInstance().addExplosion(asteroid.getLocation(), asteroid.getSize().x);
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
