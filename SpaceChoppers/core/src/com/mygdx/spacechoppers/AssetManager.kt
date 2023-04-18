package com.mygdx.spacechoppers

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture

object AssetManager {
    val manager = com.badlogic.gdx.assets.AssetManager()

    init {
        // Sounds and music
        manager.load("sounds/laser.mp3", Sound::class.java)
        manager.load("sounds/background_music.mp3", Music::class.java)

        // Textures
        manager.load("asteroid-sheet.png", Texture::class.java)
        manager.load("heli_img/Chopper_1.png", Texture::class.java)
        manager.load("heli_img/Chopper_2.png", Texture::class.java)
        manager.load("heli_img/Chopper_3.png", Texture::class.java)
        manager.load("heli_img/Chopper_4.png", Texture::class.java)
        manager.load("menu_bg.png", Texture::class.java)
        manager.load("menu_bg_clean.png", Texture::class.java)
        manager.finishLoading()
    }

    fun setUpMusic() {
        val backgroundMusic = manager.get("sounds/background_music.mp3", Music::class.java);
        backgroundMusic.isLooping = true
        backgroundMusic.play()
    }

    fun playLaserSound() {
        val laserSound = manager.get("sounds/laser.mp3", Sound::class.java)
        laserSound.play()
    }
}