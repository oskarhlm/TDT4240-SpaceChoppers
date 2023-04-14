package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.gamestates.menu.utils.MenuCommon


class LeaderboardMenuState(gsm: GameStateManager) : GameState(gsm) {
    private val stage = Stage(FitViewport(cam.viewportWidth, cam.viewportHeight), sb)
    private val skin = MenuCommon.skin
    var leaderboard = listOf(
        "jan",
        "banan",
        "ththththththehehehehthhehhthe",
        "tang",
        "stang",
        "bangbang",
        "js bach",
        "thinghitng thign",
        "ehehehehe",
        "yazpllplp",
        "jan",
        "banan",
        "ththththththehehehehthhehhthe",
        "tang",
        "stang",
        "bangbang",
        "js bach",
        "thinghitng thign",
        "ehehehehe",
        "yazpllplp",
        "jan",
        "banan",
        "ththththththehehehehthhehhthe",
        "tang",
        "stang",
        "bangbang",
        "js bach",
        "thinghitng thign",
        "ehehehehe",
        "yazpllplp",
        "jan",
        "banan",
        "ththththththehehehehthhehhthe",
        "tang",
        "stang",
        "bangbang",
        "js bach",
        "thinghitng thign",
        "ehehehehe",
        "yazpllplp",
        "jan",
        "banan",
        "ththththththehehehehthhehhthe",
        "tang",
        "stang",
        "bangbang",
        "js bach",
        "thinghitng thign",
        "ehehehehe",
        "yazpllplp",
        "jan",
        "banan",
        "ththththththehehehehthhehhthe",
        "tang",
        "stang",
        "bangbang",
        "js bach",
        "thinghitng thign",
        "ehehehehe",
        "yazpllplp"
    )
    val score = 69

    init {
        Gdx.input.inputProcessor = stage

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
        leaderboard.forEachIndexed { index, name ->
            table.row()
            table.add(scaledLabel("${index + 1}", scoresScl))
            val usernameLabel = scaledLabel(name, scoresScl)
            usernameLabel.wrap = true
            table.add(usernameLabel).expandX().fillX()
            table.add(scaledLabel("$score", scoresScl))
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

    override fun update(dt: Float) {}

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Math.min(Gdx.graphics.deltaTime, 1 / 30f))
        stage.draw()
    }

    fun scaledLabel(lbl: String, scl: Float): Label {
        val scaledLbl = Label(lbl, skin)
        scaledLbl.setFontScale(scl)
        return scaledLbl
    }
}