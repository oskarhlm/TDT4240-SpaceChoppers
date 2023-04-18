package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.gamestates.PlayState
import com.mygdx.spacechoppers.gamestates.menu.utils.MenuCommon.scaledLabel
import com.mygdx.spacechoppers.gamestates.menu.utils.Preferences
import kotlin.random.Random


class CreateLobbyState(gsm: GameStateManager) : MenuBase(gsm) {
    private val users = listOf("olav", "tingeling", "ballefaen", "karius", "baktus", "jan", "banan")
    private val pin = 6969
    private val readyColor = Color(0f, 1f, 0f, 1f)
    private val notReadyColor = Color(1f, 0f, 0f, 1f)

    init {
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
        header.add(scaledLabel("PIN: $pin", headerScl))

        val table = Table()
        table.align(Align.top)
        val scroll = ScrollPane(table, skin)
        table.pad(10f).defaults().expandX().space(8f)
        val listItemScale = 3f
        users.forEach { name ->
            table.row()
            val usernameLabel = scaledLabel("- $name", listItemScale)
            usernameLabel.wrap = true
            usernameLabel.setColor(if (Random.nextBoolean()) readyColor else notReadyColor)
            table.add(usernameLabel).expandX().fillX()
        }

        val backButton = TextButton("Back", skin)
        backButton.label.setFontScale(4f)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.input.setOnscreenKeyboardVisible(false)
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
        container.add(scroll).expand().fill().colspan(2).row()
        container.add(backButton).fill().uniformX().expandX()
        container.add(startButton).fill().uniformX().expandX()
    }

    override fun show() {
        TODO("Not yet implemented")
    }

    override fun resize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        println("Dispose create-lobby")
    }
}