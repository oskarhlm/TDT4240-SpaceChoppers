package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameContactListener
import com.mygdx.spacechoppers.utils.AssetManager
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.controller.AsteroidController
import com.mygdx.spacechoppers.ui.Background
import com.mygdx.spacechoppers.controller.ChopperController
import com.mygdx.spacechoppers.controller.ExplosionsController
import com.mygdx.spacechoppers.controller.HealthBarController
import com.mygdx.spacechoppers.controller.LaserController
import com.mygdx.spacechoppers.controller.LiveScoresController
import com.mygdx.spacechoppers.gamestates.menu.GameOverState
import com.mygdx.spacechoppers.gamestates.menu.MainMenuState
import com.mygdx.spacechoppers.ui.Joystick
import com.mygdx.spacechoppers.networking.MessageReceiver
import com.mygdx.spacechoppers.networking.NetworkClient
import com.mygdx.spacechoppers.utils.Preferences

class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)
    private val timer = Timer()

    private val world = World(Vector2(0f, 0f), false)

    private val networkClient: NetworkClient? = NetworkClient.getInstance()
    private val messageReceiver: MessageReceiver? = MessageReceiver.getInstance()

    // Contact listener
    private val gameContactListener = GameContactListener()

    // Chopper
    private val chopperController = ChopperController(joystick.touchpad, world)

    // Laser
    private val lasersController = LaserController()

    // Live scores
    private val liveScoresController = LiveScoresController(cam)

    // Background
    private val background = Background()

    // Asteroids
    private val asteroidsController = AsteroidController.getInstance()

    // Health bar
    private val healthBarController = HealthBarController(cam, chopperController.model)

    // Explosions
    private val explosionsController = ExplosionsController.getInstance()

    // Buttons
    private val quitButton = TextButton("Quit", AssetManager.menuSkin)
    private val lobbyLabel = Label(Preferences.lobbyID.toString(), AssetManager.menuSkin)
    private var boostButton: ImageButton
    private var rapidFireButton: ImageButton

    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)

        // Set contact listener for the world of bodies
        world.setContactListener(gameContactListener)

        quitButton.setPosition(20f, SpaceChoppersGame.height - quitButton.height - 80f)
        quitButton.width *= 2 // increase width
        quitButton.height *= 2 // increase height
        quitButton.label.setFontScale(2f)
        quitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                networkClient?.leaveLobby(Preferences.lobbyID, Preferences.username)
                gsm.set(MainMenuState(gsm))
            }
        })

        lobbyLabel.setFontScale(3f)
        lobbyLabel.setPosition(
            quitButton.width + 80f,
            SpaceChoppersGame.height - 80f
        )

        boostButton = ImageButton(TextureRegionDrawable(AssetManager.boostButtonActive))
        boostButton.setPosition(boostButton.width / 2, 160f)
        boostButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (boostButton.isDisabled) return
                chopperController.boost()
                boostButton.style.imageUp = TextureRegionDrawable(AssetManager.boostButtonInactive)
                boostButton.isDisabled = true
                val task: Timer.Task = object : Timer.Task() {
                    override fun run() {
                        boostButton.style.imageUp =
                            TextureRegionDrawable(AssetManager.boostButtonActive)
                        boostButton.isDisabled = false
                    }
                }
                timer.scheduleTask(task, 30f)
            }
        })

        rapidFireButton = ImageButton(TextureRegionDrawable(AssetManager.rapidFireButtonActive))
        rapidFireButton.setPosition(SpaceChoppersGame.width - rapidFireButton.width * 1.5f, 160f)
        rapidFireButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (rapidFireButton.isDisabled) return
                lasersController.rapidFire()
                rapidFireButton.style.imageUp =
                    TextureRegionDrawable(AssetManager.rapidFireButtonInactive)
                rapidFireButton.isDisabled = true
                val task: Timer.Task = object : Timer.Task() {
                    override fun run() {
                        rapidFireButton.style.imageUp =
                            TextureRegionDrawable(AssetManager.rapidFireButtonActive)
                        rapidFireButton.isDisabled = false
                    }
                }
                timer.scheduleTask(task, 30f)
            }
        })

        println("lobby label: $lobbyLabel.text")

        stage.addActor(quitButton)
        stage.addActor(lobbyLabel)
        stage.addActor(boostButton)
        stage.addActor(rapidFireButton)

        SpaceChoppersGame.mapWidth = background.mapWidth
        SpaceChoppersGame.mapHeight = background.mapHeight

        // Send scores in order to draw on screen on startup
        networkClient?.sendScore(Preferences.lobbyID, Preferences.username, 0)
    }

    override fun update(dt: Float) {
        // Step world
        world.step(1 / 60f, 6, 2)

        // Move chopper
        chopperController.updateModel(dt)

        // Move camera
        cam.position.set(chopperController.position)

        // Fire and move lasers
        lasersController.fireLasers(dt, cam.position, chopperController.model.currentAngle, world)

        // Spawn and move asteroids
        asteroidsController.spawnAndMoveAsteroids(dt, world, cam)

        // Update explosions
        explosionsController.updateModel(dt)
        
        // Update scores
        liveScoresController.updateModel(dt)

        // Check if player is out of HP and if so leave the lobby
        if (chopperController.model.hitPoints <= 0) {
            networkClient?.leaveLobby(Preferences.lobbyID, Preferences.username)
            gsm.set(GameOverState(gsm, messageReceiver?.playerScore ?: -1))
        }
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        cam.update()
        sb.projectionMatrix = cam.combined
        sb.begin()

        // Draw background
        background.draw(sb)

        // Draw lasers
        lasersController.updateView(sb)

        // Draw chopper
        chopperController.updateView(sb)

        // Draw asteroids
        asteroidsController.updateView(sb)

        // Draw scores
        liveScoresController.updateView(sb)

        // Draw health bar
        healthBarController.updateView(sb)

        // Draw explosions
        explosionsController.updateView(sb)

        sb.end()
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
    }
}