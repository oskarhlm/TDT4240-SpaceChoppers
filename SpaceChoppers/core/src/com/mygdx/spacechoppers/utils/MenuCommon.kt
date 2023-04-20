package com.mygdx.spacechoppers.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.mygdx.spacechoppers.AssetManager
import com.mygdx.spacechoppers.gamestates.menu.MainMenuState


internal object MenuCommon {
    fun scaledLabel(lbl: String, scl: Float): Label {
        val scaledLbl = Label(lbl, AssetManager.menuSkin)
        scaledLbl.setFontScale(scl)
        return scaledLbl
    }
}