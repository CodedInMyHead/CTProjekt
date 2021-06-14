package com.example.ctprojekt.memory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.ctprojekt.R
import java.util.*

class Memory() : AppCompatActivity() {
    private var int: Int
    private lateinit var carts:MutableList<MemoryKarte>
    private lateinit var wordList:MutableList<String>
    private lateinit var cosmeticList: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory2)
        val layout = findViewById<LinearLayout>(R.id.layout)

        cosmeticList = mutableListOf("Baum", "Haus")
        while (wordList.lastIndex == -1){
            val button = Button(this)
            button.id = int
            int++
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val random = Random()
            val randomNumber = random.nextInt(wordList.lastIndex)
            button.text = wordList[randomNumber]
            button.setOnClickListener(){
                Toast.makeText(this@Memory, "Du brauchst mehr Geld!!", Toast.LENGTH_LONG).show()
            }
            layout.addView(button)
            wordList.removeAt(randomNumber)
        }
    }
    init {
        int = 0
        wordList = mutableListOf("hallo","baum", "flo")
    }
}