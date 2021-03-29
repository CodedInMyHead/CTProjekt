package com.example.ctprojekt.snake

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ctprojekt.R

class Snake : AppCompatActivity() {
    var snakeEngine: SnakeEngine? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake)
        // Get the pixel dimensions of the screen
        val display = windowManager.defaultDisplay

        // Initialize the result into a Point object
        val size = Point()
        display.getSize(size)

        // Create a new instance of the SnakeEngine class
        snakeEngine = SnakeEngine(this, size)

        // Make snakeEngine the view of the Activity
        setContentView(snakeEngine)
    }
}