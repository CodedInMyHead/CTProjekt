package com.example.ctprojekt

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ctprojekt.cosmetic.Cosmetics
import com.example.ctprojekt.goodlPlayServices.GoogleAPI
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase


@SuppressLint("RestrictedApi")
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
        val intent = Intent(this, GoogleAPI::class.java)
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
    init{
        val filename = "coins.txt"
        val settings = AuthUI.getApplicationContext().getSharedPreferences(filename, 0)
        val editor = settings.edit()
        editor.putString("1", "Darkmode")
        editor.putInt("kDarkmode", 10)
        editor.putBoolean("bDarkmode", false)
        editor.putInt("backgroundDarkmode", Color.BLACK)
        editor.putInt("snakeDarkmode", Color.GREEN)
        editor.putInt("applDarkmode", Color.GREEN)

        editor.putString("2", "Lila")
        editor.putInt("kLila", 10)
        editor.putBoolean("bLila", false)
        editor.putInt("backgroundLila", Color.BLUE)
        editor.putInt("snakeLila", Color.rgb(108,70,117))
        editor.putInt("applLila", Color.GREEN)

        editor.putInt("maxAnzahl", 3)

        editor.putInt("activeSkin", 0)
        editor.apply()
    }

    /*
    * TODO Skin system für Spiele (Andere Snake-Farbe etc.)
    * TODO Options für das Aussehen (Standard Einstellungsmenü von Apps)
    * */


}