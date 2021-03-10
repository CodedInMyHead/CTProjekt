package com.example.ctprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MemoryDifficulty : AppCompatActivity() {

    var x = 0
    var y = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_difficulty)
    }


    fun onBabyDifficulty(view: View){
        x = 2
        y = 3
        val intent = Intent(this, Memory::class.java)
        intent.putExtra("x", ""+x)
        intent.putExtra("y", ""+y)
        startActivity(intent)

    }

    fun onEasyDifficulty(view: View){
        x = 2
        y = 5
        val intent = Intent(this, Memory::class.java)
        intent.putExtra("x", ""+x)
        intent.putExtra("y", ""+y)
        startActivity(intent)
    }

    fun onMediumDifficulty(view: View){
        x = 3
        y = 6
        val intent = Intent(this, Memory::class.java)
        intent.putExtra("x", ""+x)
        intent.putExtra("y", ""+y)
        startActivity(intent)
    }

    fun onHardDifficulty(view: View){
        x = 4
        y = 7
        val intent = Intent(this, Memory::class.java)
        intent.putExtra("x", ""+x)
        intent.putExtra("y", ""+y)
        startActivity(intent)
    }

    fun onSweatDifficulty(view: View){
        x = 5
        y = 8
        val intent = Intent(this, Memory::class.java)
        intent.putExtra("x", ""+x)
        intent.putExtra("y", ""+y)
        startActivity(intent)
    }

    fun onDeityDifficulty(view: View){
        x = 5
        y = 10
        val intent = Intent(this, Memory::class.java)
        intent.putExtra("x", ""+x)
        intent.putExtra("y", ""+y)
        startActivity(intent)
    }
}