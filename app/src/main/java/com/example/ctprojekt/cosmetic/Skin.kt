package com.example.ctprojekt.cosmetic

import android.graphics.Color
import android.graphics.Paint

data class Skin(var name: String?, var kosts:Int, var bought:Boolean = false, var colorBackgrund: Int = Color.BLUE, var snakeColor: Int = Color.WHITE, var colorApple:Int = Color.RED) {

}