package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.utils.MenuCommon

abstract class MenuBase(gsm: GameStateManager) : GameState(gsm) {
    protected val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    protected val skin = MenuCommon.skin

    init {
        Gdx.input.inputProcessor = stage
    }

    override fun update(dt: Float) {}

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Math.min(delta, 1 / 30f))
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
    }
}