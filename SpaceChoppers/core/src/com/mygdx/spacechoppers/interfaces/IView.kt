package com.mygdx.spacechoppers.interfaces

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface IView<Model> {
    fun draw(sb: SpriteBatch, model: Model)
}