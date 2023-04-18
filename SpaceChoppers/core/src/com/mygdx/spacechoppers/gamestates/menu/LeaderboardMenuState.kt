package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.utils.MenuCommon.scaledLabel
import com.mygdx.spacechoppers.networking.MessageReceiver


class LeaderboardMenuState(gsm: GameStateManager) : MenuBase(gsm) {

    init {
        val container = Table()
        stage.addActor(container)
        container.setFillParent(true)

        val table = Table()
        val scroll = ScrollPane(table, skin)

        val headerScl = 3f
        table.add(scaledLabel("", headerScl))
        table.add(scaledLabel("Username", headerScl)).expandX().fillX()
        table.add(scaledLabel("Score", headerScl))

        table.pad(10f).defaults().expandX().space(8f)
        val scoresScl = 2f
        // Set leaderboard data
        val messageReceiver = MessageReceiver.getInstance()

        var index = 0;

        messageReceiver.highScores.forEach { score ->
            table.row()
            table.add(scaledLabel("${index + 1}", scoresScl))
            val usernameLabel = scaledLabel(score.username, scoresScl)
            usernameLabel.wrap = true
            table.add(usernameLabel).expandX().fillX()
            table.add(scaledLabel("${score.score}", scoresScl))
            index++;
        }




        val backButton = TextButton("Back", skin)
        backButton.label.setFontScale(4f)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(MainMenuState(gsm))
            }
        })

        container.add(scroll).expand().fill().colspan(3)
        container.row().space(10f).pad(20f, 0f, 20f, 0f)
        container.add(backButton).expandX().center()
    }


}