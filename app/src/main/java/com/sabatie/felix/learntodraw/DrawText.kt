package com.sabatie.felix.learntodraw

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.sabatie.felix.learntodraw.helpers.BitmapHelper
import java.lang.IllegalArgumentException

class DrawText : AppCompatActivity() {

    private lateinit var stringToDraw: String
    private lateinit var paintView: PaintView
    private lateinit var instructionsText: TextView
    private var charIndex: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_text)
        supportActionBar?.hide()

        stringToDraw = intent.getStringExtra("stringToDraw")
        if (intent.hasExtra("charIndex"))
            charIndex = intent.getIntExtra("charIndex", -1)

        paintView = findViewById(R.id.paintView)
        paintView.stringToDisplay = if(charIndex == null) stringToDraw
            else stringToDraw[charIndex!!].toString()

        val instructions = when (charIndex) {
            0 -> "Maintenant, nous allons apprendre à écrire le mot \"$stringToDraw\" lettre par lettre. " +
                        "Écris la lettre ${stringToDraw[charIndex!!]} en suivant le modèle :"
            null, -1 -> "Maintenant, écris le mot en entier :"
            else -> "Écris la lettre ${stringToDraw[charIndex!!]} en suivant le modèle :"
        }
        instructionsText = findViewById(R.id.instructionsText)
        instructionsText.text = instructions
    }

    override fun onBackPressed() {
        // Do nothing
    }

    fun resetCanvas(v: View) {
        paintView.resetDrawing()
    }

    fun onNextClick(v: View) {
        if(charIndex == null) {
            val congratulationsQuestion = Intent(v.context, CongratulationsQuestion::class.java)
            congratulationsQuestion.putExtra("question", CurrentGame.game.currentQuestion())

            CongratulationsQuestion.drawingBitmap = getDrawingBitmap()

            startActivity(congratulationsQuestion)
        } else {
            val writeView = Intent(v.context, DrawText::class.java)
            writeView.putExtra("stringToDraw", (stringToDraw))
            if(charIndex!! + 1 < stringToDraw.count())
                writeView.putExtra("charIndex", charIndex!! + 1)
            startActivity(writeView)
        }
    }

    private fun getDrawingBitmap(): Bitmap {
        val paintedBitmap = paintView.drawingBitmap
        return try {
            BitmapHelper.addPadding(BitmapHelper.trimBitmap(paintedBitmap), 10)
        } catch (e: IllegalArgumentException) {
            paintedBitmap
        }
    }

}
