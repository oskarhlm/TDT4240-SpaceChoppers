package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.controller.ChopperController
import com.mygdx.spacechoppers.model.Asteroid
import com.mygdx.spacechoppers.model.AsteroidTextures
import com.mygdx.spacechoppers.model.Chopper
import com.mygdx.spacechoppers.model.Joystick
import com.mygdx.spacechoppers.view.AsteroidView
import com.mygdx.spacechoppers.view.ChopperView


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)

    // Chopper
    private val chopper = Chopper(100, Vector3(100f, 100f, 0f))
    private val chopperView = ChopperView(chopper)
    private val chopperController = ChopperController(chopper, joystick.touchpad)

    // Asteroid resource(s)
    private val asteroidTextures = AsteroidTextures()

    // Asteroids
    // TODO: Create single class for this logic (MVC)
    private val asteroid = Asteroid(10, Vector3(40F, 40F, 0F))
    private val asteroidView = AsteroidView(asteroid, asteroidTextures)

    private val asteroid1 = Asteroid(10, Vector3(50F, 50F, 0F))
    private val asteroidView1 = AsteroidView(asteroid1, asteroidTextures)


    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)
    }

    override fun update(dt: Float) {
        chopperController.moveChopper()
        asteroid.moveAsteroid()
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        sb.begin()
        chopperView.draw(sb)
        asteroidView.draw(sb)
        asteroidView1.draw(sb)
        //asteroidView.draw(sb)

        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}