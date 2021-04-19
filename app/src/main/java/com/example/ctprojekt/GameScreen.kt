package com.example.ctprojekt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import com.example.ctprojekt.flappybird.FlappyBirdActivity
import com.example.ctprojekt.memory.Memory
import com.example.ctprojekt.snake.SnakeActivity
import com.example.ctprojekt.tictactoe.TicTacToe
import com.example.ctprojekt.tictactoe.TicTacToeActionActivity

class GameScreen : AppCompatActivity() {
    private lateinit var mDetector: GestureDetectorCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
        mDetector = GestureDetectorCompat(this, MyGestureListener())
    }

    fun startTicTacToe(view: View){
        val intent = Intent(this, TicTacToeActionActivity::class.java)
        startActivity(intent)
    }

    fun startMemoryDifficulty(view: View){
        val intent = Intent(this, Memory::class.java)
        startActivity(intent)
    }

    fun startSnake(view: View){
        val intent = Intent(this, SnakeActivity::class.java)
        startActivity(intent)
    }

    fun startFlappyBird(view: View){
        val intent = Intent(this, FlappyBirdActivity::class.java)
        startActivity(intent)
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
                        this@GameScreen.onSwipeRight()
                    }else{
                        //left swipe
                        this@GameScreen.onSwipeLeft()
                    }
                    true
                }else{
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }else{
                if (Math.abs(diffY)>SWIPE_FRECHHOLD && Math.abs(velocityY)>SIPE_VELOCITY_THRESHHOLD){
                    if (diffY>0){
                        //swipe top
                        this@GameScreen.onSwipeBottom()

                    }else{
                        //swipe down
                        this@GameScreen.onSwipeTop()
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
        val intent = Intent(this, Options::class.java)
        startActivity(intent)
    }
    fun onSwipeBottom(){

    }

}