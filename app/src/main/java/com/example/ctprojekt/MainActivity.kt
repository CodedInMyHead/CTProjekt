package com.example.ctprojekt

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ctprojekt.cosmetic.Cosmetics
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase


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
    * */


}