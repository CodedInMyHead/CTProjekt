package com.example.ctprojekt.memory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.ctprojekt.R

class Memory() : AppCompatActivity() {
    private lateinit var carts:MutableList<MemoryKarte>
    private lateinit var wordList:MutableList<String>
    private lateinit var cosmeticList: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory2)
        val layout = findViewById<LinearLayout>(R.id.layout)
        cosmeticList = mutableListOf("Baum", "Haus")
        for (i in cosmeticList.indices){
            val button = Button(this)
            button.id = i
            // setting layout_width and layout_height using layout parameters
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            button.text = cosmeticList[i]
            button.setOnClickListener { Toast.makeText(this@Memory, "Hello GEEK", Toast.LENGTH_LONG).show() }
            // add Button to LinearLayout
            layout.addView(button)
        }
    }
    init {
        wordList = mutableListOf("hallo","baum", "flo")
    }
}