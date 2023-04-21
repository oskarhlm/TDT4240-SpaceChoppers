package com.mygdx.spacechoppers.model

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.spacechoppers.AssetManager

class ExplosionModel(x: Float, y: Float, asteroidSize: Float) {
    var x: Float
    var y: Float
    var statetime: Float
    var remove = false

    init {
        this.x = x
        this.y = y
        statetime = 0f
        if (anim == null) {
            val texture = AssetManager.explosion
            anim = Animation<Any?>(FRAME_LENGTH, *TextureRegion.split(texture, SIZE, SIZE)[0])
        }
    }

    companion object {
        const val FRAME_LENGTH = .2f
        const val SIZE = 32
        private var anim: Animation<*>? = null
    }

    fun update(deltaTime: Float) {
        statetime += deltaTime
        if (anim!!.isAnimationFinished(statetime)) {
            remove = true
        }
    }

    fun draw(spriteBatch: SpriteBatch) {
        spriteBatch.draw(
                anim!!.getKeyFrame(statetime) as TextureRegion,
                x,
                y,
                500f,
                500f)
    }
}