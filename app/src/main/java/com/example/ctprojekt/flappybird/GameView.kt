package com.example.ctprojekt.flappybird

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*

class GameView: SurfaceHolder.Callback, SurfaceView, GameLoop, Runnable {
    private var mPaint: Paint? = null
    private var mContext: Context? = null
    private var mHolder: SurfaceHolder? = null
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mThread: Thread? = null
    private var mCanvas: Canvas? = null
    private var mRunning: Boolean = false
    private var blockSize: Int = 0
    private val NUM_BLOCKS_WIDE = 55
    private var numBlocksHigh = 0
    private var FPS = 30
        set(value) {
            field = value
        }
    private var timeToUpdate = 0L

    //private var snakeLength = 0
    private var score = 0

    //private lateinit var snakeXs: IntArray
    //private lateinit var snakeYs: IntArray
    private var birdX: Int = 10
    private var birdY: Int = 5
    private lateinit var pillarX: IntArray
    private lateinit var pillarYUnten: IntArray
    private lateinit var pillarYOben: IntArray
    private var pillarCounter = 0
    enum class Heading {
        UP, DOWN
    }

    private var heading = Heading.DOWN
    private var canvas: Canvas? = null
    private var paint: Paint? = null
    private var size: Point = Point()

    constructor(ctx: Context, screenPoint: Point) : super(ctx) {
        size = screenPoint
        mWidth = size.x
        mHeight = size.y



        // Work out how many pixels each block is
        blockSize = mWidth / NUM_BLOCKS_WIDE
        // How many blocks of the same size will fit into the height
        numBlocksHigh = mHeight / blockSize
        newGame()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        pillarYOben = IntArray(200)
        pillarYUnten = IntArray(200)
        pillarX = IntArray(200)
        paint = Paint()
        mContext = context
        mHolder = holder
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG) //keine Kantenklättung
        mPaint?.setColor(Color.RED)
        timeToUpdate = System.currentTimeMillis()
        when {
            mHolder != null -> mHolder?.addCallback(this)//Informationen Änderungen
        }
        //alpha = 0f
        mThread = Thread(this)
    }

    fun newGame() {
        // Start with a single snake segment
        // init
        birdX = 10
        birdY = 5
        // Get Bob ready for dinner
        pillarX = IntArray(200)
        pillarYOben = IntArray(200)
        pillarYUnten = IntArray(200)
        pillarCounter = 0

        spawnPillar()

        // Reset the score
        score = 0
        run()
        // Setup nextFrameTime so an update is triggered
        //nextFrameTime = System.currentTimeMillis()
    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d(this.toString(), "surface changed")
        //this.mHolder = holder
        this.mWidth = width
        this.mHeight = height
        Log.d("surface changed", width.toString() + " : " + height)

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d(this.toString(), "surface destroyed")
        //this.mHolder = holder
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.d(this.toString(), "surface created")
        //this.mHolder = holder
    }

    override fun run() {
        while (mRunning) {
            if (timeToUpdate()) {
                update()
                draw()
                Thread.sleep( 100-score.toLong())
            }

        }
    }

    fun timeToUpdate(): Boolean {
        return true
    }


    override fun stop() {

    }

    override fun start() {
        newGame()
    }

    override fun pause() {
        mRunning = false
    }

    override fun resume() {
        mRunning = true
        mThread = Thread(this)
        mThread?.start()
    }

    fun spawnPillar() {
        val random = Random()
        pillarCounter++
        var x = random.nextInt(numBlocksHigh) + 1
        pillarX[pillarCounter-1] = NUM_BLOCKS_WIDE
        pillarYUnten[pillarCounter-1] = x
        pillarYOben[pillarCounter-1] = x - 5
    }

    private fun collidePillar() {
        newGame()
    }

    private fun moveBird() {
        // Move the body
        when (heading) {
            Heading.UP -> birdY--
            Heading.DOWN -> birdY++

        }
        heading = Heading.DOWN
    }
    private fun movePillar(){
        for(i in 0 until pillarCounter){
            pillarX[i] --
        }
        if (pillarX[0] < 0){
            pillarCounter--
            score++
            for (i in 0 until pillarCounter){
                pillarX[i] = pillarX[i+1]
                pillarYOben[i] = pillarYOben[i+1]
                pillarYUnten[i] = pillarYUnten[i+1]
            }
        }

    }

    override fun update() {
        // Did the head of the snake eat Bob?
        if (birdY < pillarYOben[0] || birdY > pillarYUnten[0]){ if(birdX == pillarX[0])collidePillar()}
        for (i in 0 until pillarCounter){
            if (pillarX[i] == 20){
                spawnPillar()
            }
        }

        moveBird()
        movePillar()
    }

    override fun draw() {
        // Get a lock on the canvas
        if (mHolder!!.surface.isValid == true) {
            canvas = mHolder!!.lockCanvas()

            // Fill the screen with Game Code School blue
            canvas!!.drawColor(Color.argb(255, 26, 128, 182))

            // Scale the HUD text
            paint!!.color = Color.argb(255, 255,255,255)
            paint!!.textSize = 90f
            canvas!!.drawText("Score:$score", 10f, 70f, paint!!)
            paint!!.color = Color.argb(255, 255, 0, 0)
            // Set the color of the paint to draw the bird red
            // Draw the Bird
            canvas!!.drawRect(
                (birdX * blockSize).toFloat(),
                (birdY * blockSize).toFloat(),
                (birdX * blockSize + blockSize).toFloat(),
                (birdY * blockSize + blockSize).toFloat(),
                paint!!
            )


            // Set the color of the paint to draw Bob red
            paint!!.color = Color.argb(255, 0, 255, 0)

            // Draw Pillars
            for(i in 0 until pillarCounter){

                canvas!!.drawRect(
                    (pillarX[i]*blockSize).toFloat(),
                    (0).toFloat(),
                    (pillarX[i]*blockSize  + 2 * blockSize).toFloat(),
                    (pillarYOben[i]*blockSize).toFloat(),
                    paint!!
                )
                canvas!!.drawRect(
                    (pillarX[i]*blockSize).toFloat(), (pillarYUnten[i]*blockSize).toFloat(), (pillarX[i]*blockSize + 2 * blockSize).toFloat(), (mHeight).toFloat(),paint!!
                )



            }


            // Unlock the canvas and reveal the graphics for this frame
            mHolder!!.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_UP -> heading = Heading.UP
        }
        return true
    }
}

