package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.AssetManager.manager
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.controller.*
import com.mygdx.spacechoppers.factories.AsteroidFactory
import com.mygdx.spacechoppers.model.AsteroidTextures
import com.mygdx.spacechoppers.model.Joystick
import kotlin.random.Random


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)
    private val world = World(Vector2(0f,0f), false)

    // Chopper
    private val chopperTexture = manager.get("heli_img/Chopper_1.png", Texture::class.java)
    private val chopperTextureSize = Vector2(chopperTexture.width.toFloat(), chopperTexture.height.toFloat())

    private val chopperController = ChopperController(joystick.touchpad, chopperTextureSize, world)

    // Laser
    private val lasersController = LaserController();

    // Scores
    private val liveScoresController = LiveScoresController();

    // Asteroids
    private val asteroidTextureSize = with(manager.get("asteroid-sheet.png",
        Texture::class.java)) {
        Pair(width / 4, height / 4)
    }

    private val asteroidFactory = AsteroidFactory(sb, AsteroidTextures(), world)
    private val asteroids = ArrayList<AsteroidController>()

    // Background
    private val background = BackgroundController(stage);

    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)

        createAsteroids(3)

        println("Width: " + SpaceChoppersGame.width)
        println("Height: " + SpaceChoppersGame.height)

    }

    private fun createAsteroids(num: Int) {
        for (i in 0..num) {
            asteroids.add(asteroidFactory.create())
        }
    }

    override fun update(delta: Float) {
        // Get chopper movement
        chopperController.moveChopper(delta)

        cam.position.set(chopperController.model.location)
        lasersController.fireLasers(delta, chopperController.model.location, chopperController.model.currentAngle)


        // Check if asteroids are out of bounds
        if (asteroids.all { a: AsteroidController -> a.model.isOutOfBounds }) {
            asteroids.clear()
            createAsteroids(Random.nextInt(5)) // TODO: Dynamic number
        }

        asteroids.forEach { asteroidController: AsteroidController -> asteroidController.moveAsteroid() }
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam.update()
        sb.projectionMatrix = cam.combined

        sb.begin()
        background.draw(sb)

        chopperController.draw(sb)
        lasersController.draw(sb)
        liveScoresController.renderScores(sb)

        liveScoresController.renderScores(sb)
        asteroids.forEach{ asteroidController: AsteroidController -> asteroidController.draw() }

        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
        
    }

    override fun dispose() {
        stage.dispose()
        joystick.dispose()
    }
}