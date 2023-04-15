package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameContactListener
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.controller.ChopperController
import com.mygdx.spacechoppers.helper.Const.PIXELS_TO_METERS
import com.mygdx.spacechoppers.model.AsteroidModel
import com.mygdx.spacechoppers.model.ChopperModel
import com.mygdx.spacechoppers.model.Joystick
import com.mygdx.spacechoppers.view.AsteroidView
import com.mygdx.spacechoppers.view.ChopperView


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val world = World(Vector2(0F,0F), false)
    private val box2DDebugRenderer = Box2DDebugRenderer()
    private val gameContactListener = GameContactListener()

    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)

    // Load Chopper texture and obtain its size
    private val chopperTexture = Texture(ChopperModel.TEXTURE_PATH)
    private val chopperTextureSize = Vector2(chopperTexture.width.toFloat(),
        chopperTexture.height.toFloat()
    )

    // Load Asteroid texture and obtain its size
    private val asteroidTexture = Texture(AsteroidModel.TEXTURE_PATH)
    private val asteroidTextureSize = Vector2(asteroidTexture.width.toFloat(),
        asteroidTexture.height.toFloat()
    )

    // Chopper
    private val chopperModel = ChopperModel(100, Vector3(100f, 100f, 0f), chopperTextureSize, world)
    private val chopperView = ChopperView(chopperModel, chopperTexture)
    private val chopperController = ChopperController(chopperModel, joystick.touchpad)

    //Asteroid
    private val asteroidModel = AsteroidModel(10, Vector3(0F,0F, 0F), asteroidTextureSize, world)
    private val asteroidView = AsteroidView(asteroidModel, asteroidTexture)


    init {
        Gdx.input.inputProcessor = stage
        stage.addActor(joystick.touchpad)
    }

    override fun update(dt: Float) {
        chopperController.moveChopper()
        asteroidModel.moveAsteroid()
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        sb.begin()
        chopperView.draw(sb)
        asteroidView.draw(sb)
        //asteroidView.draw(sb)

        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()

        // Render Box2D world using the debug renderer
        val debugMatrix = Matrix4(cam.combined)
        debugMatrix.scale(1 / PIXELS_TO_METERS, 1 / PIXELS_TO_METERS, 1f)
        box2DDebugRenderer.render(world, debugMatrix)

    }
}