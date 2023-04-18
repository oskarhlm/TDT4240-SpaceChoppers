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
import com.mygdx.spacechoppers.gamestates.menu.MainMenuState


internal object MenuCommon {
    val skin = Skin(Gdx.files.internal("star-soldier/star-soldier-ui.json"))
    val style = TextButton.TextButtonStyle()

    fun scaledLabel(lbl: String, scl: Float): Label {
        val scaledLbl = Label(lbl, skin)
        scaledLbl.setFontScale(scl)
        return scaledLbl
    }
}