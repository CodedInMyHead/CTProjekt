package com.example.ctprojekt.tictactoe

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ctprojekt.R

class TicTacToe : AppCompatActivity() {
    private lateinit var myTicTacToeCanvas:MyTicTacToeCanvas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myTicTacToeCanvas = MyTicTacToeCanvas(this)
        setContentView(myTicTacToeCanvas)
        //setContentView(R.layout.activity_tic_tac_toe)

    }


}