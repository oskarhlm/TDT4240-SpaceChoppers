package com.mygdx.spacechoppers

import com.badlogic.gdx.graphics.Texture

object AssetManager {
    val manager = com.badlogic.gdx.assets.AssetManager()

    init {
        manager.load("asteroid-sheet.png", Texture::class.java)
        manager.load("heli_img/Chopper_1.png", Texture::class.java)
        manager.load("heli_img/Chopper_2.png", Texture::class.java)
        manager.load("heli_img/Chopper_3.png", Texture::class.java)
        manager.load("heli_img/Chopper_4.png", Texture::class.java)
        manager.load("menu_bg.png", Texture::class.java)
        manager.load("menu_bg_clean.png", Texture::class.java)
        manager.finishLoading()
    }
}