package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Container
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.gamestates.PlayState
import com.mygdx.spacechoppers.gamestates.menu.utils.MenuCommon


class MainMenuState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val skin = MenuCommon.skin

    init {
        Gdx.input.inputProcessor = stage

        // Create Table
        val mainTable = Table()
//        mainTable.debug = true

        // Set table to fill stage
        mainTable.setFillParent(true)

        // Set alignment of contents in the table.
        mainTable.center()

        // Create buttons
        val playButton = TextButton("Play", skin)
        playButton.label.setFontScale(4f)
        val leaderboardButton = TextButton("Leaderboard", skin)
        leaderboardButton.label.setFontScale(4f)
        val settingsButton = TextButton("Options", skin)
        val exitButton = TextButton("Exit", skin)
        exitButton.label.setFontScale(4f)

        // Add listeners to buttons
        playButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(PlayState(gsm))
            }
        })
        leaderboardButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(LeaderboardMenuState(gsm))
            }
        })
        settingsButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(OptionsState(gsm))
            }
        })
        exitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.app.exit()
            }
        })

        // Add buttons to table
        mainTable.columnDefaults(0).expandX().fillX()
        mainTable.add(playButton)
        mainTable.row()
        mainTable.add(leaderboardButton)
//        mainTable.row()
//        mainTable.add(settingsButton)
        mainTable.row()
        mainTable.add(exitButton)

        // Add table to
        stage.addActor(mainTable)
    }

    override fun update(dt: Float) {}

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Math.min(Gdx.graphics.deltaTime, 1 / 30f))
        stage.draw()
    }
}