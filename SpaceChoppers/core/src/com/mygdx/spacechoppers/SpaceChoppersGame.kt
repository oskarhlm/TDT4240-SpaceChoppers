package com.mygdx.spacechoppers

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.mygdx.spacechoppers.gamestates.menu.MainMenuState

class SpaceChoppersGame : ApplicationAdapter() {
    lateinit var sb: SpriteBatch
        private set
    lateinit var gsm: GameStateManager
        private set

    companion object {
        const val TITLE = "Space Choppers"
        val width get() = Gdx.graphics.width
        val height get() = Gdx.graphics.height
    }

    override fun create() {
        sb = SpriteBatch()
        gsm = GameStateManager(this)
        gsm.push(MainMenuState(gsm))
//        gsm.push(TestMenuState(gsm))
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        gsm.update(Gdx.graphics.deltaTime)
        gsm.render()
    }

    override fun dispose() {
        sb.dispose()
    }
}