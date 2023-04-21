package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.spacechoppers.GameStateManager

class GameOverState(gsm : GameStateManager, score: Int) : MenuBase(gsm) {

    private val table = Table()

    init {
        table.setFillParent(true)
        table.center().pad(20f).defaults().space(10f)

        // GAME OVER TEXT
        val gameOverLabel = Label("GAME OVER", skin)
        gameOverLabel.setFontScale(4f)

        // GAME OVER TEXT
        val scoreLabel = Label("Your score: NULL", skin)
        scoreLabel.setFontScale(4f)

        // BACK BUTTON
        val backButton = TextButton("Main menu", skin)
        backButton.label.setFontScale(4f)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.input.setOnscreenKeyboardVisible(false)
                gsm.set(MainMenuState(gsm))
            }
        })

        table.add(gameOverLabel).expandX().fillX().row()
        table.add(scoreLabel).expandX().fillX().row()
        table.add(backButton).fill().uniformX().expandX()

        stage.addActor(table)
    }
}