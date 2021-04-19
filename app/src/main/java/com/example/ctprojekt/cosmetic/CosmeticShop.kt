package com.example.ctprojekt.cosmetic

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ctprojekt.R


class CosmeticShop : AppCompatActivity() {
    private lateinit var cosmeticList: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cosmetic_shop)
        val layout = findViewById<LinearLayout>(R.id.layout)
        cosmeticList = mutableListOf("Baum", "Haus")
        for (i in cosmeticList.indices){
            val button = Button(this)
            button.id = i
            // setting layout_width and layout_height using layout parameters
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            button.text = cosmeticList[i]
            button.setOnClickListener { Toast.makeText(this@CosmeticShop, "Hello GEEK", Toast.LENGTH_LONG).show() }
            // add Button to LinearLayout
            layout.addView(button)
        }
    }
    init {

    }


}