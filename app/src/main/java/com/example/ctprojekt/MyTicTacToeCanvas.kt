package com.example.ctprojekt

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat

class MyTicTacToeCanvas(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private lateinit var ticTacToeFieldStrokeVert1: Rect
    private lateinit var ticTacToeFieldStrokeVert2: Rect
    private lateinit var ticTacToeFieldStrokeHori1: Rect
    private lateinit var ticTacToeFieldStrokeHori2: Rect
    private val STROKE_WIDTH = 10f
    private val drawColor = ResourcesCompat.getColor(resources, R.color.btColorMain, null)
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE// default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)
    }
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.MainScreen, null)

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)

        ticTacToeFieldStrokeVert1 = Rect(width/3, 0, width/3, height)
        ticTacToeFieldStrokeVert2 = Rect(width/3*2, 0, width/3*2, height)
        ticTacToeFieldStrokeHori1 = Rect(0, height/3, width, height/3)
        ticTacToeFieldStrokeHori2 = Rect(0, height/3*2, width, height/3*2)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)

        canvas.drawRect(ticTacToeFieldStrokeVert1, paint)
        canvas.drawRect(ticTacToeFieldStrokeVert2, paint)
        canvas.drawRect(ticTacToeFieldStrokeHori1, paint)
        canvas.drawRect(ticTacToeFieldStrokeHori2, paint)
    }
}
