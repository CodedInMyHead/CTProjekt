package com.example.ctprojekt.cosmetic

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.core.view.GestureDetectorCompat
import com.example.ctprojekt.MainActivity
import com.example.ctprojekt.Options
import com.example.ctprojekt.R
import com.firebase.ui.auth.AuthUI

class Cosmetics : AppCompatActivity(), View.OnClickListener {
    private lateinit var mDetector: GestureDetectorCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        mDetector = GestureDetectorCompat(this, MyGestureListener())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cosmetics)
        findViewById<Button>(R.id.btnFlappyBirdSelection).setOnClickListener(this)
        findViewById<Button>(R.id.btnFlappyBirdShop).setOnClickListener(this)
        findViewById<Button>(R.id.btnCosmeticSelection).setOnClickListener(this)
        findViewById<Button>(R.id.btnCosmeticShop).setOnClickListener(this)
        findViewById<Button>(R.id.btnDeletall).setOnClickListener(this)
    }

    @SuppressLint("RestrictedApi")
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnFlappyBirdShop -> startActivity(Intent(this, FlappyBirdShop::class.java))
            R.id.btnFlappyBirdSelection -> startActivity(Intent(this, FlappyBirdSelection::class.java))
            R.id.btnCosmeticSelection -> startActivity(Intent(this, SnakeSelection::class.java))
            R.id.btnCosmeticShop -> startActivity(Intent(this, SnakeShop::class.java))
            R.id.btnDeletall ->{
                val filenames = "coins.txt"
                val settings = AuthUI.getApplicationContext().getSharedPreferences(filenames,0)
                val editor = settings.edit()
                for (i in 0..settings.getInt("maxAnzahl",0)){
                    val STRING = settings.getString("$i","")
                    editor.putBoolean("b"+STRING, false)
                    editor.apply()
                }
            }
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
                        this@Cosmetics.onSwipeRight()
                    }else{
                        //left swipe
                        this@Cosmetics.onSwipeLeft()
                    }
                    true
                }else{
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }else{
                if (Math.abs(diffY)>SWIPE_FRECHHOLD && Math.abs(velocityY)>SIPE_VELOCITY_THRESHHOLD){
                    if (diffY>0){
                        //swipe top
                        this@Cosmetics.onSwipeBottom()

                    }else{
                        //swipe down
                        this@Cosmetics.onSwipeTop()
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