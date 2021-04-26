package com.example.ctprojekt.snake


import android.app.Activity
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


class GameView : SurfaceView, SurfaceHolder.Callback, GameLoop, Runnable {

    private var mContext: Context? = null
    private var mHolder: SurfaceHolder? = null
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mThread: Thread? = null
    private var mCanvas: Canvas? = null
    private var mRunning: Boolean = false
    private var blockSize: Int = 0
    private val NUM_BLOCKS_WIDE = 40
    private var numBlocksHigh = 0
    /*
    private var FPS = 30
        set(value) {
            field = value
        }

     */
    private var timeToUpdate = 0L
    private var bobX = 0
    private var bobY = 0
    private var snakeLength = 0
    private var score = 0
    private lateinit var snakeXs: IntArray
    private lateinit var snakeYs: IntArray
    enum class Heading {
        UP, RIGHT, DOWN, LEFT
    }
    private var heading = Heading.RIGHT
    private var canvas:Canvas? = null
    private var paint: Paint? = null
    private var size: Point = Point()

    constructor(ctx: Context, screenPoint: Point) : super(ctx){
        size = screenPoint
        mWidth = size.x
        mHeight = size.y

        blockSize = mWidth / NUM_BLOCKS_WIDE

        numBlocksHigh = mHeight / blockSize
        newGame()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init{
        snakeXs = IntArray(200)
        snakeYs = IntArray(200)
        paint = Paint()
        mContext = context
        mHolder = holder
        timeToUpdate = System.currentTimeMillis()
        when {
            mHolder != null -> mHolder?.addCallback(this)//Informationen Änderungen
        }
        //alpha = 0f
        mThread = Thread(this)
    }

    fun newGame() {
        // Start with a single snake segment
        snakeLength = 1
        snakeXs[0] = NUM_BLOCKS_WIDE / 2
        snakeYs[0] = numBlocksHigh / 2

        // Get Bob ready for dinner
        spawnBob()

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
        while (mRunning){
            if(timeToUpdate()){
                update()
                draw()
                Thread.sleep(100)
            }

        }
    }
    fun timeToUpdate():Boolean {
        return true
    }


    override fun stop() {
        mRunning = false
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun pause() {
        mRunning = false
    }

    override fun resume() {
        mRunning = true
        mThread = Thread(this)
        mThread?.start()
    }
    fun spawnBob() {
        val random = Random()
        bobX = random.nextInt(NUM_BLOCKS_WIDE - 1) + 1
        bobY = random.nextInt(numBlocksHigh - 1) + 1
    }
    private fun eatBob() {
        // wenn appfle gegessen dann erhöhe score und länge
        snakeLength++
        score += 1
        //erstelle einen neuen Apfel
        spawnBob()
    }
    private fun moveSnake() {
        //Bewegeung des Körpers
        for (i in snakeLength downTo 1) {
            snakeXs[i] = snakeXs[i - 1]
            snakeYs[i] = snakeYs[i - 1]
        }
        when (heading) {
            Heading.UP -> snakeYs[0]--
            Heading.RIGHT -> snakeXs[0]++
            Heading.DOWN -> snakeYs[0]++
            Heading.LEFT -> snakeXs[0]--
        }
    }
    private fun detectDeath(): Boolean {
        // Gestorben?
        var dead = false

        // Hit the screen edge
        if (snakeXs[0] == -1) dead = true
        if (snakeXs[0] >= NUM_BLOCKS_WIDE + 2) dead = true
        if (snakeYs[0] == -1) dead = true
        if (snakeYs[0] == numBlocksHigh + 1) dead = true

        // Selbstgegessen
        for (i in snakeLength - 1 downTo 1) {
            if (i > 4 && snakeXs[0] === snakeXs[i] && snakeYs[0] === snakeYs[i]) {
                dead = true
            }
        }
        return dead
    }
    override fun update() {
        // apfel gegessen
        if (snakeXs[0] === bobX && snakeYs[0] === bobY) {
            eatBob()
        }
        moveSnake()
        if (detectDeath()) {
            (context as Activity).finish()
            newGame()
        }
    }
    override fun draw() {
        if (mHolder!!.surface.isValid == true) {
            //lock canvas das alles gleichzeitig geupdatet wird
            canvas = mHolder!!.lockCanvas()

            // Blauer hintergrund
            canvas!!.drawColor(Color.argb(255, 26, 128, 182))

            // Schlangenfarbe
            paint!!.color = Color.argb(255, 255, 255, 255)
            //text malen
            paint!!.textSize = 90f
            canvas!!.drawText("Score:$score", 10f, 70f, paint!!)

            //Male Schlange
            for (i in 0 until snakeLength) {
                canvas!!.drawRect(
                        (snakeXs[i] * blockSize).toFloat(),
                        (snakeYs[i] * blockSize).toFloat(),
                        (snakeXs[i] * blockSize + blockSize).toFloat(),
                        (snakeYs[i] * blockSize + blockSize).toFloat(),
                        paint!!
                )
            }

            // Set the color of the paint to draw Bob red
            paint!!.color = Color.argb(255, 255, 0, 0)

            // Draw Bob
            canvas!!.drawRect(
                    (bobX * blockSize).toFloat(),
                    (bobY * blockSize).toFloat(), (
                    bobX * blockSize + blockSize).toFloat(), (
                    bobY * blockSize + blockSize).toFloat(),
                    paint!!
            )

            // Unlock the canvas and reveal the graphics for this frame
            mHolder!!.unlockCanvasAndPost(canvas)
        }
    }
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_UP -> if (motionEvent.x >= mWidth / 2) {
                when (heading) {
                    Heading.UP -> heading = Heading.RIGHT
                    Heading.RIGHT -> heading = Heading.DOWN
                    Heading.DOWN -> heading = Heading.LEFT
                    Heading.LEFT -> heading = Heading.UP
                }
            } else {
                when (heading) {
                    Heading.UP -> heading = Heading.LEFT
                    Heading.LEFT -> heading = Heading.DOWN
                    Heading.DOWN -> heading = Heading.RIGHT
                    Heading.RIGHT -> heading = Heading.UP
                }
            }
        }
        return true
    }
}

