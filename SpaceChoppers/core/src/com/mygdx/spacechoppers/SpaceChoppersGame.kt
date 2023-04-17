package com.mygdx.spacechoppers

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.mygdx.spacechoppers.gamestates.menu.MainMenuState
import com.mygdx.spacechoppers.gamestates.menu.UsernamePromptState
import com.mygdx.spacechoppers.gamestates.menu.utils.Preferences

class SpaceChoppersGame : ApplicationAdapter() {
    lateinit var sb: SpriteBatch
        private set
    lateinit var gsm: GameStateManager
        private set
    val assetManager : AssetManager = AssetManager()

    init {
        assetManager.load("asteroid-sheet.png", Texture::class.java)
        assetManager.load("helicopter_1-png", Texture::class.java)
    }

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
    }

    override fun render() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        gsm.update(Gdx.graphics.deltaTime)
        gsm.render()
    }

    override fun dispose() {
        println("Closed game")
        sb.dispose()
        assetManager.dispose()
    }
}