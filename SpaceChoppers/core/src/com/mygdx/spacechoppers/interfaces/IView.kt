package com.mygdx.spacechoppers.interfaces

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface IView<M> {
    fun draw(sb: SpriteBatch)
    fun setModel(model: M)
}