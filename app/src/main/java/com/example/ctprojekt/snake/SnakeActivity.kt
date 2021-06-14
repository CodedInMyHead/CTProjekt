package com.example.ctprojekt.snake

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.ctprojekt.GameScreen
import com.example.ctprojekt.R
import com.example.ctprojekt.cosmetic.SnakeSelection
import com.firebase.ui.auth.AuthUI

class SnakeActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake2)
        val filename = "coins.txt"
        val settings: SharedPreferences = AuthUI.getApplicationContext()
            .getSharedPreferences(filename, 0)
        val editor = settings.edit()
        findViewById<TextView>(R.id.textView2).text = settings.getInt("highScore", 0).toString()
        findViewById<Button>(R.id.startBtn).setOnClickListener(this)
        findViewById<Button>(R.id.controlBtn).setOnClickListener(this)
        findViewById<Button>(R.id.returnBtn).setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.startBtn -> startActivity( Intent(this, GameActivity::class.java))
            R.id.returnBtn -> startActivity(Intent(this, GameScreen::class.java))
            R.id.controlBtn -> startActivity(Intent(this, SnakeSelection::class.java))
        }
    }
}