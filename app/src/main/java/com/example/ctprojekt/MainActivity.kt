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
        settings.getBoolean("baum", true)
        if(settings.getBoolean("baum",true)){
            editor.putString("1", "OwnSkin")
            editor.putInt("kOwnSkin", 0)
            editor.putBoolean("bOwnSkin", false)
            editor.putInt("backgroundOwnSkin", Color.rgb(104,118,138))
            editor.putInt("snakeOwnSkin", Color.rgb(83,41,175))
            editor.putInt("applOwnSkin", Color.rgb(40,200,40))

            editor.putString("2", "Darkmode")
            editor.putInt("kDarkmode", 10)
            editor.putBoolean("bDarkmode", false)
            editor.putInt("backgroundDarkmode", Color.BLACK)
            editor.putInt("snakeDarkmode", Color.GREEN)
            editor.putInt("applDarkmode", Color.GREEN)

            editor.putString("3", "Lila")
            editor.putInt("kLila", 40)
            editor.putBoolean("bLila", false)
            editor.putInt("backgroundLila", (Color.argb(255, 26, 128, 182)))
            editor.putInt("snakeLila", Color.rgb(255,182,193))
            editor.putInt("applLila", Color.GREEN)

            editor.putString("4", "Lukas")
            editor.putInt("kLukas", 30)
            editor.putBoolean("bLukas", false)
            editor.putInt("backgroundLukas", Color.rgb(114,132,163))
            editor.putInt("snakeLukas", Color.rgb(255,203,245))
            editor.putInt("applLukas", Color.rgb(205,255,241))

            editor.putString("5", "Gelb")
            editor.putInt("kGelb", 60)
            editor.putBoolean("bGelb", false)
            editor.putInt("backgroundGelb", Color.rgb(0,0,0))
            editor.putInt("snakeGelb", Color.rgb(255,155,30))
            editor.putInt("applGelb", Color.rgb(103,207,31))

            editor.putString("6", "Flo")
            editor.putInt("kFlo", 50)
            editor.putBoolean("bFlo", false)
            editor.putInt("backgroundFlo", Color.rgb(0,0,0))
            editor.putInt("snakeFlo", Color.rgb(255,155,30))
            editor.putInt("applFlo", Color.rgb(103,207,31))

            editor.putString("7", "Joona")
            editor.putInt("kJoona", 20)
            editor.putBoolean("bJoona", false)
            editor.putInt("backgroundJoona", Color.rgb(242,188,148))
            editor.putInt("snakeJoona", Color.rgb(48,17,13))
            editor.putInt("applJoona", Color.rgb(114,38,132))


            editor.putString("8", "Luki")
            editor.putInt("kLuki", 30)
            editor.putBoolean("bLuki", false)
            editor.putInt("backgroundLuki", Color.rgb(104,118,138))
            editor.putInt("snakeLuki", Color.rgb(83,41,175))
            editor.putInt("applLuki", Color.rgb(40,200,40))



            editor.putInt("maxAnzahl", 8)

            editor.putInt("activeSkin", 0)
            editor.putBoolean("baum", false)
            editor.apply()
        }
        settings.getBoolean("FlappyBird", true)
        if (settings.getBoolean("FlappyBird", true)){
            editor.putString("flap1", "FlapOwnSkin")
            editor.putInt("KFlapOwnSkin", 0)
            editor.putBoolean("BFlapOwnSkin", false)
            editor.putInt("BackgroundFlapOwnSkin", Color.rgb(104,118,138))
            editor.putInt("BirdFlapOwnSkin", Color.rgb(83,41,175))
            editor.putInt("PillarFlapOwnSkin", Color.rgb(40,200,40))

            editor.putString("flap2", "FlapDarkmode")
            editor.putInt("KFlapDarkmode", 10)
            editor.putBoolean("BFlapDarkmode", false)
            editor.putInt("BackgroundFlapDarkmode", Color.BLACK)
            editor.putInt("BirdFlapDarkmode", Color.GREEN)
            editor.putInt("PillarFlapDarkmode", Color.GREEN)

            editor.putString("flap3", "FlapLila")
            editor.putInt("KFlapLila", 40)
            editor.putBoolean("BFlapLila", false)
            editor.putInt("BackgroundFlapLila", (Color.argb(255, 26, 128, 182)))
            editor.putInt("BirdFlapLila", Color.rgb(255,182,193))
            editor.putInt("PillarFlapLila", Color.GREEN)

            editor.putString("flap4", "FlapLukas")
            editor.putInt("KFlapLukas", 30)
            editor.putBoolean("BFlapLukas", false)
            editor.putInt("BackgroundFlapLukas", Color.rgb(114,132,163))
            editor.putInt("BirdFlapLukas", Color.rgb(255,203,245))
            editor.putInt("PillarFlapLukas", Color.rgb(205,255,241))

            editor.putString("flap5", "FlapGelb")
            editor.putInt("KFlapGelb", 60)
            editor.putBoolean("BFlapGelb", false)
            editor.putInt("BackgroundFlapGelb", Color.rgb(0,0,0))
            editor.putInt("BirdFlapGelb", Color.rgb(255,155,30))
            editor.putInt("PillaFlaprGelb", Color.rgb(103,207,31))

            editor.putString("flap6", "FlapFlo")
            editor.putInt("KFlapFlo", 50)
            editor.putBoolean("BFlapFlo", false)
            editor.putInt("BackgroundFlapFlo", Color.rgb(0,0,0))
            editor.putInt("BirdFlapFlo", Color.rgb(255,155,30))
            editor.putInt("PillarFlapFlo", Color.rgb(103,207,31))

            editor.putString("flap7", "FlapJoona")
            editor.putInt("KFlapJoona", 20)
            editor.putBoolean("BFlapJoona", false)
            editor.putInt("BackgroundFlapJoona", Color.rgb(242,188,148))
            editor.putInt("BirdFlapJoona", Color.rgb(48,17,13))
            editor.putInt("PillarFlapJoona", Color.rgb(114,38,132))


            editor.putString("flap8", "FlapLuki")
            editor.putInt("KFlapLuki", 30)
            editor.putBoolean("BFlapLuki", false)
            editor.putInt("BackgroundFlapLuki", Color.rgb(104,118,138))
            editor.putInt("BirdFlapLuki", Color.rgb(83,41,175))
            editor.putInt("PillarFlapLuki", Color.rgb(40,200,40))

            editor.putInt("flapMaxAnzahl", 8)

            editor.putInt("flapActiveSkin", 0)
            editor.putBoolean("FlappyBird", false)
            editor.apply()
        }
    }
    /*
    * TODO Options für das Aussehen (Standard Einstellungsmenü von Apps)
    * */
}