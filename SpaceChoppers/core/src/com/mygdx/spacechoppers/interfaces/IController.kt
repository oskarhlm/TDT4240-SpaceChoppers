package com.mygdx.spacechoppers.interfaces

interface IController<M, V> where V: IView<M> {
    fun updateView()
}