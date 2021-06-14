package com.example.ctprojekt.cosmetic

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import com.example.ctprojekt.MainActivity
import com.example.ctprojekt.Options
import com.example.ctprojekt.R
import com.firebase.ui.auth.AuthUI
import java.io.File

@SuppressLint("RestrictedApi")
class FlappyBirdShop : AppCompatActivity() {
    private lateinit var mDetector: GestureDetectorCompat
    var filename:String
    //var filenameSkin:String
    var settings: SharedPreferences
    var fileContents = ""
    var coins = 0
    lateinit var file: File
    var cosmeticList: MutableList<Skin> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        mDetector = GestureDetectorCompat(this, MyGestureListener())
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
            if(settings.getBoolean("B"+cosmeticList[i].name, false)){
                button.setBackgroundColor(Color.GREEN)
            }

            button.setOnClickListener {
                if (settings.getBoolean("B"+cosmeticList[i].name, false)) {
                    Toast.makeText(this@FlappyBirdShop, "Du hast diesen Skin schon gekauft", Toast.LENGTH_LONG).show()
                }else{
                    if (coins >= cosmeticList[i].kosts){

                        coins = coins - cosmeticList[i].kosts
                        val editor = settings.edit()
                        editor.putInt("coins", coins)
                        editor.putBoolean("B"+cosmeticList[i].name,true)

                        editor.apply()
                        var baum = settings.getBoolean("B"+cosmeticList[i].name, true)
                        tvCoins.text = coins.toString()
                        cosmeticList[i].bought = true

                        button.setBackgroundColor(Color.GREEN)
                    }else{
                        Toast.makeText(this@FlappyBirdShop, "Du brauchst mehr Geld!!", Toast.LENGTH_LONG).show()
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
        for (i in 0..settings.getInt("flapMaxAnzahl", 0)){
            val STRING:String? = (settings.getString("flap$i", "noName"))
            cosmeticList.add(Skin(STRING, settings.getInt("K"+STRING, 0), settings.getBoolean("B"+STRING, false)))
        }
    }
    fun createTV(){
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)){
            true
        }else{
            return super.onTouchEvent(event)
        }

    }
    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_FRECHHOLD = 10
        private val SIPE_VELOCITY_THRESHHOLD = 10
        override fun onDown(event: MotionEvent): Boolean {
            //Log.d(DEBUG_TAG, "onDown: $event")

            return true
        }

        override fun onFling(downEvent: MotionEvent?, moveEvent: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F
            return if (Math.abs(diffX) > Math.abs(diffY)){
                if (Math.abs(diffX)>SWIPE_FRECHHOLD && Math.abs(velocityX)>SIPE_VELOCITY_THRESHHOLD){
                    if (diffX >0){
                        //right swipe
                        this@FlappyBirdShop.onSwipeRight()
                    }else{
                        //left swipe
                        this@FlappyBirdShop.onSwipeLeft()
                    }
                    true
                }else{
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }else{
                if (Math.abs(diffY)>SWIPE_FRECHHOLD && Math.abs(velocityY)>SIPE_VELOCITY_THRESHHOLD){
                    if (diffY>0){
                        //swipe top
                        this@FlappyBirdShop.onSwipeBottom()

                    }else{
                        //swipe down
                        this@FlappyBirdShop.onSwipeTop()
                    }
                    true
                }else{
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }

            }

        }
    }
    fun onSwipeRight(){
        /*
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
         */
        finish()
    }
    fun onSwipeLeft(){
    }
    fun onSwipeTop(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun onSwipeBottom(){

    }
}