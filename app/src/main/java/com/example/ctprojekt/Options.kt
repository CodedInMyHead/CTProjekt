package com.example.ctprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

class Options : AppCompatActivity() {
    private lateinit var mDetector: GestureDetectorCompat // erstelle Variable der Inner Class GestureDetectorCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
        mDetector = GestureDetectorCompat(this, MyGestureListener())  // initialisiere Vaiable
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)){ //Schaue ob ein Event vorhanden ist wenn ja führe onTouchEvent der Inner Class aus.
            true
        }else{
            return super.onTouchEvent(event)
        }
    }
    /*
    Eine inner Class ist ein Klasse welche in einer Klasse eingebedet ist und funktionen Hinzufügt. Meißtens sind inner Classes Klassen welche
    von einer anderen Klasse erben.
     */
    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_FRECHHOLD = 10 //lege die minimum Distanz welche ein swipe auslöst
        private val SIPE_VELOCITY_THRESHHOLD = 10 //lege die minimum Distanz welche ein swipe auslöst
        /*
        Detectiert den Punkt an welchem der Finger losgelassen wird
         */
        override fun onDown(event: MotionEvent): Boolean {
            //Log.d(DEBUG_TAG, "onDown: $event")
            return true
        }
        /*
        onFling ist eine Methode, welche die Auswertung der Touchgesten bearbeitet
         */
        override fun onFling(downEvent: MotionEvent?, moveEvent: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F // bildet aus die differenz aus dem X Startwerts und des Endwerts
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F // bildet aus die differenz aus dem Y Startwerts und des Endwerts
            return if (Math.abs(diffX) > Math.abs(diffY)){ //überprüft ob die Verschiebung in X oder in Y richtung größer ist
                if (Math.abs(diffX)>SWIPE_FRECHHOLD && Math.abs(velocityX)>SIPE_VELOCITY_THRESHHOLD){ //überprüft ob die Minimum distanz eingehalten wird
                    if (diffX >0){ //bei Swipes nach unten wird die dazu gehörige Funktion aufgerufen
                        //right swipe
                        this@Options.onSwipeRight()
                    }else{
                        //left swipe
                        this@Options.onSwipeLeft()
                    }
                    true
                }else{
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }else{
                if (Math.abs(diffY)>SWIPE_FRECHHOLD && Math.abs(velocityY)>SIPE_VELOCITY_THRESHHOLD){
                    if (diffY>0){
                        //swipe top
                        this@Options.onSwipeBottom()

                    }else{
                        //swipe down
                        this@Options.onSwipeTop()
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
    fun onSwipeLeft(){
    }
    fun onSwipeTop(){
    }
    fun onSwipeBottom(){
        finish()
    }

}