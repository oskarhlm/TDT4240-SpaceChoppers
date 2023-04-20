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

import com.mygdx.spacechoppers.controller.AsteroidController
import com.mygdx.spacechoppers.controller.BackgroundController
import com.mygdx.spacechoppers.controller.ChopperController
import com.mygdx.spacechoppers.controller.HealthBarController
import com.mygdx.spacechoppers.controller.LaserController
import com.mygdx.spacechoppers.controller.LiveScoresController
import com.mygdx.spacechoppers.model.Explosion

import com.mygdx.spacechoppers.gamestates.menu.MainMenuState
import com.mygdx.spacechoppers.model.Joystick
import com.mygdx.spacechoppers.networking.NetworkClient
import com.mygdx.spacechoppers.utils.MenuCommon.skin
import com.mygdx.spacechoppers.utils.Preferences


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
    private val liveScoresController = LiveScoresController(cam)
    private val backgroundController = BackgroundController(stage)

    // Asteroids
    private val asteroidsController = AsteroidController.getInstance();


    // Explosions
    private val explosions = ArrayList<Explosion>()

    private val quitButton = TextButton("Quit", skin)

    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)

        // Set contact listener for the world of bodies
        world.setContactListener(gameContactListener)

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

    override fun update(dt: Float) {
        // Step world
        world.step(1/60f, 6, 2)
        // Move chopper
        chopperController.moveChopper(dt)


        // Move camera
        cam.position.set(chopperController.position)

        // Fire and move lasers
        lasersController.fireLasers(dt, cam.position, chopperController.model.currentAngle, world)
        
        // Spawn and move asteroids
        asteroidsController.spawnAndMoveAsteroids(dt, world, cam);

    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam.update()
        sb.projectionMatrix = cam.combined


        sb.begin()


        // Draw background
        backgroundController.draw(sb)

        // Draw lasers
        lasersController.draw(sb)

        // Draw chopper
        chopperController.draw(sb)

        // Draw asteroids
        asteroidsController.draw(sb)

        // Draw scores
        liveScoresController.renderScores(sb)

        sb.end()
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()

    }

    override fun dispose() {
        stage.dispose()
        joystick.dispose()
    }
}