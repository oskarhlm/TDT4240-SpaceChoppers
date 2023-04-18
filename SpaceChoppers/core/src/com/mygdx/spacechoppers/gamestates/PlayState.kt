package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.controller.AsteroidController
import com.mygdx.spacechoppers.controller.ChopperController
import com.mygdx.spacechoppers.factories.AsteroidFactory
import com.mygdx.spacechoppers.model.AsteroidTextures
import com.mygdx.spacechoppers.model.Joystick
import kotlin.random.Random


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)

    // Chopper
    private val chopperController = ChopperController(sb, joystick.touchpad)

    // Asteroids
    private val asteroidFactory = AsteroidFactory(sb, AsteroidTextures())
    private val asteroids = ArrayList<AsteroidController>()

    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)

        createAsteroids(3)

        println("Width: " + SpaceChoppersGame.width)
        println("Height: " + SpaceChoppersGame.height)

    }

    private fun createAsteroids(num : Int)  {
        for (i in 0..num){
            asteroids.add(asteroidFactory.create())
        }
    }

    override fun update(delta: Float) {
        // Get chopper movement
        chopperController.moveChopper()

        // Check if asteroids are out of bounds
        if (asteroids.all{ a : AsteroidController -> a.model.isOutOfBounds }){
            asteroids.clear()
            createAsteroids(Random.nextInt(5)) // TODO: Dynamic number
        }

        asteroids.forEach { asteroidController: AsteroidController -> asteroidController.moveAsteroid() }

    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        sb.begin()
        chopperController.draw()

        asteroids.forEach{ asteroidController: AsteroidController -> asteroidController.draw() }

        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }


}