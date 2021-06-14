package com.example.ctprojekt.snake

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ctprojekt.R
import com.firebase.ui.auth.AuthUI

@SuppressLint("RestrictedApi")
class SnakeControls : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake_controls)
    }

    override fun onClick(v: View?) {
    }
    init {
        val name = "coins.txt"
        val settings = AuthUI.getApplicationContext().getSharedPreferences(name,0)
        val edit  = settings.edit()


    }
}