package com.example.ctprojekt.flappybird

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.ctprojekt.GameScreen
import com.example.ctprojekt.R
import com.example.ctprojekt.cosmetic.FlappyBirdSelection

class FlappyBirdActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flappy_bird)
        findViewById<Button>(R.id.startBtn).setOnClickListener(this)
        findViewById<Button>(R.id.controlBtn).setOnClickListener(this)
        findViewById<Button>(R.id.controlBtn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.startBtn -> startActivity( Intent(this, GameActivity::class.java))
            //R.id.controlBtn ->
            R.id.returnBtn -> startActivity(Intent(this, GameScreen::class.java))

            R.id.controlBtn -> startActivity(Intent(this, FlappyBirdSelection::class.java))
        }
    }

}