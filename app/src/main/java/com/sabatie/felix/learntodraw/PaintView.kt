package com.sabatie.felix.learntodraw

import android.content.Context
import android.graphics.*
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View

class PaintView(context: Context): View(context) {

    private val TOUCH_TOLERANCE = 4F

    private var paint = Paint()
    val emboss = EmbossMaskFilter(floatArrayOf(1F, 1F, 1F), 0.4F, 6F, 3.5F)
    val blur = BlurMaskFilter(8F, BlurMaskFilter.Blur.NORMAL)
    var path = Path()
    var currentX = 0F
    var currentY = 0F

    private lateinit var viewCanvas: Canvas
    private lateinit var bitmap: Bitmap
    private val bitmapPaint = Paint(Paint.DITHER_FLAG)

    init {
        paint.style = Paint.Style.STROKE
        paint.setARGB(255, 0, 255, 0)
        paint.strokeWidth = 6F
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
        paint.isDither = true
    }

    fun init(metrics: DisplayMetrics) {
        bitmap = Bitmap.createBitmap(metrics.widthPixels, metrics.heightPixels, Bitmap.Config.ARGB_8888)
        viewCanvas = Canvas()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> touchStart(event.x, event.y)
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> touchUp(event.x, event.y)
        }
        invalidate()
        return true
    }

    private fun touchUp(x: Float, y: Float) {
        path.lineTo(x, y)
        viewCanvas.drawPath(path, paint)
        path.reset()
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - currentX)
        val dy = Math.abs(y - currentY)
        if(dx > TOUCH_TOLERANCE || dy > TOUCH_TOLERANCE) {
            path.moveTo(x, y)
            currentX = x
            currentY = y
        }
    }

    private fun touchStart(x: Float, y: Float) {
        path.reset()
        path.moveTo(x, y)
        currentX = x
        currentY = y
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(0xFFAAAAAA.toInt())
        canvas.drawBitmap(bitmap, 0F, 0F, bitmapPaint)
        canvas.drawPath(path, paint)
    }
}