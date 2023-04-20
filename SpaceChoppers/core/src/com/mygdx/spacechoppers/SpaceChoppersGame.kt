package com.mygdx.spacechoppers

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.mygdx.spacechoppers.gamestates.menu.MainMenuState
import com.mygdx.spacechoppers.gamestates.menu.OptionsState
import com.mygdx.spacechoppers.utils.Preferences
import com.mygdx.spacechoppers.networking.NetworkClient

import com.mygdx.spacechoppers.utils.MenuCommon


class SpaceChoppersGame : ApplicationAdapter() {
    lateinit var sb: SpriteBatch
        private set
    lateinit var gsm: GameStateManager
        private set

    companion object {
        const val TITLE = "Space Choppers"
        val width get() = Gdx.graphics.width
        val height get() = Gdx.graphics.height

        var mapWidth: Int = 0
        var mapHeight: Int = 0
    }

    override fun create() {
        sb = SpriteBatch()
        gsm = GameStateManager(this)
        Preferences.username?.let { gsm.push(MainMenuState(gsm)) }
            ?: run { gsm.push(OptionsState(gsm)) }

        // Create network handler and fetch highscores
        val networkClient = NetworkClient.getInstance()
        networkClient.getHighscores();

        // Create music
        AssetManager.setUpMusic()
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        gsm.update(Gdx.graphics.deltaTime)
        gsm.render(Gdx.graphics.deltaTime)
    }

    override fun dispose() {
        println("Closed game")
        sb.dispose()
        MenuCommon.skin.dispose()
        AssetManager.manager.dispose()
    }
}