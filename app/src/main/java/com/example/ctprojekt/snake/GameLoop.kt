package com.example.ctprojekt.snake

interface GameLoop {
    fun start()
    fun pause()
    fun resume()
    fun update()
    fun draw()
    fun stop()
}