package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad
import com.badlogic.gdx.utils.viewport.FitViewport


class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val shapeRenderer = ShapeRenderer()
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val tp = Touchpad(20f, Skin(Gdx.files.internal("neon/neon-ui.json")))

    private val touchpad_width = 300f
    private val touchpad_height = 300f
    private val touchpad_x = cam.viewportWidth / 2 - touchpad_width / 2
    private val touchpad_y = 200f

    private var circleX = 50f
    private var circleY = 50f

    override fun update(dt: Float) {}

    init {
        Gdx.input.inputProcessor = stage
        tp.setBounds(touchpad_x, touchpad_y, touchpad_width, touchpad_height)
        stage.addActor(tp)


    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        var scaler = Math.sqrt((tp.knobPercentX.toDouble() * tp.knobPercentX.toDouble()) + (tp.knobPercentY.toDouble() * tp.knobPercentY.toDouble())) * 5
        circleX = (circleX + tp.knobPercentX * scaler).toFloat()
        circleY = (circleY + tp.knobPercentY * scaler).toFloat()

        shapeRenderer.circle(circleX, circleY, 32f);
        shapeRenderer.end();



        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}