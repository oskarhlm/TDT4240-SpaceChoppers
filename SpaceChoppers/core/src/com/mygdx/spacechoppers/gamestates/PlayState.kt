package com.mygdx.spacechoppers.gamestates

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.mygdx.spacechoppers.GameState
import com.mygdx.spacechoppers.GameStateManager
import com.mygdx.spacechoppers.SpaceChoppersGame

class PlayState(gsm: GameStateManager) : GameState(gsm) {
    private val sr = ShapeRenderer()

    override fun update(dt: Float) {}

    override fun render() {
        sr.begin(ShapeRenderer.ShapeType.Filled)
        sr.circle(SpaceChoppersGame.width/2.toFloat() , SpaceChoppersGame.height/2.toFloat(), 80f)
        sr.end()
    }
}