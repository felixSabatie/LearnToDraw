package com.sabatie.felix.learntodraw

import android.content.Context
import android.graphics.*
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.graphics.Rect




class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val touchTolerance = 4F
    private val defaultTextSize = 300F
    private val maxTextSize = 600F

    private var paint = Paint()
    private var path = Path()
    private var currentX = 0F
    private var currentY = 0F
    lateinit var stringToDisplay: String
    private lateinit var textPaint: Paint

    private lateinit var drawingCanvas: Canvas
    lateinit var drawingBitmap: Bitmap
    private lateinit var textCanvas : Canvas
    private lateinit var textBitmap: Bitmap

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
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        drawingBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        textBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        resetCanvasContent()
    }

    private fun resetCanvasContent() {
        drawingCanvas = Canvas(drawingBitmap)
        textCanvas = Canvas(textBitmap)
        textCanvas.drawColor(bitmapBackgroundColor)

        setTextSize()
        val centeredX = textCanvas.width.toFloat() / 2
        val centeredY = (textCanvas.height / 2 - (textPaint.descent() + textPaint.ascent()) / 2)
        textCanvas.drawText(stringToDisplay, centeredX, centeredY, textPaint)
    }

    // Set the text size to fit the screen width, with a padding of 25
    private fun setTextSize() {
        textPaint.textSize = defaultTextSize
        val bounds = Rect()
        textPaint.getTextBounds(stringToDisplay, 0, stringToDisplay.count(), bounds)
        val desiredTextSize = defaultTextSize * (drawingBitmap.width - 50) / bounds.width()
        textPaint.textSize = if(desiredTextSize < maxTextSize) desiredTextSize else maxTextSize
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
        if (dx > touchTolerance || dy > touchTolerance) {
            path.quadTo(currentX, currentY, (x + currentX) / 2, (y + currentY) / 2)
            currentX = x
            currentY = y

            drawingCanvas.drawPath(path, paint)
        }
    }

    private fun touchStart(x: Float, y: Float) {
        path.moveTo(x, y)
        currentX = x
        currentY = y
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(0xFFAAAAAA.toInt())
        canvas.drawBitmap(textBitmap, 0F, 0F, null)
        canvas.drawBitmap(drawingBitmap, 0F, 0F, null)
    }
}