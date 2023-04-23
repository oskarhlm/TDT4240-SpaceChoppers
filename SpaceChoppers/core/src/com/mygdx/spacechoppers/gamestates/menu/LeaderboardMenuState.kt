package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.data.networking.Score
import com.mygdx.spacechoppers.utils.MenuUtils.scaledLabel
import com.mygdx.spacechoppers.networking.MessageReceiver
import com.mygdx.spacechoppers.networking.NetworkClient


class LeaderboardMenuState(gsm: GameStateManager) : MenuBase(gsm) {

    init {
        NetworkClient.getInstance().getHighscores()

        val container = Table()
        stage.addActor(container)
        container.setFillParent(true)

        // Create table
        val scoreTable = Table();

        // Init table
        initTable(scoreTable)

        // Create scrolling
        val scroll = ScrollPane(scoreTable, skin)

        // Fetch leaderboard data
        val messageReceiver = MessageReceiver.getInstance()

        // Add scores
        addScoreTable(messageReceiver.highScores, scoreTable)


        val backButton = TextButton("Back", skin)
        backButton.label.setFontScale(4f)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(MainMenuState(gsm))
            }
        })

        val refreshButton = TextButton("Refresh Scores", skin)
        refreshButton.label.setFontScale(4f)
        refreshButton.addListener(object: ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                // Fetch new scores
                NetworkClient.getInstance().getHighscores()
                initTable(scoreTable)
                addScoreTable(messageReceiver.highScores, scoreTable)

            }
        })

        container.add(scroll).expand().fill().colspan(3)
        container.row().space(10f).pad(20f, 0f, 20f, 0f)
        container.add(backButton).expandX().center()
        container.row()
        container.add(refreshButton).expandX().center()
    }

    fun addScoreTable(highScores: MutableList<Score>, table: Table) {
        var index = 0;
        highScores.forEach { score ->
            table.row()
            table.add(scaledLabel("${index + 1}", 2f))
            val usernameLabel = scaledLabel(score.username, 2f)
            usernameLabel.wrap = true
            table.add(usernameLabel).expandX().fillX()
            table.add(scaledLabel("${score.score}", 2f))
            index++;
        }
    }

    fun initTable(table: Table) {
        // Reset table if scores are present
        table.reset()

        // Add headings
        table.add(scaledLabel("", 3f))
        table.add(scaledLabel("Username", 3f)).expandX().fillX()
        table.add(scaledLabel("Score", 3f))

        table.pad(10f).defaults().expandX().space(8f)
    }
}