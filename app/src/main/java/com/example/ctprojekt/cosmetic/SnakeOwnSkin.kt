package com.example.ctprojekt.cosmetic

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ctprojekt.R
import com.firebase.ui.auth.AuthUI

class SnakeOwnSkin : AppCompatActivity() {

    lateinit var filename:String
    lateinit var btnSnakeColor:Button
    lateinit var tvSnakeColorRed:TextView
    lateinit var tvSnakeColorGreen:TextView
    lateinit var tvSnakeColorBlue:TextView
    lateinit var sbSnakeColorRed:SeekBar
    lateinit var sbSnakeColorGreen:SeekBar
    lateinit var sbSnakeColorBlue:SeekBar

    lateinit var tvBackgroundColorRed:TextView
    lateinit var tvBackgroundColorGreen:TextView
    lateinit var tvBackgroundColorBlue:TextView

    lateinit var tvAppleColorRed:TextView
    lateinit var tvAppleColorGreen:TextView
    lateinit var tvAppleColorBlue:TextView
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_own_skin)
        filename = "coins.txt"
        var settings = AuthUI.getApplicationContext().getSharedPreferences(filename, 0)
        val editor = settings.edit()
        var rgbSnake = settings.getInt("snakeOwnSkin", Color.argb(255,255,255,255))
        val hexColorSnake = java.lang.Integer.toHexString(rgbSnake)
        val hexColorBackground = java.lang.Integer.toHexString(settings.getInt("backgroundOwnSkin", Color.argb(255,255,255,255)))
        val hexColorApple = java.lang.Integer.toHexString(settings.getInt("applOwnSkin", Color.argb(255,255,255,255)))

        btnSnakeColor = findViewById<Button>(R.id.btnSnakeColor)
        tvSnakeColorRed = findViewById<TextView>(R.id.tvSnakeRed)
        tvSnakeColorGreen = findViewById<TextView>(R.id.tvSnakeGreen)
        tvSnakeColorBlue = findViewById<TextView>(R.id.tvSnakeBlue)
        sbSnakeColorRed = findViewById<SeekBar>(R.id.sbSnakeRed)
        sbSnakeColorGreen = findViewById<SeekBar>(R.id.sbSnakeGreen)
        sbSnakeColorBlue = findViewById<SeekBar>(R.id.sbSnakeBlue)

        tvSnakeColorRed.text = (hexColorSnake[2].toString()+hexColorSnake[3].toString()).toInt(16).toString()
        tvSnakeColorGreen.text = (hexColorSnake[4].toString()+hexColorSnake[5].toString()).toInt(16).toString()
        tvSnakeColorBlue.text = (hexColorSnake[6].toString()+hexColorSnake[7].toString()).toInt(16).toString()
        var snakeColorRed = tvSnakeColorRed.text.toString().toInt()
        var snakeColorGreen = tvSnakeColorGreen.text.toString().toInt()
        var snakeColorBlue = tvSnakeColorBlue.text.toString().toInt()
        btnSnakeColor.setBackgroundColor(Color.rgb(snakeColorRed,snakeColorGreen,snakeColorBlue))

        sbSnakeColorRed.progress = snakeColorRed
        sbSnakeColorRed.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvSnakeColorRed.setText(progress.toString())
                snakeColorRed = tvSnakeColorRed.text.toString().toInt()
                btnSnakeColor.setBackgroundColor(
                    Color.rgb(
                        snakeColorRed,
                        snakeColorGreen,
                        snakeColorBlue
                    )
                )
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })
        sbSnakeColorGreen.progress = snakeColorGreen
        sbSnakeColorGreen.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvSnakeColorGreen.setText(progress.toString())
                snakeColorGreen = tvSnakeColorGreen.text.toString().toInt()
                btnSnakeColor.setBackgroundColor(
                    Color.rgb(
                        snakeColorRed,
                        snakeColorGreen,
                        snakeColorBlue
                    )
                )
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })
        sbSnakeColorBlue.progress = snakeColorBlue
        sbSnakeColorBlue.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvSnakeColorBlue.setText(progress.toString())
                snakeColorBlue = tvSnakeColorBlue.text.toString().toInt()
                btnSnakeColor.setBackgroundColor(
                    Color.rgb(
                        snakeColorRed,
                        snakeColorGreen,
                        snakeColorBlue
                    )
                )
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })

        var btnBackgroundColor = findViewById<Button>(R.id.btnBackgroundColor)
        tvBackgroundColorRed = findViewById<TextView>(R.id.tvBackgroundRed)
        tvBackgroundColorGreen = findViewById<TextView>(R.id.tvBackgroundGreen)
        tvBackgroundColorBlue = findViewById<TextView>(R.id.tvBackgroundBlue)
        var sbBackgroundColorRed = findViewById<SeekBar>(R.id.sbBackgroundRed)
        var sbBackgroundColorGreen = findViewById<SeekBar>(R.id.sbBackgroundGreen)
        var sbBackgroundColorBlue = findViewById<SeekBar>(R.id.sbBackgroundBlue)

        tvBackgroundColorRed.text = (hexColorBackground[2].toString()+hexColorBackground[3].toString()).toInt(16).toString()
        tvBackgroundColorGreen.text = (hexColorBackground[4].toString()+hexColorBackground[5].toString()).toInt(16).toString()
        tvBackgroundColorBlue.text = (hexColorBackground[6].toString()+hexColorBackground[7].toString()).toInt(16).toString()

        var backgroundColorRed = tvBackgroundColorRed.getText().toString().toInt()
        var backgroundColorGreen = tvBackgroundColorGreen.text.toString().toInt()
        var backgroundColorBlue = tvBackgroundColorBlue.text.toString().toInt()

        btnBackgroundColor.setBackgroundColor(Color.rgb(backgroundColorRed,backgroundColorGreen,backgroundColorBlue))

        sbBackgroundColorRed.progress = backgroundColorRed
        sbBackgroundColorRed.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvBackgroundColorRed.setText(progress.toString())
                backgroundColorRed = tvBackgroundColorRed.text.toString().toInt()
                btnBackgroundColor.setBackgroundColor(
                    Color.rgb(
                        backgroundColorRed,
                        backgroundColorGreen,
                        backgroundColorBlue
                    )
                )
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })
        sbBackgroundColorGreen.progress = backgroundColorGreen
        sbBackgroundColorGreen.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvBackgroundColorGreen.setText(progress.toString())
                backgroundColorGreen = tvBackgroundColorGreen.text.toString().toInt()
                btnBackgroundColor.setBackgroundColor(
                    Color.rgb(
                        backgroundColorRed,
                        backgroundColorGreen,
                        backgroundColorBlue
                    )
                )
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })
        sbBackgroundColorBlue.progress = backgroundColorBlue
        sbBackgroundColorBlue.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvBackgroundColorBlue.setText(progress.toString())
                backgroundColorBlue = tvBackgroundColorBlue.text.toString().toInt()
                btnBackgroundColor.setBackgroundColor(
                    Color.rgb(
                        backgroundColorRed,
                        backgroundColorGreen,
                        backgroundColorBlue
                    )
                )
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })

        var btnAppleColor = findViewById<Button>(R.id.btnAppleColor)
        tvAppleColorRed = findViewById<TextView>(R.id.tvAppleRed)
        tvAppleColorGreen = findViewById<TextView>(R.id.tvAppleGreen)
        tvAppleColorBlue = findViewById<TextView>(R.id.tvAppleBlue)
        var sbAppleColorRed = findViewById<SeekBar>(R.id.sbAppleRed)
        var sbAppleColorGreen = findViewById<SeekBar>(R.id.sbAppleGreen)
        var sbAppleColorBlue = findViewById<SeekBar>(R.id.sbAppleBlue)

        tvAppleColorRed.text = (hexColorApple[2].toString()+hexColorApple[3].toString()).toInt(16).toString()
        tvAppleColorGreen.text = (hexColorApple[4].toString()+hexColorApple[5].toString()).toInt(16).toString()
        tvAppleColorBlue.text = (hexColorApple[6].toString()+hexColorApple[7].toString()).toInt(16).toString()

        var appleColorRed = tvAppleColorRed.getText().toString().toInt()
        var appleColorGreen = tvAppleColorGreen.text.toString().toInt()
        var appleColorBlue = tvAppleColorBlue.text.toString().toInt()

        btnAppleColor.setBackgroundColor(Color.rgb(appleColorRed,appleColorGreen,appleColorBlue))

        sbAppleColorRed.progress = appleColorRed
        sbAppleColorRed.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvAppleColorRed.setText(progress.toString())
                appleColorRed = tvAppleColorRed.text.toString().toInt()
                btnAppleColor.setBackgroundColor(
                    Color.rgb(
                        appleColorRed,
                        appleColorGreen,
                        appleColorBlue
                    )
                )
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })
        sbAppleColorGreen.progress = appleColorGreen
        sbAppleColorGreen.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvAppleColorGreen.setText(progress.toString())
                appleColorGreen = tvAppleColorGreen.text.toString().toInt()
                btnAppleColor.setBackgroundColor(
                    Color.rgb(
                        appleColorRed,
                        appleColorGreen,
                        appleColorBlue
                    )
                )
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })
        sbAppleColorBlue.progress = appleColorBlue
        sbAppleColorBlue.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                tvAppleColorBlue.setText(progress.toString())
                appleColorBlue = tvAppleColorBlue.text.toString().toInt()
                btnAppleColor.setBackgroundColor(
                    Color.rgb(
                        appleColorRed,
                        appleColorGreen,
                        appleColorBlue
                    )
                )
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {}
        })

    }
    @SuppressLint("RestrictedApi")
    fun onClick(view: View){
        var settings = AuthUI.getApplicationContext().getSharedPreferences(filename, 0)
        val editor = settings.edit()
        editor.putInt("snakeOwnSkin", Color.rgb(tvSnakeColorRed.text.toString().toInt(), tvSnakeColorGreen.text.toString().toInt(), tvSnakeColorBlue.text.toString().toInt()))
        editor.putInt("backgroundOwnSkin", Color.rgb(tvBackgroundColorRed.text.toString().toInt(), tvBackgroundColorGreen.text.toString().toInt(), tvBackgroundColorBlue.text.toString().toInt()))
        editor.putInt("applOwnSkin", Color.rgb(tvAppleColorRed.text.toString().toInt(), tvAppleColorGreen.text.toString().toInt(), tvAppleColorBlue.text.toString().toInt()))
        editor.apply()
        finish()
    }
    fun lul(view: View){
        finish()
    }
}

