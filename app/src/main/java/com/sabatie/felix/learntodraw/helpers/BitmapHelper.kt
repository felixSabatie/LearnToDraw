package com.sabatie.felix.learntodraw.helpers

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Canvas




class BitmapHelper {
    companion object {
        // Source : https://stackoverflow.com/questions/32797295/how-to-remove-blank-space-around-an-image-in-android
        fun trimBitmap(bmp: Bitmap): Bitmap {
            val imgHeight = bmp.height
            val imgWidth = bmp.width


            //TRIM WIDTH - LEFT
            var startWidth = 0
            for (x in 0 until imgWidth) {
                if (startWidth == 0) {
                    for (y in 0 until imgHeight) {
                        if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                            startWidth = x
                            break
                        }
                    }
                } else
                    break
            }


            //TRIM WIDTH - RIGHT
            var endWidth = 0
            for (x in imgWidth - 1 downTo 0) {
                if (endWidth == 0) {
                    for (y in 0 until imgHeight) {
                        if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                            endWidth = x
                            break
                        }
                    }
                } else
                    break
            }


            //TRIM HEIGHT - TOP
            var startHeight = 0
            for (y in 0 until imgHeight) {
                if (startHeight == 0) {
                    for (x in 0 until imgWidth) {
                        if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                            startHeight = y
                            break
                        }
                    }
                } else
                    break
            }


            //TRIM HEIGHT - BOTTOM
            var endHeight = 0
            for (y in imgHeight - 1 downTo 0) {
                if (endHeight == 0) {
                    for (x in 0 until imgWidth) {
                        if (bmp.getPixel(x, y) != Color.TRANSPARENT) {
                            endHeight = y
                            break
                        }
                    }
                } else
                    break
            }


            return Bitmap.createBitmap(
                    bmp,
                    startWidth,
                    startHeight,
                    endWidth - startWidth,
                    endHeight - startHeight
            )

        }

        // Source : https://stackoverflow.com/questions/6957032/android-padding-left-a-bitmap-with-white-color
        fun addPadding(Src: Bitmap, padding: Int): Bitmap {
            val outputimage = Bitmap.createBitmap(Src.width + padding * 2, Src.height + padding * 2, Bitmap.Config.ARGB_8888)
            val can = Canvas(outputimage)
            can.drawBitmap(Src, padding.toFloat(), padding.toFloat(), null)
            return outputimage
        }
    }

}