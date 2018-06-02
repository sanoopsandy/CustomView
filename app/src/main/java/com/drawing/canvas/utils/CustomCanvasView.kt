package com.drawing.canvas.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.drawing.canvas.models.RectCoordinate

class CustomCanvasView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paintTopRight: Paint
    private val paintTopLeft: Paint
    private var isTouched: Boolean
    private var clickedX: Float
    private var clickedY: Float
    private var defaultRect: RectF
    private var listOfRectF: ArrayList<RectCoordinate>

    companion object {
        val BASE_SCALE = 100
    }

    init {
        isTouched = false
        listOfRectF = ArrayList()
        clickedX = 0f
        clickedY = 0f
        paintTopLeft = Paint()
        paintTopRight = Paint()
        defaultRect = RectF()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isTouched) {
            paintTopRight.style = Paint.Style.STROKE
            paintTopRight.color = Color.parseColor("#2196F3")

            listOfRectF.forEach {
                canvas?.drawRect(it.rectf, paintTopRight)
            }

        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                val x = event.getX(0)
                val y = event.getY(0)
                val clickedRectF = RectF()
                clickedRectF.set((x - 100), (y - 100), (x + 100), (y + 100))
                val rectCoordinate = RectCoordinate(clickedRectF, x, y)
                listOfRectF.add(rectCoordinate)
                isTouched = true
                invalidate()
                return true
            }
            else -> return super.onTouchEvent(event)
        }
    }

    fun modifyListOfRectF(newListOfRectF: ArrayList<RectCoordinate>) {
        listOfRectF = newListOfRectF
        invalidate()
    }

    fun getListOfRectF(): ArrayList<RectCoordinate> {
        return listOfRectF
    }

}