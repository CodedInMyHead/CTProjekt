package com.example.ctprojekt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.Size
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.Dimension
//import androidx.constraintlayout.solver.state.Dimension
import java.util.*

class SnakeEngine(context: Context, size: Point): SurfaceView(context), Runnable{





    private var thread: Thread? = null

    // To hold a reference to the Activity

    //TODO("Not running")
    //private val context: Context? = null

    // for plaing sound effects

    // For tracking movement Heading
    enum class Heading {
        UP, RIGHT, DOWN, LEFT
    }

    // Start by heading to the right
    private var heading = Heading.RIGHT

    // To hold the screen size in pixels
    private var screenX = 0
    private var screenY = 0

    // How long is the snake
    private var snakeLength = 0

    // Where is Bob hiding?
    private var bobX = 0
    private var bobY = 0

    // The size in pixels of a snake segment
    private var blockSize = 0

    // The size in segments of the playable area
    private val NUM_BLOCKS_WIDE = 40
    private var numBlocksHigh = 0

    // Control pausing between updates
    private var nextFrameTime: Long = 0

    // Update the game 10 times per second
    private val FPS: Long = 10

    // There are 1000 milliseconds in a second
    private val MILLIS_PER_SECOND: Long = 1000
    // We will draw the frame much more often

    // We will draw the frame much more often
    // How many points does the player have
    private var score = 0

    //The location in the grid of all the segments
    //TODO not running

    private lateinit var snakeXs: MutableList<Int>
    private lateinit var snakeYs: MutableList<Int>

    // Everything we need for drawing
    // Is the game currently playing?

    private var isPlaying = false

    // A canvas for our paint
    private var canvas: Canvas? = null

    // Required to use canvas
    private var surfaceHolder: SurfaceHolder? = null

    // Some paint for our canvas
    private var paint: Paint? = null
    override fun run() {
        while (isPlaying){
            if(updateReqierd()){
                update()
                draw()
            }
        }
    }
    fun pause() {
        isPlaying = false
        try {
            thread!!.join()
        } catch (e: InterruptedException) {
            // Error
        }
    }

    fun resume() {
        isPlaying = true
        thread = Thread(this)
        thread!!.start()

    }


    fun newGame(){
        snakeLength = 1;
        snakeXs[0] = NUM_BLOCKS_WIDE / 2;
        snakeYs[0] = numBlocksHigh / 2;

        // Get Bob ready for dinner
        spawnBob();

        // Reset the score
        score = 0;

        // Setup nextFrameTime so an update is triggered
        nextFrameTime = System.currentTimeMillis();
    }
    fun  spawnBob(){
        val random = Random()
        bobX = random.nextInt(NUM_BLOCKS_WIDE - 1) + 1
        bobY = random.nextInt(numBlocksHigh - 1) + 1
    }
    fun eatBob(){
        snakeLength++
        spawnBob()
        score++
    }
    fun moveSnake(){
        for (i in snakeLength downTo 1) {
            // Start at the back and move it
            // to the position of the segment in front of it
            snakeXs[i] = snakeXs[i - 1]
            snakeYs[i] = snakeYs[i - 1]

            // Exclude the head because
            // the head has nothing in front of it
        }
        when (heading) {
            Heading.UP -> snakeYs[0]--
            Heading.RIGHT -> snakeXs[0]++
            Heading.DOWN -> snakeYs[0]++
            Heading.LEFT -> snakeXs[0]--
        }
    }
    private fun detectDeath(): Boolean {
        // Has the snake died?
        var dead = false

        // Hit the screen edge
        if (snakeXs[0] === -1) dead = true
        if (snakeXs[0] >= NUM_BLOCKS_WIDE) dead = true
        if (snakeYs[0] === -1) dead = true
        if (snakeYs[0] === numBlocksHigh) dead = true

        // Eaten itself?
        for (i in snakeLength - 1 downTo 1) {
            if (i > 4 && snakeXs[0] === snakeXs[i] && snakeYs[0] === snakeYs[i]) {
                dead = true
            }
        }
        return dead
    }
    fun update() {
        // Did the head of the snake eat Bob?
        if (snakeXs[0] === bobX && snakeYs[0] === bobY) {
            eatBob()
        }
        moveSnake()
        if (detectDeath()) {
            //start again

            newGame()
        }
    }
    fun draw(){
        if (surfaceHolder!!.surface.isValid) {
            canvas = surfaceHolder!!.lockCanvas()

            // Fill the screen with Game Code School blue
            canvas!!.drawColor(Color.argb(255, 26, 128, 182))

            // Set the color of the paint to draw the snake white
            paint!!.color = Color.argb(255, 255, 255, 255)

            // Scale the HUD text
            paint!!.textSize = 90f
            canvas!!.drawText("Score:$score", 10f, 70f, paint!!)

            // Draw the snake one block at a time
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
            surfaceHolder!!.unlockCanvasAndPost(canvas)
        }
    }
    fun updateReqierd():Boolean{
        if(nextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            nextFrameTime =System.currentTimeMillis() + MILLIS_PER_SECOND / FPS;

            // Return true so that the update and draw
            // functions are executed
            return true;
        }

        return false;
    }
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_UP -> heading = if (motionEvent.x >= screenX / 2) {
                when (heading) {
                    Heading.UP -> Heading.RIGHT
                    Heading.RIGHT -> Heading.DOWN
                    Heading.DOWN -> Heading.LEFT
                    Heading.LEFT -> Heading.UP
                }
            } else {
                when (heading) {
                    Heading.UP -> Heading.LEFT
                    Heading.LEFT -> Heading.DOWN
                    Heading.DOWN -> Heading.RIGHT
                    Heading.RIGHT -> Heading.UP
                }
            }
        }
        return true
    }

    init {

        var context = context
        context = context

        screenX = size.x
        screenY = size.y

        // Work out how many pixels each block is
        blockSize = screenX / NUM_BLOCKS_WIDE
        // How many blocks of the same size will fit into the height
        numBlocksHigh = screenY / blockSize

        // Set the sound up



        // Initialize the drawing objects
        surfaceHolder = holder
        paint = Paint()

        // If you score 200 you are rewarded with a crash achievement!
        snakeXs = mutableListOf(200)
        snakeYs = mutableListOf(200)

        // Start the game
        newGame()

    }
}
