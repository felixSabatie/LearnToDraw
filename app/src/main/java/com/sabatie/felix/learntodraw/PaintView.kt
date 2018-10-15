package com.sabatie.felix.learntodraw

import android.content.Context
import android.graphics.*
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val TOUCH_TOLERANCE = 4F

    private var paint = Paint()
    private var path = Path()
    private var currentX = 0F
    private var currentY = 0F
    lateinit var stringToDisplay: String
    private lateinit var textPaint: Paint

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    // Colors
    private val bitmapBackgroundColor = ResourcesCompat.getColor(resources, R.color.bitmapBackgroundColor, null)
    private val drawingColor = ResourcesCompat.getColor(resources, R.color.drawingColor, null)
    private val textToDrawColor = ResourcesCompat.getColor(resources, R.color.textToDrawColor, null)

    init {
        initPaint()
        initTextPaint()
    }

    private fun initPaint() {
        paint.style = Paint.Style.STROKE
        paint.color = drawingColor
        paint.strokeWidth = 6F
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
        paint.isDither = true
    }

    private fun initTextPaint() {
        textPaint = Paint()
        textPaint.color = textToDrawColor
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 300F
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        extraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        resetCanvasContent()
    }

    private fun resetCanvasContent() {
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(bitmapBackgroundColor)

        val centeredX = extraCanvas.width.toFloat() / 2
        val centeredY = (extraCanvas.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2)
        extraCanvas.drawText(stringToDisplay, centeredX, centeredY, textPaint)
    }

    fun resetDrawing() {
        resetCanvasContent()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart(event.x, event.y)
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> touchUp()
        }
        invalidate()
        return true
    }

    private fun touchUp() {
        path.reset()
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - currentX)
        val dy = Math.abs(y - currentY)
        if (dx > TOUCH_TOLERANCE || dy > TOUCH_TOLERANCE) {
            path.quadTo(currentX, currentY, (x + currentX) / 2, (y + currentY) / 2)
            currentX = x
            currentY = y

            extraCanvas.drawPath(path, paint)
        }
    }

    private fun touchStart(x: Float, y: Float) {
        path.moveTo(x, y)
        currentX = x
        currentY = y
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(0xFFAAAAAA.toInt())
        canvas.drawBitmap(extraBitmap, 0F, 0F, null)
    }
}