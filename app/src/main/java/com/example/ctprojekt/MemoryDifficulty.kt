package com.example.ctprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MemoryDifficulty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_difficulty)
    }
    fun onEasyDifficulty(view: View){
        val intent = Intent(this, MemoryEasy::class.java)
        startActivity(intent)
    }

    fun onMediumDifficulty(view: View){
        val intent = Intent(this, MemoryMedium::class.java)
        startActivity(intent)
    }

    fun onHardDifficulty(view: View){
        val intent = Intent(this, MemoryHard::class.java)
        startActivity(intent)
    }

    fun onSweatDifficulty(view: View){
        val intent = Intent(this, MemorySweat::class.java)
        startActivity(intent)
    }
}