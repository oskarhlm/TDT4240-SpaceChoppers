package com.mygdx.spacechoppers

import com.badlogic.gdx.graphics.Texture

object AssetManager {
    val manager = com.badlogic.gdx.assets.AssetManager()

    init {
        manager.load("asteroid-sheet.png", Texture::class.java)
        manager.load("helicopter_1.png", Texture::class.java)
        manager.load("menu_bg.png", Texture::class.java)
        manager.load("menu_bg_clean.png", Texture::class.java)
        manager.finishLoading()
    }
}