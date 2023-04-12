package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.gamestates.menu.utils.MenuCommon

class OptionsState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val style = MenuCommon.style

    init {

    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun render() {
        TODO("Not yet implemented")
    }
}