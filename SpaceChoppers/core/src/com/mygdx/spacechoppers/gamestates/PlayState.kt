package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Screen
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.controller.ChopperController
import com.mygdx.spacechoppers.controller.LiveScoresController
import com.mygdx.spacechoppers.model.AsteroidModel
import com.mygdx.spacechoppers.model.AsteroidTextures
import com.mygdx.spacechoppers.model.ChopperModel
import com.mygdx.spacechoppers.model.Joystick
import com.mygdx.spacechoppers.view.AsteroidView
import com.mygdx.spacechoppers.view.ChopperView


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val joystick = Joystick(cam.viewportWidth)

    // Chopper
    private val chopperModel = ChopperModel(100, Vector3(100f, 100f, 0f))
    private val chopperView = ChopperView(chopperModel)
    private val chopperController = ChopperController(chopperModel, joystick.touchpad)

    //Asteroid
    private val asteroidModel = AsteroidModel(10,Vector3(0F,0F, 0F))
    private val asteroidTextures = AsteroidTextures()
    private val asteroidView = AsteroidView(asteroidModel, asteroidTextures)

    // Scores
    private val liveScoresController = LiveScoresController();


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
        liveScoresController.renderScores(sb)

        println(chopperModel.location)
        println(liveScoresController.position)

        sb.end()

        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}