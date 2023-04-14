package com.mygdx.spacechoppers

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3

abstract class GameState(protected var gsm: GameStateManager) {
    protected val game: SpaceChoppersGame = gsm.game
    protected val sb: SpriteBatch = gsm.game.sb
    protected lateinit var cam: OrthographicCamera

    abstract fun update(dt: Float)
    abstract fun render()

    init {
        setupCamera(SpaceChoppersGame.width.toFloat(), SpaceChoppersGame.height.toFloat())
    }

    protected fun setupCamera(viewportWidth: Float, viewportHeight: Float) {
        cam = OrthographicCamera()
        cam.setToOrtho(false, viewportWidth, viewportHeight)
    }
}