package com.example.ctprojekt.cosmetic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ctprojekt.R
import android.content.SharedPreferences
import android.graphics.Color
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import java.io.File


@SuppressLint("RestrictedApi", "CommitPrefEdits")
class SnakeShop : AppCompatActivity() {
    var filename:String
    //var filenameSkin:String
    var settings:SharedPreferences
    var fileContents = ""
    var coins = 0
    lateinit var file:File
    var cosmeticList: MutableList<Skin> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cosmetic_shop)
        val layout = findViewById<LinearLayout>(R.id.layout)
        var tvCoins = findViewById<TextView>(R.id.tvCoins)
        tvCoins.text = coins.toString()
        for (i in cosmeticList.indices){
            val button = Button(this)
            button.id = i
            // setting layout_width and layout_height using layout parameters
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            button.text = cosmeticList[i].name + ":  " +cosmeticList[i].kosts.toString()
            if(settings.getBoolean("b"+cosmeticList[i].name, false)){
                button.setBackgroundColor(Color.GREEN)
            }

            button.setOnClickListener {
                if (settings.getBoolean("b"+cosmeticList[i].name, false)) {
                    Toast.makeText(this@SnakeShop, "Du kannst diesen Skin schon gekauft", Toast.LENGTH_LONG).show()
                }else{
                if (coins >= cosmeticList[i].kosts){

                    coins = coins - cosmeticList[i].kosts
                    val editor = settings.edit()
                    editor.putInt("coins", coins)
                    editor.putBoolean("b"+cosmeticList[i].name,true)
                    editor.apply()
                    tvCoins.text = coins.toString()
                    cosmeticList[i].bought = true

                    button.setBackgroundColor(Color.GREEN)
                }else{
                    Toast.makeText(this@SnakeShop, "Du brauchst mehr Geld!!", Toast.LENGTH_LONG).show()
                }
            }

            }

            layout.addView(button)
        }
    }
    init {
        filename = "coins.txt"
        settings = AuthUI.getApplicationContext().getSharedPreferences(filename, 0)
        val editor = settings.edit()
        val mCoins = settings.getInt("coins", 0)
        coins = mCoins
        // Apply the edits!
        editor.apply()
        for (i in 0..settings.getInt("maxAnzahl", 0)){
            val STRING:String? = (settings.getString("$i", "noName"))
            cosmeticList.add(Skin(STRING, settings.getInt("k"+STRING, 0), settings.getBoolean("b"+STRING, false)))
        }
    }
    fun createTV(){
    }

}


