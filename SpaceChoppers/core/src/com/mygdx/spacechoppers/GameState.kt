package com.mygdx.spacechoppers

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Disposable

abstract class GameState(protected var gsm: GameStateManager) : Screen {
    protected val game: SpaceChoppersGame = gsm.game
    protected val sb: SpriteBatch = gsm.game.sb
    protected lateinit var cam: OrthographicCamera

    abstract fun update(dt: Float)

    init {
        setupCamera(SpaceChoppersGame.width.toFloat(), SpaceChoppersGame.height.toFloat())
    }

    protected fun setupCamera(viewportWidth: Float, viewportHeight: Float) {
        cam = OrthographicCamera()
        cam.setToOrtho(false, viewportWidth, viewportHeight)
    }

    override fun show() {
        TODO("Not yet implemented")
    }

    override fun resize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun hide() {
        TODO("Not yet implemented")
    }

}