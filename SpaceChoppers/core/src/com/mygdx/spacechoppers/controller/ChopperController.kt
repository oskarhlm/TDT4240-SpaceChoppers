package com.mygdx.spacechoppers.controller

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad
import com.mygdx.spacechoppers.SpaceChoppersGame
import com.mygdx.spacechoppers.interfaces.IController
import com.mygdx.spacechoppers.model.ChopperModel
import com.mygdx.spacechoppers.view.ChopperView

class ChopperController(
    touchpad: Touchpad,
    world: World
) : IController<ChopperModel, ChopperView> {

    private val view = ChopperView()
    private val model = ChopperModel(
        100, Vector3(
            SpaceChoppersGame.width / 2 - ChopperView.sprite.width / 2,
            SpaceChoppersGame.height / 2 - ChopperView.sprite.height / 2,
            100f
        ), ChopperView.getTextureSize(), world
    )

    override fun updateModel(dt: Float) {
        model.moveChopper(touchpad.knobPercentX, touchpad.knobPercentY)
    }

    fun boost() {
        model.boost()
    }

    private val touchpad: Touchpad

    init {
        this.touchpad = touchpad
    }

    val position: Vector3 get() = model.location

    override fun updateView(sb: SpriteBatch) {
        view.draw(sb, model.body, model.location, model.currentAngle)
    }
}