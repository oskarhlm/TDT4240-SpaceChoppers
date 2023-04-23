package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.mygdx.spacechoppers.utils.AssetManager
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.utils.Preferences
import com.mygdx.spacechoppers.networking.NetworkClient


class MainMenuState(gsm: GameStateManager) : MenuBase(gsm) {
    init {
        // Create network client
        val networkClient = NetworkClient.getInstance()

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
        val tutorialButton = TextButton("How To Play", skin)
        tutorialButton.label.setFontScale(buttonFontScale)
        val settingsButton = TextButton("Options", skin)
        settingsButton.label.setFontScale(buttonFontScale)
        val exitButton = TextButton("Exit", skin)
        exitButton.label.setFontScale(buttonFontScale)

        // Add listeners to buttons
        createButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                // Tell network client to start game
                networkClient.createLobby(Preferences.username)
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
        tutorialButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(TutorialState(gsm))
            }
        })
        settingsButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
//                gsm.set(OptionsState(gsm))
                gsm.set(OptionsState(gsm))
            }
        })
        exitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.app.exit()
            }
        })

        // Override default background
        background = TextureRegion(AssetManager.menuBackground)

        // Add buttons to table
        mainTable.columnDefaults(0).expandX().fillX()
        Preferences.username?.let { mainTable.add(welcomeLabel).center().row() }
        mainTable.add(createButton).row()
        mainTable.add(joinButton).row()
        mainTable.add(leaderboardButton).row()
        mainTable.add(tutorialButton).row()
        mainTable.add(settingsButton).row()
        mainTable.add(exitButton).row()

        // Add table to
        stage.addActor(mainTable)
    }
}