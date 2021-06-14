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
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import com.example.ctprojekt.MainActivity
import com.example.ctprojekt.R
import com.firebase.ui.auth.AuthUI
import java.io.File

@SuppressLint("RestrictedApi")
class SnakeSelection : AppCompatActivity() {
    private lateinit var mDetector: GestureDetectorCompat
    var int: Int = 0
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
        setContentView(R.layout.activity_snake_selection)
        val layout = findViewById<LinearLayout>(R.id.layout)
        for (i in cosmeticList.indices){
            val button = Button(this)
            button.id = i
            // setting layout_width and layout_height using layout parameters
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            button.text = cosmeticList[i].name
            if(settings.getBoolean("b"+cosmeticList[i].name, false)){
                button.setBackgroundColor(Color.LTGRAY)
            }else{
                button.setBackgroundColor(Color.DKGRAY)
            }
            if(button.id == settings.getInt("activeSkin", 0)){
                button.setBackgroundColor(Color.GREEN)
                int = i
            }

            button.setOnClickListener {
                if (settings.getBoolean("b"+cosmeticList[i].name, false)) {
                    Toast.makeText(this@SnakeSelection, "Du hast einen Neuen Skinn ausgewählt", Toast.LENGTH_LONG).show()
                    if(button.text == "OwnSkin"){
                        startActivity(Intent(this, SnakeOwnSkin::class.java))
                    }
                    button.setBackgroundColor(Color.GREEN)
                    val editor = settings.edit()
                    editor.putInt("activeSkin", i)
                    var lastSkin = findViewById<Button>(int)
                    lastSkin.setBackgroundColor(Color.LTGRAY)
                    int = button.id
                    editor.apply()
                }else{
                    Toast.makeText(this@SnakeSelection, "Du musst den Skin erst noch im Shop kaufen", Toast.LENGTH_LONG).show()
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
                        this@SnakeSelection.onSwipeRight()
                    }else{
                        //left swipe
                        this@SnakeSelection.onSwipeLeft()
                    }
                    true
                }else{
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }else{
                if (Math.abs(diffY)>SWIPE_FRECHHOLD && Math.abs(velocityY)>SIPE_VELOCITY_THRESHHOLD){
                    if (diffY>0){
                        //swipe top
                        this@SnakeSelection.onSwipeBottom()

                    }else{
                        //swipe down
                        this@SnakeSelection.onSwipeTop()
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