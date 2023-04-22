package com.mygdx.spacechoppers.controller

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad
import com.badlogic.gdx.utils.Timer
import com.mygdx.spacechoppers.SpaceChoppersGame.Companion.height
import com.mygdx.spacechoppers.SpaceChoppersGame.Companion.mapHeight
import com.mygdx.spacechoppers.SpaceChoppersGame.Companion.mapWidth
import com.mygdx.spacechoppers.SpaceChoppersGame.Companion.width
import com.mygdx.spacechoppers.model.ChopperModel
import com.mygdx.spacechoppers.view.ChopperView

class ChopperController(touchpad: Touchpad, world: World?) {
    val model: ChopperModel
    private val view: ChopperView
    private val touchpad: Touchpad
    private val initialSpeedScaler = 10f
    private var speedScaler = initialSpeedScaler
    private val boostMultiplier = 3f
    private val boostDurationSeconds = 0f
    private val timer: Timer
//    val boostListeners = mutableListOf<>()

    init {
        view = ChopperView()
        this.touchpad = touchpad
        timer = Timer()
        model = ChopperModel(
            100, Vector3(
                width / 2 - view.sprite.width / 2,
                height / 2 - view.sprite.height / 2,
                100f
            ), view.textureSize, world
        )
    }

    fun moveChopper(dt: Float) {
        val deltaX = touchpad.knobPercentX
        val deltaY = touchpad.knobPercentY
        val movementMagnitude =
            Math.sqrt(Math.pow(deltaX.toDouble(), 2.0) + Math.pow(deltaY.toDouble(), 2.0))
                .toFloat() * speedScaler
        if (movementMagnitude > 0) {
            val movementNormalizedX = deltaX / movementMagnitude
            val movementNormalizedY = deltaY / movementMagnitude
            val movementSpeed = movementMagnitude * speedScaler
            model.setVelocityVector(Vector2(deltaX * movementSpeed, deltaY * movementSpeed))
            val nextXPosition = model.location.x + movementNormalizedX * movementSpeed
            val nextYPosition = model.location.y + movementNormalizedY * movementSpeed
            val canMoveInXDirection = nextXPosition >= 0 && nextXPosition <= mapWidth - 150
            val canMoveInYDirection = nextYPosition >= 0 && nextYPosition <= mapHeight - 150
            if (canMoveInXDirection && canMoveInYDirection) {
                model.location.x = nextXPosition
                model.location.y = nextYPosition
            }
        }
    }

    val textureSize: Vector2 get() = view.textureSize
    val position: Vector3 get() = model.location

    fun draw(sb: SpriteBatch) {
        view.draw(sb, model)
    }

    fun boost() {
        speedScaler *= boostMultiplier
        val task: Timer.Task = object : Timer.Task() {
            override fun run() {
                speedScaler -= 0.2.toFloat()
                if (speedScaler < initialSpeedScaler) {
                    speedScaler = initialSpeedScaler
                    cancel()
                }
            }
        }
        timer.scheduleTask(task, 0f, 0.1f)
    }
}