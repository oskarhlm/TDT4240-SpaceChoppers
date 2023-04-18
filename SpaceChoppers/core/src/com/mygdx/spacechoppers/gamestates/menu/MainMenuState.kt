package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Container
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.gamestates.PlayState
import com.mygdx.spacechoppers.gamestates.menu.utils.MenuCommon
import com.mygdx.spacechoppers.gamestates.menu.utils.Preferences


class MainMenuState(gsm: GameStateManager) : MenuBase(gsm) {
    init {
        // Create Table
        val mainTable = Table()

        // Set table to fill stage
        mainTable.setFillParent(true)

        // Set alignment of contents in the table.
        mainTable.center()

        val welcomeLabel = Label("Welcome, ${Preferences.username}!", skin)
        welcomeLabel.setFontScale(2f)
        welcomeLabel.setAlignment(Align.center)

        // Create buttons
        val buttonFontScale = 4f
        val createButton = TextButton("Create", skin)
        createButton.label.setFontScale(buttonFontScale)
        val joinButton = TextButton("Join", skin)
        joinButton.label.setFontScale(buttonFontScale)
        val leaderboardButton = TextButton("Leaderboard", skin)
        leaderboardButton.label.setFontScale(buttonFontScale)
        val settingsButton = TextButton("Options", skin)
        settingsButton.label.setFontScale(buttonFontScale)
        val exitButton = TextButton("Exit", skin)
        exitButton.label.setFontScale(buttonFontScale)

        // Add listeners to buttons
        createButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(CreateLobbyState(gsm))
            }
        })
        joinButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(JoinLobbyState(gsm))
            }
        })
        leaderboardButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(LeaderboardMenuState(gsm))
            }
        })
        settingsButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
//                gsm.set(OptionsState(gsm))
                gsm.set(UsernamePromptState(gsm))
            }
        })
        exitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.app.exit()
            }
        })

        // Add buttons to table
        mainTable.columnDefaults(0).expandX().fillX()
        Preferences.username?.let { mainTable.add(welcomeLabel).center().row() }
        mainTable.add(createButton).row()
        mainTable.add(joinButton).row()
        mainTable.add(leaderboardButton).row()
        mainTable.add(settingsButton).row()
        mainTable.add(exitButton).row()

        // Add table to
        stage.addActor(mainTable)
    }
}