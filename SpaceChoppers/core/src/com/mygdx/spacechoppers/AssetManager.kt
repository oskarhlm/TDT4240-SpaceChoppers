package com.mygdx.spacechoppers

import com.badlogic.gdx.graphics.Texture

object AssetManager {
    val assetManager = com.badlogic.gdx.assets.AssetManager()

    init {
        assetManager.load("asteroid-sheet.png", Texture::class.java)
        assetManager.load("helicopter_1.png", Texture::class.java)
        assetManager.finishLoading()
    }
}