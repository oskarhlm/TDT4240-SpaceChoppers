package com.mygdx.spacechoppers.utils

import com.badlogic.gdx.scenes.scene2d.ui.Label


internal object MenuUtils {
    fun scaledLabel(lbl: String, scl: Float): Label {
        val scaledLbl = Label(lbl, AssetManager.menuSkin)
        scaledLbl.setFontScale(scl)
        return scaledLbl
    }
}