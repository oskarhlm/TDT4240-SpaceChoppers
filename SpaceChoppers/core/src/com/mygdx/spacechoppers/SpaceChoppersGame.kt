package com.mygdx.spacechoppers

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.mygdx.spacechoppers.gamestates.menu.MainMenuState
import com.mygdx.spacechoppers.gamestates.menu.UsernamePromptState
import com.mygdx.spacechoppers.utils.Preferences
import com.mygdx.spacechoppers.networking.NetworkClient

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
        Preferences.username?.let { gsm.push(MainMenuState(gsm)) }
            ?: run { gsm.push(UsernamePromptState(gsm)) }
        // Create network handler and fetch highscores
        val networkClient = NetworkClient.getInstance()
        networkClient.getHighscores();

        // Create asset manager and start playing music

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