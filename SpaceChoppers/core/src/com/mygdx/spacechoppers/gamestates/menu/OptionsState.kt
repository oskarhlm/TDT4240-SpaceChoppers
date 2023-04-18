package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.gamestates.menu.utils.MenuCommon


class OptionsState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val skin = MenuCommon.skin
    init {
        Gdx.input.inputProcessor = stage

        val musicVolumeLabel = Label("Music", skin)
        val musicVolumeSlider = Slider(0f, 1f, 0.1f, false, skin)
        musicVolumeSlider.setSize(musicVolumeSlider.width * 4f, musicVolumeSlider.height * 4f)
        val soundVolumeLabel = Label("Sound", skin)
        val soundVolumeSlider = Slider(0f, 1f, 0.1f, false, skin)
        soundVolumeSlider.setSize(soundVolumeSlider.width * 4f, soundVolumeSlider.height * 4f)
        val backButton = TextButton("Back", skin)

        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(MainMenuState(gsm))
            }
        })

        val table = Table()
        table.debug = true
        table.setFillParent(true)
        table.center()
        table.columnDefaults(0).uniform()
        table.columnDefaults(1).uniform()

        table.add(musicVolumeLabel)
        table.add(musicVolumeSlider)
        table.row()
        table.add(soundVolumeLabel)
        table.add(soundVolumeSlider)
        table.row().padBottom(40f)
        table.add(backButton).colspan(2)

        stage.addActor(table)
    }

    override fun update(dt: Float) {}
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

    override fun render(dt: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(dt)
        stage.draw()
    }

    override fun dispose() {
        println("Dispose options")
    }
}