package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.utils.AssetManager
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager

import kotlin.math.min

abstract class MenuBase(gsm: GameStateManager) : GameState(gsm) {
    protected val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    protected val skin = AssetManager.menuSkin
    protected var background = TextureRegion(AssetManager.menuBackgroundClean)

    init {
        Gdx.input.inputProcessor = stage
    }

    override fun update(dt: Float) {}

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        sb.begin()
        sb.draw(background, 0f, 0f)
        sb.end()

        stage.act(min(delta, 1 / 30f))
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
    }
}