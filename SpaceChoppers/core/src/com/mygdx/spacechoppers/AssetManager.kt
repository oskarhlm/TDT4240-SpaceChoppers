package com.mygdx.spacechoppers

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

object AssetManager {
    val manager = com.badlogic.gdx.assets.AssetManager()
    var playSoundEffects = true;

    private const val LASER_SOUND = "sounds/laser.mp3"
    private const val BG_MUSIC = "sounds/background_music.mp3"

    private const val ASTEROID_SHEET = "asteroid-sheet.png"
    private const val HELI_IMG_1 = "heli_img/Chopper_1.png"
    private const val HELI_IMG_2 = "heli_img/Chopper_2.png"
    private const val HELI_IMG_3 = "heli_img/Chopper_3.png"
    private const val HELI_IMG_4 = "heli_img/Chopper_4.png"
    private const val MENU_BG = "menu_bg.png"
    private const val MENU_BG_CLEAN = "menu_bg_clean.png"
    private const val EXPLOSION = "explosion.png"
    private const val BLANK = "blank.png"
    private const val LASER = "green_laser.png"
    private const val MENU_SKIN = "star-soldier/star-soldier-ui.json"
    private const val JOYSTICK_SKIN = "quantum-horizon/skin/quantum-horizon-ui.json"
    private const val BOOST_BTN_ACTIVE = "button/Boost_Active.png"
    private const val BOOST_BTN_INACTIVE = "button/Boost_Inactive.png"
    private const val RAPID_FIRE_BTN_ACTIVE = "button/RapidFire_Active.png"
    private const val RAPID_FIRE_BTN_INACTIVE = "button/RapidFire_Inactive.png"

    private val HELI_IMAGES = listOf(HELI_IMG_1, HELI_IMG_2, HELI_IMG_3, HELI_IMG_4)

    init {
        // Sounds and music
        manager.load(LASER_SOUND, Sound::class.java)
        manager.load(BG_MUSIC, Music::class.java)

        // Textures
        manager.load<Texture>(ASTEROID_SHEET, Texture::class.java)
        HELI_IMAGES.forEach { manager.load(it, Texture::class.java) }
        manager.load(MENU_BG, Texture::class.java)
        manager.load(MENU_BG_CLEAN, Texture::class.java)
        manager.load(EXPLOSION, Texture::class.java)
        manager.load(BLANK, Texture::class.java)
        manager.load(LASER, Texture::class.java)
        manager.load(BOOST_BTN_ACTIVE, Texture::class.java)
        manager.load(BOOST_BTN_INACTIVE, Texture::class.java)
        manager.load(RAPID_FIRE_BTN_ACTIVE, Texture::class.java)
        manager.load(RAPID_FIRE_BTN_INACTIVE, Texture::class.java)

        // Skins
        manager.load(MENU_SKIN, Skin::class.java)
        manager.load(JOYSTICK_SKIN, Skin::class.java)

        manager.finishLoading()
    }

    // Textures
    val heliTextures get() = HELI_IMAGES.map { manager.get<Texture>(it) }
    val menuBackground get() = manager.get<Texture>(MENU_BG)
    val menuBackgroundClean get() = manager.get<Texture>(MENU_BG_CLEAN)
    val explosion get() = manager.get<Texture>(EXPLOSION)
    val blank get() = manager.get<Texture>(BLANK)
    val asteroidSheet get() = manager.get<Texture>(ASTEROID_SHEET)
    val laserTexture get() = manager.get<Texture>(LASER)
    val boostButtonActive
        get() = manager.get<Texture>(BOOST_BTN_ACTIVE)
    val boostButtonInactive
        get() = manager.get<Texture>(BOOST_BTN_INACTIVE)
    val rapidFireButtonActive
        get() = manager.get<Texture>(RAPID_FIRE_BTN_ACTIVE)
    val rapidFireButtonInactive
        get() = manager.get<Texture>(RAPID_FIRE_BTN_INACTIVE)

    // Music
    private val backgroundMusic: Music get() = manager.get<Music>(BG_MUSIC)
    private val laserSound: Music get() = manager.get<Music>(LASER_SOUND)

    // Skins
    val menuSkin get() = manager.get<Skin>(MENU_SKIN)
    val joystickSkin get() = manager.get<Skin>(JOYSTICK_SKIN)

    fun playMusic() {
        backgroundMusic.isLooping = true
        backgroundMusic.play()
    }

    fun muteMusic() {
        backgroundMusic.pause()
    }

    fun playLaserSound() {
        if (!playSoundEffects) return
        val laserSound = manager.get("sounds/laser.mp3", Sound::class.java)
        laserSound.play()
    }
}