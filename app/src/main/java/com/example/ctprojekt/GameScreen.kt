package com.example.ctprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GameScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
    }


    fun startTicTacToe(view: View){
        val intent = Intent(this, TicTacToe::class.java)
        startActivity(intent)
    }

    fun startMemory(view: View){
        val intent = Intent(this, Memory::class.java)
        startActivity(intent)
    }

    fun startSnake(view: View){
        val intent = Intent(this, Snake::class.java)
        startActivity(intent)
    }
}