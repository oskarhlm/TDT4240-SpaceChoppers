package com.mygdx.spacechoppers.gamestates.menu.utils

import com.badlogic.gdx.Gdx


object Preferences {
    // Aliases
    private const val PREF_MUSIC_VOLUME = "volume"
    private const val PREF_MUSIC_ENABLED = "music.enabled"
    private const val PREF_SOUND_VOL = "sound"
    private const val PREF_SOUND_ENABLED = "sound.enabled"
    private const val PREF_USERNAME = "username"

    private const val PREFS_NAME = "appPreferences"
    private val prefs get() = Gdx.app.getPreferences(PREFS_NAME)

    var musicVolume
        get() = prefs.getFloat(PREF_MUSIC_VOLUME, 0.5f)
        set(value) = prefs.putFloat(PREF_MUSIC_VOLUME, value).flush()

    var musicEnabled
        get() = prefs.getBoolean(PREF_MUSIC_ENABLED, true)
        set(value) = prefs.putBoolean(PREF_MUSIC_ENABLED, value).flush()

    var soundVolume
        get() = prefs.getFloat(PREF_SOUND_VOL, 0.5f)
        set(value) = prefs.putFloat(PREF_SOUND_VOL, value).flush()

    var soundEnabled
        get() = prefs.getBoolean(PREF_SOUND_ENABLED, true)
        set(value) = prefs.putBoolean(PREF_SOUND_ENABLED, value).flush()

    var username: String?
        get() = prefs.getString(PREF_USERNAME, null)
        set(value) = prefs.putString(PREF_USERNAME, value).flush()
}
