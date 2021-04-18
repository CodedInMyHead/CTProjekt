package com.example.ctprojekt.snake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.ctprojekt.GameScreen
import com.example.ctprojekt.R

class SnakeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake2)
        findViewById<Button>(R.id.startBtn).setOnClickListener(this)
        findViewById<Button>(R.id.controlBtn).setOnClickListener(this)
        findViewById<Button>(R.id.returnBtn).setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.startBtn -> startActivity( Intent(this, GameActivity::class.java))
            R.id.returnBtn -> startActivity(Intent(this, GameScreen::class.java))
        }
    }
}