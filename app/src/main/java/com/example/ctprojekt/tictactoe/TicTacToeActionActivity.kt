package com.example.ctprojekt.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import com.example.ctprojekt.MainActivity
import com.example.ctprojekt.R
import java.security.spec.MGF1ParameterSpec

class TicTacToeActionActivity : AppCompatActivity() {
    private var currentPlayer = "X"
    lateinit var tf0:TextView
    lateinit var tf1:TextView
    lateinit var tf2:TextView
    lateinit var tf3:TextView
    lateinit var tf4:TextView
    lateinit var tf5:TextView
    lateinit var tf6:TextView
    lateinit var tf7:TextView
    lateinit var tf8:TextView
    lateinit var tvPlayer1:TextView
    lateinit var tvPlayer2:TextView
    var scorePlayer1:Int = 0
    var scorePlayer2:Int = 0

    lateinit var tvCurrentPlayer:TextView
    lateinit var allFields:MutableList<TextView>

    private lateinit var mDetector: GestureDetectorCompat // erstelle Variable der Inner Class GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe_action)
        mDetector = GestureDetectorCompat(this, MyGestureListener())
        tvPlayer1 = findViewById(R.id.tvPlayer1)
        tvPlayer2 = findViewById(R.id.tvPlayer2)
        tvCurrentPlayer = findViewById<TextView>(R.id.tv)
        tf0 = findViewById<TextView>(R.id.tfTicTacToe0)
        tf1 = findViewById<TextView>(R.id.tfTicTacToe1)
        tf2 = findViewById<TextView>(R.id.tfTicTacToe2)
        tf3 = findViewById<TextView>(R.id.tfTicTacToe3)
        tf4 = findViewById<TextView>(R.id.tfTicTacToe4)
        tf5 = findViewById<TextView>(R.id.tfTicTacToe5)
        tf6 = findViewById<TextView>(R.id.tfTicTacToe6)
        tf7 = findViewById<TextView>(R.id.tfTicTacToe7)
        tf8 = findViewById<TextView>(R.id.tfTicTacToe8)
        allFields = arrayListOf<TextView>(tf0,tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8)
        for(field in allFields){
            field.setOnClickListener{
                onFieldClick(field)
            }
        }
    }
    fun onFieldClick(field: TextView){
        if (field.text == ""){
            field.text = currentPlayer
            if (checkWinn()){when(currentPlayer){"X"->scorePlayer1++; "O"->scorePlayer2++};  tvPlayer1.text = "Player 1(X): $scorePlayer1" ;tvPlayer2.text = "Player 2(O): $scorePlayer2";clearGame()}
            currentPlayer = when(currentPlayer){"X" -> "O"; else -> "X"}
        }
        if(checkWinn()){
            tvCurrentPlayer.text = "Spieler $currentPlayer hat Gewonnen"
        }
        if (checkAllFieldsFilled())clearGame();tvCurrentPlayer.text = "niemand hat gewonnen"
        tvCurrentPlayer.text = "Spieler $currentPlayer ist am Zug"

    }
    fun checkWinn():Boolean{
        return (tf0.text == tf1.text && tf1.text == tf2.text && tf0.text != "")||(tf0.text == tf4.text && tf4.text == tf8.text && tf0.text != "")||(tf2.text == tf4.text && tf4.text == tf6.text && tf2.text != "")||(tf3.text == tf4.text && tf4.text == tf5.text && tf3.text != "")||(tf6.text == tf7.text && tf7.text == tf8.text && tf7.text != "")||(tf0.text == tf3.text && tf3.text == tf6.text && tf0.text != "")||(tf1.text == tf4.text && tf4.text == tf7.text && tf1.text != "")|| (tf2.text == tf5.text && tf5.text == tf8.text && tf2.text != "")
    }
    fun checkAllFieldsFilled():Boolean{
        return (tf0.text != "" && tf1.text != "" && tf2.text != "" && tf3.text != "" && tf4.text != "" && tf5.text != "" && tf6.text != "" && tf7.text != "" && tf8.text != "")
    }
    fun clearGame(){
        for (field in allFields)field.text = ""
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
                        this@TicTacToeActionActivity.onSwipeRight()
                    }else{
                        //left swipe
                        this@TicTacToeActionActivity.onSwipeLeft()
                    }
                    true
                }else{
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }else{
                if (Math.abs(diffY)>SWIPE_FRECHHOLD && Math.abs(velocityY)>SIPE_VELOCITY_THRESHHOLD){
                    if (diffY>0){
                        //swipe top
                        this@TicTacToeActionActivity.onSwipeBottom()

                    }else{
                        //swipe down
                        this@TicTacToeActionActivity.onSwipeTop()
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
    }
    fun onSwipeBottom(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}