package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions.delay
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.gamestates.PlayState
import com.mygdx.spacechoppers.utils.MenuCommon.scaledLabel
import com.mygdx.spacechoppers.utils.Preferences
import com.mygdx.spacechoppers.networking.MessageReceiver
import kotlin.random.Random


class CreateLobbyState(gsm: GameStateManager) : MenuBase(gsm) {
    private val readyColor = Color(0f, 1f, 0f, 1f)
    private val notReadyColor = Color(1f, 0f, 0f, 1f)

    init {
        // Fetch pin that was created
        val messageReceiver = MessageReceiver.getInstance()
        while (messageReceiver.lobbyID == -1) {
            delay(100F)
        }
        Preferences.lobbyID = messageReceiver.lobbyID


        val container = Table()
        container.pad(40f)
        stage.addActor(container)
        container.setFillParent(true)

        val headerScl = 4f
        val header = Table()
        header.columnDefaults(0).expandX().align(Align.left)
        header.defaults().space(40f)
        val title = scaledLabel("${Preferences.username}'s game", headerScl)
        title.wrap = true
        header.add(title).expandX().fillX().row()
        header.add(scaledLabel("PIN: ${Preferences.lobbyID}", headerScl))


        val backButton = TextButton("Back", skin)
        backButton.label.setFontScale(4f)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.input.setOnscreenKeyboardVisible(false)
                // Reset lobbyID
                messageReceiver.lobbyID = -1
                gsm.set(MainMenuState(gsm))
            }
        })

        val startButton = TextButton("Start", skin)
        startButton.label.setFontScale(4f)
        startButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.input.setOnscreenKeyboardVisible(false)
                gsm.set(PlayState(gsm))
            }
        })

        container.add(header).expandX().fillX().padBottom(40f).colspan(2).row()
//        container.add(scroll).expand().fill().colspan(2).row()
        container.add(backButton).fill().uniformX().expandX()
        container.add(startButton).fill().uniformX().expandX()
    }
}