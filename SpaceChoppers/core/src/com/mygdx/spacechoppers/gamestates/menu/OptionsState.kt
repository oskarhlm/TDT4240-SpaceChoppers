package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.utils.Preferences

class OptionsState(gsm: GameStateManager) : MenuBase(gsm) {
    private val table = Table()

    init {
        table.setFillParent(true)
        table.center().pad(20f).defaults().space(10f)

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
                usernameField.text.isNotBlank().let {
                    Preferences.username = usernameField.text
                    Gdx.input.setOnscreenKeyboardVisible(false)
                    gsm.set(MainMenuState(gsm))
                }
            }
        })

        table.add(usernamePromptLabel).expandX().fillX().colspan(2).row()
        table.add(usernameField).height(80f).expandX().fill().padBottom(80f).colspan(2).row()
        table.add(backButton).fill().uniformX().expandX()
        table.add(confirmButton).fill().uniformX().expandX()

        stage.addActor(table)
    }
}