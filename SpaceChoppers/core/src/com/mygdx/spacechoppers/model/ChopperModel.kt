package com.mygdx.spacechoppers.model

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.Timer
import com.mygdx.spacechoppers.SpaceChoppersGame
import kotlin.math.pow
import kotlin.math.sqrt

class ChopperModel(HP: Int, Location: Vector3?, textureSize: Vector2?, world: World?) :
    Actor(HP, Location, textureSize, world) {

    private var velocityVector: Vector2
    private val initialSpeedScale = 10f
    private var speedScale = initialSpeedScale
    private val boostMultiplier = 3f
    private val timer = Timer()

    init {
        velocityVector = Vector2(0f, 0f)
    }

    fun moveChopper(dx: Float, dy: Float) {
        val movementMagnitude =
            sqrt(dx.toDouble().pow(2.0) + dy.toDouble().pow(2.0))
                .toFloat() * speedScale
        if (movementMagnitude > 0) {
            val movementNormalizedX = dx / movementMagnitude
            val movementNormalizedY = dy / movementMagnitude
            val movementSpeed = movementMagnitude * speedScale
            setVelocityVector(Vector2(dx * movementSpeed, dy * movementSpeed))
            val nextXPosition = location.x + movementNormalizedX * movementSpeed
            val nextYPosition = location.y + movementNormalizedY * movementSpeed
            val canMoveInXDirection =
                nextXPosition >= 0 && nextXPosition <= SpaceChoppersGame.mapWidth - 150
            val canMoveInYDirection =
                nextYPosition >= 0 && nextYPosition <= SpaceChoppersGame.mapHeight - 150
            if (canMoveInXDirection && canMoveInYDirection) {
                location.x = nextXPosition
                location.y = nextYPosition
            }
        }
    }

    fun boost() {
        speedScale *= boostMultiplier
        val task: Timer.Task = object : Timer.Task() {
            override fun run() {
                speedScale -= 0.2.toFloat()
                if (speedScale < initialSpeedScale) {
                    speedScale = initialSpeedScale
                    cancel()
                }
            }
        }
        timer.scheduleTask(task, 0f, 0.1f)
    }

    val currentAngle: Float
        get() = velocityVector.angleDeg()

    private fun setVelocityVector(value: Vector2) {
        velocityVector = value
    }
}