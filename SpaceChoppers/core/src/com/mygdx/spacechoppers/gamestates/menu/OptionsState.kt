package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.spacechoppers.AssetManager
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.utils.Preferences

class OptionsState(gsm: GameStateManager) : MenuBase(gsm) {
    private val table = Table()
    private val br = Label("--------------------------", skin)

    private fun createCheckbox(labelText: String): CheckBox {
        val checkbox = CheckBox(labelText, skin)
        checkbox.label.setScale(16f)
        checkbox.imageCell.padLeft(20f)

        checkbox.style.checkboxOn.minWidth = 64f
        checkbox.style.checkboxOn.minHeight = 64f

        checkbox.style.checkboxOff.minWidth = 64f
        checkbox.style.checkboxOff.minHeight = 64f

        return checkbox
    }

    init {
        table.setFillParent(true)
        table.center().pad(20f).defaults().space(10f)
//        table.debug = true

        val musicCheckbox = createCheckbox("Music")
        musicCheckbox.isChecked = Preferences.musicEnabled
        musicCheckbox.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                Preferences.musicEnabled = musicCheckbox.isChecked
                if (musicCheckbox.isChecked) AssetManager.playMusic()
                else AssetManager.muteMusic()
            }
        })

        val soundEffectCheckbox = createCheckbox("Sound")
        soundEffectCheckbox.isChecked = Preferences.soundEnabled
        soundEffectCheckbox.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                Preferences.soundEnabled = soundEffectCheckbox.isChecked
                AssetManager.playSoundEffects = !AssetManager.playSoundEffects
            }
        })

        val usernamePromptLabel = Label("Set username:", skin)
        usernamePromptLabel.setFontScale(4f)
        val usernameField = TextField(null, skin)
        usernameField.style.font.data.setScale(2f)

        val backButton = TextButton("Back", skin)
        backButton.label.setFontScale(4f)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.input.setOnscreenKeyboardVisible(false)
                gsm.set(MainMenuState(gsm))
            }
        })

        val confirmButton = TextButton("Confirm", skin)
        confirmButton.label.setFontScale(4f)
        confirmButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (usernameField.text.isNotBlank()) {
                    Preferences.username = usernameField.text
                    Gdx.input.setOnscreenKeyboardVisible(false)
                    gsm.set(MainMenuState(gsm))
                }
            }
        })

        table.add(musicCheckbox).fill().uniformX().expandX().padBottom(60f)
        table.add(soundEffectCheckbox).fill().uniformX().expandX().padBottom(60f)
        table.row()
        table.add(usernamePromptLabel).expandX().fillX().colspan(2).row()
        table.add(usernameField).height(80f).expandX().fill().padBottom(80f).colspan(2).row()
        table.add(backButton).fill().uniformX().expandX()
        table.add(confirmButton).fill().uniformX().expandX()

        stage.addActor(table)
    }
}