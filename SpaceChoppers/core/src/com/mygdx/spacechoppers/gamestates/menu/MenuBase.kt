package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.AssetManager
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.controller.AsteroidController
import com.mygdx.spacechoppers.factories.AsteroidFactory
import com.mygdx.spacechoppers.model.AsteroidTextures
import com.mygdx.spacechoppers.utils.MenuCommon
import kotlin.math.min
import kotlin.random.Random

abstract class MenuBase(gsm: GameStateManager) : GameState(gsm) {
    protected val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    protected val skin = AssetManager.menuSkin
    protected var background = TextureRegion(AssetManager.menuBackgroundClean)

    // Asteroids
    private val asteroidFactory = AsteroidFactory(sb, AsteroidTextures(), cam)
    private val asteroids = ArrayList<AsteroidController>()

    init {
        Gdx.input.inputProcessor = stage
        createAsteroids(3)
    }

    private fun createAsteroids(num: Int) {
        for (i in 0..num) {
            asteroids.add(asteroidFactory.create())
        }
    }

    override fun update(dt: Float) {

        if (asteroids.all { a: AsteroidController -> a.model.isOutOfBounds }) {
            asteroids.clear()
            createAsteroids(Random.nextInt(5)) // TODO: Dynamic number
        }

        asteroids.forEach { asteroidController: AsteroidController -> asteroidController.moveAsteroid() }
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        sb.begin()
        sb.draw(background, 0f, 0f)
        asteroids.forEach{ asteroid: AsteroidController -> asteroid.draw() }
        sb.end()

        stage.act(min(delta, 1 / 30f))
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
    }
}