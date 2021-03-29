package com.example.ctprojekt.snake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.ctprojekt.R

class SnakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake2)

    }
    fun onFunction(view: View?){
        startActivity( Intent(this, GameActivity::class.java))
    }
}