package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame
import org.w3c.dom.Text

class MenuState(gsm: GameStateManager) : GameState(gsm) {
    private val font = BitmapFont()
    private val skin = Skin()
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)

    init {
        font.data.scale(5f)
        Gdx.input.inputProcessor = stage

        // Create Table
        val mainTable = Table()

        // Set table to fill stage
        mainTable.setFillParent(true)

        // Set alignment of contents in the table.
        mainTable.center()

        // Create buttons
        val style = TextButtonStyle()
        style.font = font
        style.fontColor = Color.WHITE
        val playButton = TextButton("Play", style)
        val optionsButton = TextButton("Options", style)
        val exitButton = TextButton("Exit", style)

        // Add listeners to buttons
        playButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                (Gdx.app.applicationListener as SpaceChoppersGame).gsm.set(PlayState(gsm))
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
        mainTable.add(optionsButton)
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