package com.example.ctprojekt.snake
import android.graphics.*
import android.util.Log
import kotlin.collections.ArrayList

/**
 * @author Tung Nguyen (petaminds)
 * The worm always moves to the destination with the speed defined by movement vector
 */
class Snakee : Collidable, Runnable {


    private var mPaint = Paint()
    private var mPath = Path()
    var state = SpriteState.ALIVE

    private var worm_pace = 0f
    private var timeToMove = 0L

    @Volatile
    private var mCurrentMoveVector = PointF()
    private var mTargetMoveVec = PointF()
    private var mSize = 300.0f
        set(value) {
            field = value
        }
    private var bodyPoints = ArrayList<PointF>()
    private val BODY_SEGMENTS = 50

    private val mHeadPoints = arrayOf(PointF(), PointF(), PointF(), PointF())
    private val mHeadTracker = PointF()
    private var mHeadRadial = 0.0 //Moving up and down witn sinus phase
    private var mMoveAmplitude = 0f //head moving amplitude

    private var mAnimationThread: Thread? = null
    private val mAnimationFR = 26//5 frames /sec

    @Volatile
    private var isRotatingHead = false
    private var mRotateHeadRunnable = Runnable {
        val dx = (mTargetMoveVec.x - mCurrentMoveVector.x) / mAnimationFR
        val dy = (mTargetMoveVec.y - mCurrentMoveVector.y) / mAnimationFR



        mCurrentMoveVector.set(mTargetMoveVec)
        isRotatingHead = false
    }

    var destination = PointF()
        set(value) {
            if (!field.equals(value)) {
                field.set(value)
                updateDirection()
            }
            Log.i(this.toString(), destination.toString())
        }

    init {
        mPath.cubicTo(mSize / 3, -mSize / 5, mSize * 2 / 3, mSize / 5, mSize, 0f)
        val pm = PathMeasure(mPath, false)

        for (f in 0..BODY_SEGMENTS) {
            val aBodyPoint = FloatArray(2)
            pm.getPosTan(pm.length * f / BODY_SEGMENTS, aBodyPoint, null)
            bodyPoints.add(PointF(aBodyPoint[0], aBodyPoint[1]))
        }

        worm_pace = pm.length / BODY_SEGMENTS
        mCurrentMoveVector.set(-worm_pace, 0f)
        mTargetMoveVec.set(mCurrentMoveVector)
        mMoveAmplitude = 2 * worm_pace

        mPath.reset()

        val startP = PointF(0f, 15f)
        val r90Vec = PointF(0f, -1f)
        mHeadPoints.forEach {

            startP.set(it)
        }

        mPaint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 10f
            isAntiAlias = true
            textSize = 60f
        }

        mAnimationThread = Thread(this)
        mAnimationThread?.start()
    }

    override fun collide(o: Collidable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     *
     */
    private fun move() {
        mHeadTracker.offset(mCurrentMoveVector.x, mCurrentMoveVector.y)
        //move zigzag
        mHeadRadial += 2 * Math.PI / mAnimationFR

        val dxOrigin = 0f
        val dyOrigin = mMoveAmplitude * Math.sin(mHeadRadial)

        //update body part
        for (i in (bodyPoints.size - 1) downTo 1) {
            bodyPoints[i].set(bodyPoints[i - 1])
        }

        //Location of head
       // bodyPoints[0].set
        /*
            mHeadTracker.x + dTranspose.x,
            mHeadTracker.y + dTranspose.y

         */

        //)

    }

    fun translateTo(x: Float, y: Float) {
        val dx = x - bodyPoints[0].x
        val dy = y - bodyPoints[0].y

        for (i in 0..bodyPoints.size - 1) {
            bodyPoints[i].offset(dx, dy)
        }
        mHeadTracker.set(bodyPoints[0])

    }

    private fun getHeadPoints(): ArrayList<PointF> {
        val hps = arrayListOf<PointF>()
        mHeadPoints.forEach {

            hps.last().offset(bodyPoints[0].x, bodyPoints[0].y)
        }
        return hps
    }

    fun draw(c: Canvas?) {
        //draw the head
        val hps = getHeadPoints()

        mPath.moveTo(hps.last().x, hps.last().y)
        hps.forEach { mPath.lineTo(it.x, it.y) }
        mPath.close()

        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.setColor(Color.BLACK)
        c?.drawPath(mPath, mPaint)

        //draw body
        mPaint.style = Paint.Style.STROKE
        mPaint.setColor(Color.BLACK)

        mPath.moveTo(bodyPoints[0].x, bodyPoints[0].y)
        for (point in bodyPoints) {
            mPath.lineTo(point.x, point.y)
        }
        c?.drawPath(mPath, mPaint)

        //draw nose
        mPaint.style = Paint.Style.FILL_AND_STROKE
        val nose = PointF(hps[0]!!.x, hps[0].y)
        mPaint.setColor(Color.GREEN)

        c?.drawPoint(
            nose.x, nose.y,
            mPaint
        )
        mPath.reset()
    }

    override fun run() {
        while (state == SpriteState.ALIVE) {
            if (isTimeToMove()) {
                move()
            }
        }
    }

    private fun isTimeToMove(): Boolean {
        val now = System.currentTimeMillis()
        if (timeToMove < now) {
            timeToMove = now + 1000L / mAnimationFR
            return true
        }
        return false
    }

    private fun updateDirection() {
        isRotatingHead = false
        val v = PointF(destination.x - mHeadTracker.x, destination.y - mHeadTracker.y)
        //val sc = Maths.VecSinCos(v)
        /*
        mTargetMoveVec.set(
            worm_pace * sc.x,
            worm_pace * sc.y
        )

         */
        isRotatingHead = true

        Thread(mRotateHeadRunnable).start()
    }
}

