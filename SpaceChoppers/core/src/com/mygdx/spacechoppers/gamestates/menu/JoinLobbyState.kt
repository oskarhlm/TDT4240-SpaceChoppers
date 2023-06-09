package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.gamestates.PlayState
import com.mygdx.spacechoppers.networking.NetworkClient
import com.mygdx.spacechoppers.utils.Preferences

class JoinLobbyState(gsm: GameStateManager) : MenuBase(gsm) {
    init {
        val networkClient = NetworkClient.getInstance()

        val table = Table()
        table.setFillParent(true)
        table.center().pad(20f).defaults().space(10f)

        val gamePinPromptLabel = Label("Insert game pin:", skin)
        gamePinPromptLabel.setFontScale(4f)

        val gamePinField = TextField(null, skin)
        gamePinField.style.font.data.setScale(2f)

        val backButton = TextButton("Back", skin)
        backButton.label.setFontScale(4f)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.input.setOnscreenKeyboardVisible(false)
                gsm.set(MainMenuState(gsm))
            }
        })

        val joinButton = TextButton("Join", skin)
        joinButton.label.setFontScale(4f)
        joinButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.input.setOnscreenKeyboardVisible(false)
                Preferences.lobbyID = Integer.parseInt(gamePinField.text)
                networkClient.joinLobby(Integer.parseInt(gamePinField.text), Preferences.username);

                gsm.set(PlayState(gsm))
            }
        })

        table.add(gamePinPromptLabel).expandX().fillX().colspan(2).row()
        table.add(gamePinField).height(80f).expandX().fill().padBottom(80f).colspan(2).row()
        table.add(backButton).fill().uniformX().expandX()
        table.add(joinButton).fill().uniformX().expandX()

        stage.addActor(table)
    }
}