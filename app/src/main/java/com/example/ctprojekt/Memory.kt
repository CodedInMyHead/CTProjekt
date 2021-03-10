package com.example.ctprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Memory : AppCompatActivity() {

    var myX = 0
    var myY = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)
        myX = Integer.parseInt(intent.getStringExtra("x"))
        myY = Integer.parseInt(intent.getStringExtra("y"))
    }


}
