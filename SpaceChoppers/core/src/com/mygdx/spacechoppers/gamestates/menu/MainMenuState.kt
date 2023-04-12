package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.gamestates.PlayState
import com.mygdx.spacechoppers.gamestates.menu.utils.MenuCommon

class MainMenuState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val style = MenuCommon.style

    init {
        Gdx.input.inputProcessor = stage

        // Create Table
        val mainTable = Table()

        // Set table to fill stage
        mainTable.setFillParent(true)

        // Set alignment of contents in the table.
        mainTable.center()

        // Create buttons
        val playButton = TextButton("Play", style)
        val settingsButton = TextButton("Options", style)
        val exitButton = TextButton("Exit", style)

        // Add listeners to buttons
        playButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(PlayState(gsm))
            }
        })
        exitButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.app.exit()
            }
        })

        // Add buttons to table
        mainTable.add(playButton)
        mainTable.row()
        mainTable.add(settingsButton)
        mainTable.row()
        mainTable.add(exitButton)

        // Add table to stage
        stage.addActor(mainTable)
    }

    override fun update(dt: Float) {}

    override fun render() {
        stage.draw()
    }
}