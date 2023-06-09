package com.mygdx.spacechoppers.utils

import com.badlogic.gdx.Gdx


object Preferences {

    // Aliases
    private const val PREF_MUSIC_ENABLED = "music.enabled"
    private const val PREF_SOUND_ENABLED = "sound.enabled"
    private const val PREF_USERNAME = "username"
    private const val PREF_LOBBY_ID = "lobbyID"

    private const val PREFS_NAME = "appPreferences"
    private val prefs get() = Gdx.app.getPreferences(PREFS_NAME)

    var musicEnabled
        get() = prefs.getBoolean(PREF_MUSIC_ENABLED, true)
        set(value) = prefs.putBoolean(PREF_MUSIC_ENABLED, value).flush()

    var soundEnabled
        get() = prefs.getBoolean(PREF_SOUND_ENABLED, true)
        set(value) = prefs.putBoolean(PREF_SOUND_ENABLED, value).flush()

    var username
        get() = prefs.getString(PREF_USERNAME, null)
        set(value) = prefs.putString(PREF_USERNAME, value).flush()

    var lobbyID
        get() = prefs.getInteger(PREF_LOBBY_ID, -1)
        set(value) = prefs.putInteger(PREF_LOBBY_ID, value).flush()
}
