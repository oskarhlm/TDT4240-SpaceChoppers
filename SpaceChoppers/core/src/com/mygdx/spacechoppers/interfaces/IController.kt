package com.mygdx.spacechoppers.interfaces

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface IController<M, V> where V: IView<M> {
    fun updateModel(dt: Float)
    fun updateView(sb: SpriteBatch)
}