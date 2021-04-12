package com.example.ctprojekt.flappybird

interface GameLoop {
    fun start()
    fun pause()
    fun resume()
    fun update()
    fun draw()
    fun stop()
}