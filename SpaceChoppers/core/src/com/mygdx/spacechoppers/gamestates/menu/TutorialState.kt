package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.spacechoppers.AssetManager
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.utils.MenuCommon

class TutorialState(gsm: GameStateManager) : MenuBase(gsm){

    init {
        val container = Table()
        stage.addActor(container)
        container.setFillParent(true)

        val table = Table()
        val scroll = ScrollPane(table, skin)

        val heliSprite = Sprite(AssetManager.heliTextures[0])
        heliSprite.setScale(0.01f)
        val heliImage = Image((heliSprite))

        val asteroids = arrayListOf<Image>()
        val asteroidTextures = AssetManager.asteroidSheet

        val singleTextureWidth: Int = asteroidTextures.width / 4
        val singleTextureHeight: Int = asteroidTextures.height / 4

        for (row in 0 until 4) {
            for (column in 0 until 4) {
                val texture = TextureRegion(
                    asteroidTextures,
                    column * singleTextureWidth,
                    row * singleTextureHeight,
                    singleTextureWidth,
                    singleTextureHeight
                )
                val asteroidSprite = Sprite(texture)
                asteroidSprite.setScale(0.2f)
                val asteroidImage = Image(asteroidSprite)
                asteroids.add(asteroidImage)
            }
        }

        table.top()
        table.row()
        table.add(MenuCommon.scaledLabel("How To Play", 6f)).padBottom(100f)
        table.row()
        table.add(MenuCommon.scaledLabel("Entities:", 4f)).left().padBottom(50f)
        table.row()
        table.add(MenuCommon.scaledLabel("Chopper", 3f)).left().padBottom(25f)
        table.row()
        table.add(MenuCommon.scaledLabel("The chopper is the playable entity.", 2f)).left()
        table.row()
        table.add(heliImage).center()
        table.row()
        table.add(MenuCommon.scaledLabel("You control the chopper in space.", 2f)).left().padBottom(25f)
        table.row()
        table.add(MenuCommon.scaledLabel("Asteroids", 3f)).left()
        table.row()
        table.add(asteroids[0])
        table.row()
        table.add(MenuCommon.scaledLabel("The asteroids are the enemies", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("of the chopper.", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("Asteroids are destroyed when", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("in contact with lasers", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("or the chopper.", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("The chopper loses health when", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("when in contact with asteroids.", 2f)).left().padBottom(25f)
        table.row()
        table.add(MenuCommon.scaledLabel("Laser", 3f)).left().padBottom(25f)
        table.row()
        table.add(MenuCommon.scaledLabel("Lasers are fired from the chopper", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("in the direction it is facing", 2f)).left().padBottom(25f)
        table.row()
        table.add(MenuCommon.scaledLabel("Controls:", 4f)).left().padBottom(50f)
        table.row()
        table.add(MenuCommon.scaledLabel("Hold and drag the joystick to move", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("the chopper", 2f)).left().padBottom(50f)
        table.row()
        table.add(MenuCommon.scaledLabel("Objective:", 4f)).left().padBottom(50f)
        table.row()
        table.add(MenuCommon.scaledLabel("Survive for as long as you can.", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("Increase your high score by", 2f)).left()
        table.row()
        table.add(MenuCommon.scaledLabel("destroying asteroids.", 2f)).left().padBottom(50f)
        table.pad(10f).defaults().expandX().space(8f)


        val backButton = TextButton("Back", skin)
        backButton.label.setFontScale(4f)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(MainMenuState(gsm))
            }
        })

        container.add(scroll).expand().fill().colspan(3).padTop(50f)
        container.row().space(10f).pad(20f, 0f, 20f, 0f)
        container.add(backButton).expandX().center()
    }
}