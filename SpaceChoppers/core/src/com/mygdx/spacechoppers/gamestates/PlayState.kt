package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.AssetManager.manager
import com.mygdx.spacechoppers.GameContactListener
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.controller.*
import com.mygdx.spacechoppers.factories.AsteroidFactory
import com.mygdx.spacechoppers.gamestates.menu.MainMenuState
import com.mygdx.spacechoppers.helper.Const.PIXELS_TO_METERS
import com.mygdx.spacechoppers.model.AsteroidTextures
import com.mygdx.spacechoppers.model.Joystick
import com.mygdx.spacechoppers.networking.NetworkClient
import com.mygdx.spacechoppers.utils.MenuCommon.skin
import com.mygdx.spacechoppers.utils.Preferences
import kotlin.random.Random


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)
    private val world = World(Vector2(0f,0f), false)

    val networkClient: NetworkClient = NetworkClient.getInstance()

    // Contact listener
    private val gameContactListener = GameContactListener()

    // Debug renderer
    private val box2DDebugRenderer = Box2DDebugRenderer()

    // Chopper
    private val chopperController = ChopperController(joystick.touchpad, world)

    // Laser
    private val lasersController = LaserController()

    // Scores
    private val liveScoresController = LiveScoresController(cam)

    // Asteroids
    private val asteroidTextureSize = with(manager.get("asteroid-sheet.png",
        Texture::class.java)) {
        Pair(width / 4, height / 4)
    }

    private val asteroidFactory = AsteroidFactory(sb, AsteroidTextures(), world)
    private val asteroids = ArrayList<AsteroidController>()

    private val quitButton = TextButton("Quit", skin)
    // Background
    private val background = BackgroundController(stage)

    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)

        // Set contact listener for the world of bodies
        world.setContactListener(gameContactListener)

        createAsteroids(3)

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

        SpaceChoppersGame.mapWidth = background.mapWidth
        SpaceChoppersGame.mapHeight = background.mapHeight

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

        cam.position.set(chopperController.position)

        val adjustedChopperX = chopperController.position.x - (chopperController.textureSize.x / 2)
        val adjustedChopperY = chopperController.position.y - (chopperController.textureSize.y / 2)

        val adjustedChopperPos = Vector3(adjustedChopperX, adjustedChopperY, 0f);
        lasersController.fireLasers(dt, cam.position, chopperController.model.currentAngle, world)


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

        lasersController.draw(sb)
        chopperController.draw(sb)


        liveScoresController.renderScores(sb)
        asteroids.forEach{ asteroidController: AsteroidController -> asteroidController.draw() }

        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()

        val debugMatrix = Matrix4(cam.combined)
        debugMatrix.scale(1 / PIXELS_TO_METERS, 1 / PIXELS_TO_METERS, 1f)

        box2DDebugRenderer.render(world, debugMatrix)

    }

    override fun dispose() {
        stage.dispose()
        joystick.dispose()
    }
}