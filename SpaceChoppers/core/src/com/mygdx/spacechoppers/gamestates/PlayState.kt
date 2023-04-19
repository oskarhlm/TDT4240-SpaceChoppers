package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.controller.AsteroidController
import com.mygdx.spacechoppers.controller.BackgroundController
import com.mygdx.spacechoppers.controller.ChopperController
import com.mygdx.spacechoppers.controller.HealthBarController
import com.mygdx.spacechoppers.controller.LaserController
import com.mygdx.spacechoppers.controller.LiveScoresController
import com.mygdx.spacechoppers.factories.AsteroidFactory
import com.mygdx.spacechoppers.model.AsteroidTextures
import com.mygdx.spacechoppers.model.Explosion
import com.mygdx.spacechoppers.model.Joystick
import com.mygdx.spacechoppers.gamestates.menu.MainMenuState
import com.mygdx.spacechoppers.networking.NetworkClient
import com.mygdx.spacechoppers.utils.MenuCommon.skin
import com.mygdx.spacechoppers.utils.Preferences
import kotlin.random.Random


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)
    private val healthBarController: HealthBarController = HealthBarController(cam)

    val networkClient: NetworkClient = NetworkClient.getInstance()

    // Controllers
    private val chopperController = ChopperController(joystick.touchpad)
    private val lasersController = LaserController()
    private val liveScoresController = LiveScoresController(cam)
    private val backgroundController = BackgroundController(stage)

    // Asteroids
    private val asteroidFactory = AsteroidFactory(sb, AsteroidTextures())
    private val asteroids = ArrayList<AsteroidController>()

    // Explosions
    private val explosions = ArrayList<Explosion>()

    private val quitButton = TextButton("Quit", skin)

    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)

        createAsteroids(3)

        explosions.add(Explosion(100f, 100f, 0f)) // TODO: Delete these later
        explosions.add(Explosion(100f, 800f, 0f)) // TODO: Delete these later

        quitButton.setPosition(20f, SpaceChoppersGame.height - quitButton.height - 80f)
        quitButton.width = quitButton.width * 2 // increase width
        quitButton.height = quitButton.height * 2 // increase height
        quitButton.label.setFontScale(2f)
        quitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                networkClient.leaveLobby(Preferences.lobbyID, Preferences.username)
                gsm.set(MainMenuState(gsm))
            }
        })
        stage.addActor(quitButton)

        SpaceChoppersGame.mapWidth = backgroundController.mapWidth
        SpaceChoppersGame.mapHeight = backgroundController.mapHeight

        // Send scores in order to draw on screen on startup
        networkClient.sendScore(Preferences.lobbyID, Preferences.username, 0)
    }

    private fun createAsteroids(num: Int) {
        for (i in 0..num) {
            asteroids.add(asteroidFactory.create())
        }
    }

    override fun update(dt: Float) {
        // Get chopper movement
        chopperController.moveChopper(dt)

        cam.position.set(chopperController.model.location)
        lasersController.fireLasers(dt, chopperController.model.location, chopperController.model.currentAngle)

        // Check if asteroids are out of bounds
        if (asteroids.all { a: AsteroidController -> a.model.isOutOfBounds }) {
            asteroids.clear()
            createAsteroids(Random.nextInt(5)) // TODO: Dynamic number
        }

        asteroids.forEach { asteroidController: AsteroidController -> asteroidController.moveAsteroid() }
        explosions.forEach { exp : Explosion -> exp.update(dt) }

    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam.update()
        sb.projectionMatrix = cam.combined

        sb.begin()
        backgroundController.draw(sb)
        chopperController.draw(sb)
        lasersController.draw(sb)
        liveScoresController.renderScores(sb)
        asteroids.forEach{ asteroid: AsteroidController -> asteroid.draw() }
        explosions.forEach { explosion: Explosion -> explosion.draw(sb) }
        healthBarController.draw(sb, chopperController.model.hitPoints)
        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
        joystick.dispose()
    }
}