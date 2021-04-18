package com.example.ctprojekt.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe_action)
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
        tvCurrentPlayer.text = "Spieler $currentPlayer ist am Zug"

    }
    fun checkWinn():Boolean{
        return (tf0.text == tf1.text && tf1.text == tf2.text && tf0.text != "")||(tf0.text == tf4.text && tf4.text == tf8.text && tf0.text != "")||(tf2.text == tf4.text && tf4.text == tf6.text && tf2.text != "")||(tf3.text == tf4.text && tf4.text == tf5.text && tf3.text != "")||(tf6.text == tf7.text && tf7.text == tf8.text && tf7.text != "")
    }
    fun clearGame(){
        for (field in allFields)field.text = ""
    }

}