package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.Value
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.gamestates.menu.utils.MenuCommon

class OptionsState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val skin = MenuCommon.skin
    private val style = MenuCommon.style

    init {
        Gdx.input.inputProcessor = stage

        val musicVolumeLabel = TextButton("Music Volume", style)
        val musicVolumeSlider = Slider(0f, 100f, 5f, false, skin)
        val soundVolumeLabel = TextButton("Sound Volume", style)
        val soundVolumeSlider = Slider(0f, 100f, 5f, false, skin)
        val backButton = TextButton("Back", style)

        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(MainMenuState(gsm))
            }
        })

        val table = Table()
        table.debug = true
        table.setFillParent(true)
        table.center()
        table.columnDefaults(0).expandX()
        table.columnDefaults(1).width(Value.percentWidth(.7f))

        table.add(musicVolumeLabel)
        table.add(musicVolumeSlider)
        table.row()
        table.add(soundVolumeLabel)
        table.add(soundVolumeSlider)
        table.row()
        table.add(backButton).colspan(2)

        stage.addActor(table)
    }

    override fun update(dt: Float) {}

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }
}