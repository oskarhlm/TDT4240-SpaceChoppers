package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.controller.AsteroidController
import com.mygdx.spacechoppers.controller.ChopperController
import com.mygdx.spacechoppers.model.AsteroidModel
import com.mygdx.spacechoppers.model.AsteroidTextures
import com.mygdx.spacechoppers.model.Joystick
import com.mygdx.spacechoppers.view.AsteroidView


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)

    // Chopper
    private val chopperController = ChopperController(sb, joystick.touchpad)

    // Asteroid resource(s)
    private val asteroidTextures = AsteroidTextures()

    // Asteroids
    private val asteroidController = AsteroidController(sb, asteroidTextures)

    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)
    }

    override fun update(dt: Float) {
        chopperController.moveChopper()
        asteroidController.moveAsteroid()
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        sb.begin()
        chopperController.draw()
        asteroidController.draw()

        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}