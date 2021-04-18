package com.example.ctprojekt.tictactoe

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.example.ctprojekt.R

class MyTicTacToeCanvas(context: Context) : View(context) {
    private var ticTacToeField: MutableList<MutableList<Int?>> = ArrayList()
    private var isCorrect:Boolean = false
    private lateinit var ticTacToeFieldStrokeVert1: Rect
    private lateinit var ticTacToeFieldStrokeVert2: Rect
    private lateinit var ticTacToeFieldStrokeHori1: Rect
    private lateinit var ticTacToeFieldStrokeHori2: Rect
    private val STROKE_WIDTH = 10f
    private var player = "Player1"
    private val drawColor = ResourcesCompat.getColor(resources, R.color.btColorMain, null)
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    init {
        ticTacToeField.add(mutableListOf(null,null,null))
        ticTacToeField.add(mutableListOf(null,null,null))
        ticTacToeField.add(mutableListOf(null,null,null))
    }
    private var paint = Paint().apply {
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
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun drawCentertOval(x: Float, y: Float, r:Float){
        extraCanvas.drawOval((x-r).toFloat(), (y-r).toFloat(), (r*2).toFloat(),(r*2).toFloat(),paint)
    }
    fun drawCentertX(x:Float, y:Float, r: Float){
        paint.color = Color.argb(255, 255, 0, 0)
        extraCanvas.drawRect(x.toFloat(),(y+r).toFloat(),x.toFloat(),(y-r).toFloat(),paint)
        extraCanvas.drawRect((x-r).toFloat(),y,x+r,y,paint)
    }

    //@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onTouchEvent(e: MotionEvent?): Boolean {
        var y:Float = e!!.y
        var x:Float = e!!.x
        findField(player,x,y)
        if (isCorrect){
            when(player){
                "Player1" -> {
                    drawCentertX(findCoordinatesPaint(x), findCoordinatesPaint(y), 20F)
                    player = "Player2"
                }
                "Player2" -> {
                    //drawCentertOval(findCoordinatesPaint(x), findCoordinatesPaint(y),20F)
                    player = "Player1"
                }
            }
        }

        return super.onTouchEvent(e)
    }
    fun findCoordinatesPaint(x:Float):Float{
        when{
            x < width/3 -> return (width/6).toFloat()
            x > width/3 && x < (width/3)*2 -> return (width/2).toFloat()
            x > (width/3)*2 -> return((width/6)*5).toFloat()
            else -> return 0.0F
        }
    }
    fun findField(playerActive: String, x:Float, y: Float){
        when{
            x < width/3 ->
                when{
                    y < width/3 ->
                        {if(ticTacToeField[0][0] == null)ticTacToeField[0][0] = isPlayer(playerActive);isCorrect = true;}
                    y > width/3 && x < (width/3)*2 ->
                        {if(ticTacToeField[0][1] == null)ticTacToeField[0][1] = isPlayer(playerActive);isCorrect = true}
                    y > (width/3)*2 ->
                        {if(ticTacToeField[0][2] == null)ticTacToeField[0][2] = isPlayer(playerActive);isCorrect = true}
                }
            x > width/3 && x < (width/3)*2 ->
                when{
                    y < width/3 ->
                    {if(ticTacToeField[1][0] == null)ticTacToeField[1][0] = isPlayer(playerActive);isCorrect = true}
                    y > width/3 && x < (width/3)*2 ->
                    {if(ticTacToeField[1][1] == null)ticTacToeField[1][1] = isPlayer(playerActive);isCorrect = true}
                    y > (width/3)*2 ->
                    {if(ticTacToeField[1][2] == null)ticTacToeField[1][2] = isPlayer(playerActive);isCorrect = true}
                }
            x > (width/3)*2 ->
                when{
                    y < width/3 ->
                    {if(ticTacToeField[2][0] == null)ticTacToeField[2][0] = isPlayer(playerActive);isCorrect = true}
                    y > width/3 && x < (width/3)*2 ->
                    {if(ticTacToeField[2][1] == null)ticTacToeField[2][1] = isPlayer(playerActive);isCorrect = true}
                    y > (width/3)*2 ->
                    {if(ticTacToeField[2][2] == null)ticTacToeField[2][2] = isPlayer(playerActive);isCorrect = true}
                }
        }
    }

    fun newGame(){

    }
    fun isPlayer(activePlayer:String):Int?{
        when(activePlayer){
            "Player1"-> return 1
            "Player2"-> return 2
            else -> return null
        }
    }

}



