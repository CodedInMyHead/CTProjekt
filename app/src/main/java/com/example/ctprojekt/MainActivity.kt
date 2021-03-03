package com.example.ctprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onGameScreen(view: View){
        val intent = Intent(this, GameScreen::class.java)
        startActivity(intent)
    }
    fun onMusicScreen(view: View){
        val intent = Intent(this, MusicScreen::class.java)
        startActivity(intent)
    }

    fun onCosmeticsScreen(view: View){
        val intent = Intent(this, Cosmetics::class.java)
        startActivity(intent)
    }

    fun onOptionsScreen(view: View){
        val intent = Intent(this, Options::class.java)
        startActivity(intent)
    }


    /*
    * TODO Skin system für Spiele (Andere Snake-Farbe etc.)
    * TODO Options für das Aussehen (Standard Einstellungsmenü von Apps)
    *
    *
    * */


}