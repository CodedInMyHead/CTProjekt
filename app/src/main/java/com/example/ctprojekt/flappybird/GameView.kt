package com.example.ctprojekt.flappybird

import android.content.Context
import android.content.SharedPreferences
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
    //Variablen deklaration
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
    /*
    secundär Constructer welchenn ich benutze
     */
    constructor(ctx: Context, screenPoint: Point) : super(ctx) {
        //Größe des display speichern
        size = screenPoint
        mWidth = size.x
        mHeight = size.y


        //den Bilschirm in Quadrate/Blöcke einteilen zur leicheren Berechnung
        blockSize = mWidth / NUM_BLOCKS_WIDE
        numBlocksHigh = mHeight / blockSize
        newGame()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
    /*
    Initalisierung: Gibt den Variablen sinnvolle Werte
     */
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
        //reset Bird Coordinaten
        birdX = 10
        birdY = 5
        // reset Hindernisse

        pillarX = IntArray(200)
        pillarYOben = IntArray(200)
        pillarYUnten = IntArray(200)
        pillarCounter = 0

        spawnPillar()

        // Reset the score
        score = 0
        run()
    }


    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d(this.toString(), "surface changed")
        //this.mHolder = holder
        // anpassung der Bilschirmgröße falls sie sich ändert
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
                Thread.sleep(100 - when {
                    score < 50 -> score.toLong(); else -> 70
                })
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
        //pause ist um zu Pausieren wenn die App in Standby gestzt wird
        mRunning = false
    }

    override fun resume() {
        //resum ist um nach einer Pause zum Beispiel wenn die App in den Standby gestzt wurde wieder das Spiel zu starten
        mRunning = true
        mThread = Thread(this)
        mThread?.start()
    }

    fun spawnPillar() {
        // erstelle ein Randomobjekt
        val random = Random()
        // Increment pillarcounter um zu wissen wie viel verschiedene Hindernisse gerade auf dem Bildschirm zu sehen sind
        pillarCounter++
        // weise X einen zufallswert zwischen 0 und numBlocksHigh zu
        var x = random.nextInt(numBlocksHigh) + 1
        // hinderniss wird immer ganz links erzeugt
        pillarX[pillarCounter - 1] = NUM_BLOCKS_WIDE
        // die Y coordinaten werden so gemacht das 5 Blöcke zwischen Frei sind, um mit dem Vogel Durchfliegen zu können
        pillarYUnten[pillarCounter - 1] = x
        pillarYOben[pillarCounter - 1] = x - 5
    }

    private fun collidePillar() {

        //erstelle ein neues Spiel
        newGame()
    }

    private fun moveBird() {
        // Move the body
        when (heading) {
            // - da Das Koordinatensystem oben links in der Bildschirmecke bei 00 Beginnt
            Heading.UP -> birdY -= 2
            Heading.DOWN -> birdY++
        }
        //setzt Blickrichtung wider nach unten da der Vogel immer wenn mann nicht Klickt nach unten Fliegen soll
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
                pillarX[i] = pillarX[i + 1]
                pillarYOben[i] = pillarYOben[i + 1]
                pillarYUnten[i] = pillarYUnten[i + 1]
            }
        }

    }

    override fun update() {
        // Kollidiert Hindernis?
        if (birdY < pillarYOben[0] || birdY > pillarYUnten[0]){ if(birdX == pillarX[0])collidePillar()}
        /*
        Schau alle Hindernisse an und wenn eins auf der X coordinate 20 ist spawne ein weiteres
         */
        for (i in 0 until pillarCounter){
            if (pillarX[i] == 20){
                spawnPillar()
            }
        }
        //bewegt Vogel
        moveBird()
        //bewegt Hidnernis
        movePillar()
    }

    override fun draw() {

        if (mHolder!!.surface.isValid == true) {
            //lockt das Canvas das alle sachen gleichzeitig neu Gezeichnet werden
            canvas = mHolder!!.lockCanvas()

            // Gamescreen = blue
            canvas!!.drawColor(Color.argb(255, 26, 128, 182))

            // Stellt Größe und Fabe von dem Text ein
            paint!!.color = Color.argb(255, 255, 255, 255)
            paint!!.textSize = 90f
            //Malt den Score Text
            canvas!!.drawText("Score:$score", 10f, 70f, paint!!)
            //Farbe = Red
            paint!!.color = Color.argb(255, 255, 0, 0)
            // Draw the Bird
            canvas!!.drawRect(
                    (birdX * blockSize).toFloat(),
                    (birdY * blockSize).toFloat(),
                    (birdX * blockSize + blockSize).toFloat(),
                    (birdY * blockSize + blockSize).toFloat(),
                    paint!!
            )


            // Color = Green
            paint!!.color = Color.argb(255, 0, 255, 0)

            // Draw Pillers/Hindernisse
            for(i in 0 until pillarCounter){

                canvas!!.drawRect(
                        (pillarX[i] * blockSize).toFloat(),
                        (0).toFloat(),
                        (pillarX[i] * blockSize + 2 * blockSize).toFloat(),
                        (pillarYOben[i] * blockSize).toFloat(),
                        paint!!
                )
                canvas!!.drawRect(
                        (pillarX[i] * blockSize).toFloat(), (pillarYUnten[i] * blockSize).toFloat(), (pillarX[i] * blockSize + 2 * blockSize).toFloat(), (mHeight).toFloat(), paint!!
                )

            }


            // Unlock Canvas das das Bild aktualisiert wird
            mHolder!!.unlockCanvasAndPost(canvas)
        }
    }
    // Touchevent + Touchlistener
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        /*
        bei einer Berührng des Bildschims wird die Blickrichtung des Vogel nach oben gesetzt
        die Blickrichtung wird dann in der Methode moveBird in die Bewegungsrichtung umgestellt
         */
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_UP -> heading = Heading.UP
        }
        return true
    }
}

