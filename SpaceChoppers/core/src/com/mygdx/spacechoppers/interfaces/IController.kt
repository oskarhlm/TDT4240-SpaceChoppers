package com.mygdx.spacechoppers.interfaces

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface IController<M, V> where M: IModel, V: IView {
    fun updateModel(dt: Float)
    fun updateView(sb: SpriteBatch)
}