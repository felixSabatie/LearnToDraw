package com.sabatie.felix.learntodraw

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class DrawText : AppCompatActivity() {

    private lateinit var stringToDraw: String
    private lateinit var paintView: PaintView
    private lateinit var instructionsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_text)
        supportActionBar?.hide()

        stringToDraw = intent.getStringExtra("stringToDraw")

        paintView = findViewById(R.id.paintView)
        paintView.stringToDisplay = stringToDraw

        val instructions = "Maintenant, écris le mot \"$stringToDraw\" en suivant les traits"
        instructionsText = findViewById(R.id.instructionsText)
        instructionsText.text = instructions
    }

    fun resetCanvas(v: View) {
        paintView.resetDrawing()
    }

}
