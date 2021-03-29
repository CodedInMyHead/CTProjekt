package com.example.ctprojekt.snake


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView


class GameView : SurfaceView, SurfaceHolder.Callback, GameLoop, Runnable {


    private var mPaint: Paint? = null
    private var mContext: Context? = null
    private var mHolder: SurfaceHolder? = null
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mThread: Thread? = null
    private var mCanvas: Canvas? = null
    private var mRunning: Boolean = false
    private var snakee: Snakee? = null
    private var FPS = 30
        set(value) {
            field = value
        }
    private var timeToUpdate = 0L

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    init {
        mContext = context
        mHolder = holder
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.setColor(Color.RED)
        snakee = Snakee()
        timeToUpdate = System.currentTimeMillis()
        when {
            mHolder != null -> mHolder?.addCallback(this)
        }
        //alpha = 0f
        mThread = Thread(this)

    }

    override fun run() {
        while (mRunning) {
            if (timeToUpdate()) {
                update()
                draw()
                Thread.sleep(16)
            }
        }
    }

    private fun timeToUpdate(): Boolean {
        if (timeToUpdate <= System.currentTimeMillis()) {
            return true
        }
        return false
    }


    override fun draw() {
        if (mHolder?.surface?.isValid == true) {
            mCanvas = mHolder?.lockCanvas()
            mCanvas?.drawColor(Color.WHITE)
            snakee?.draw(mCanvas)
            mHolder?.unlockCanvasAndPost(mCanvas)
        }
    }

    override fun start() {

    }

    override fun pause() {
        mRunning = false
        try {
            // Stop the thread == rejoin the main thread.
            mThread?.join()
        } catch (e: InterruptedException) {
        }

    }

    override fun resume() {
        mRunning = true
        mThread = Thread(this)
        mThread?.start()
    }

    override fun update() {
        timeToUpdate += 1000 / FPS
    }

    override fun stop() {
        snakee?.state = SpriteState.DEAD
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d(this.toString(), "surface changed")
        //this.mHolder = holder
        this.mWidth = width
        this.mHeight = height
        Log.d("surface changed", width.toString() + " : " + height)
        snakee?.translateTo(mWidth.toFloat(), mHeight.toFloat() / 2)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d(this.toString(), "surface destroyed")
        //this.mHolder = holder
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.d(this.toString(), "surface created")
        //this.mHolder = holder
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val mash = event?.actionMasked
        when (mash) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> event?.x?.let {
                snakee?.destination = PointF(it, event.y)
            }
        }

        return true
    }
}

