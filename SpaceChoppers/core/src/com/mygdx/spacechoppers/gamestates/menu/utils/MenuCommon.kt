package com.mygdx.spacechoppers.gamestates.menu.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

internal object MenuCommon {
    val font = BitmapFont()
    val skin = Skin(Gdx.files.internal("neon/neon-ui.json"))
    val style = TextButton.TextButtonStyle()

    init {
        font.data.scale(5f)
        style.font = font
        style.fontColor = Color.WHITE
    }
}