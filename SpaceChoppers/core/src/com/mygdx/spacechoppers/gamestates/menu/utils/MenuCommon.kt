package com.mygdx.spacechoppers.gamestates.menu.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle


internal object MenuCommon {
    val skin = Skin(Gdx.files.internal("star-soldier/star-soldier-ui.json"))
    val style = TextButton.TextButtonStyle()

    init {
        val scl = 4f

//        val textFieldStyle = skin.get(TextFieldStyle::class.java)
//        textFieldStyle.font.data.scale(scl)

//        val sliderStyle = skin.get(SliderStyle::class.java)
//        sliderStyle.background.minWidth *= scl
//        sliderStyle.background.minHeight *= scl
//        sliderStyle.knob.minWidth *= scl
//        sliderStyle.knob.minHeight *= scl
    }
}