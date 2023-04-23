package com.mygdx.spacechoppers.gamestates.menu

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.spacechoppers.utils.AssetManager
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.utils.MenuUtils

class TutorialState(gsm: GameStateManager) : MenuBase(gsm){

    init {
        val container = Table()
        stage.addActor(container)
        container.setFillParent(true)

        val table = Table()
        val scroll = ScrollPane(table, skin)

        // Load Textures
        val chopperTexture = AssetManager.heliTextures[0]
        val laserTexture = AssetManager.laserTexture
        val asteroidTextures = AssetManager.asteroidSheet
        val joystickSkin = AssetManager.joystickSkin.getSprite("touchpad")

        // Encapsulate Textures in Images
        val chopperImage = Image(chopperTexture)
        val laserImage = Image(laserTexture)
        val laserImage2 = Image(laserTexture)
        val asteroids = arrayListOf<Image>()
        val joystickImage = Image(joystickSkin)
        joystickImage.color = Color.PURPLE
        joystickImage.setScale(2f)
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
                val asteroidImage = Image(texture)
                asteroids.add(asteroidImage)
            }
        }

        table.top()
        table.row()
        table.add(MenuUtils.scaledLabel("How To Play", 6f)).padBottom(25f)
        table.row()
        table.add(MenuUtils.scaledLabel("Entities:", 4.8f)).center().padBottom(25f)
        table.row()
        table.add(MenuUtils.scaledLabel("Chopper", 3f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("The chopper is the playable entity.", 1.8f)).center()
        table.row()
        table.add(chopperImage).center().padBottom(10f)
        table.row()
        table.add(MenuUtils.scaledLabel("You control the chopper in space.", 1.8f)).center().padBottom(25f)
        table.row()
        table.add(MenuUtils.scaledLabel("Asteroids", 3f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("The asteroids are the enemies of", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("the chopper.", 1.8f)).center()
        table.row()
        val asteroidRow = Table()
        for (i in 5..10) {
            asteroidRow.add(asteroids[i]).padRight(10f)
        }
        table.add(asteroidRow).center()
        table.row()
        table.add(MenuUtils.scaledLabel("Asteroids are destroyed when in", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("contact with lasers", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("or the chopper.", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("The chopper loses health when in", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("contact with asteroids.", 1.8f)).center().padBottom(25f)
        table.row()
        table.add(MenuUtils.scaledLabel("Lasers", 3f)).center()
        table.row()
        val laserRow = Table()
        laserRow.add(laserImage).padRight(10f)
        laserRow.add(laserImage2)
        table.add(laserRow).center()
        table.row()
        table.add(MenuUtils.scaledLabel("Lasers are automatically fired", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("from the chopper", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("in the direction it is facing.", 1.8f)).center().padBottom(25f)
        table.row()
        table.add(MenuUtils.scaledLabel("Controls:", 4.8f)).center().padBottom(25f)
        table.row()
        table.add(MenuUtils.scaledLabel("Hold and drag the joystick", 1.8f)).center()
        table.row()
        table.add(joystickImage).padTop(75f).padBottom(10f).padRight(90f).center()
        table.row()
        table.add(MenuUtils.scaledLabel("to move the chopper", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("in your desired direction", 1.8f)).center().padBottom(25f)
        table.row()
        table.add(MenuUtils.scaledLabel("Objective:", 4.8f)).center().padBottom(25f)
        table.row()
        table.add(MenuUtils.scaledLabel("Survive for as long as you can.", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("Increase your high score by", 1.8f)).center()
        table.row()
        table.add(MenuUtils.scaledLabel("dodging and destroying asteroids.", 1.8f)).center().padBottom(50f)
        table.pad(10f).defaults().expandX().space(8f)


        val backButton = TextButton("Back", skin)
        backButton.label.setFontScale(4f)
        backButton.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                gsm.set(MainMenuState(gsm))
            }
        })

        container.add(scroll).expand().fill().colspan(3).padTop(25f)
        container.row().space(10f).pad(20f, 0f, 20f, 0f)
        container.add(backButton).expandX().center()
    }
}