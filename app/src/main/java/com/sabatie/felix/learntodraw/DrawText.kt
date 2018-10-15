package com.sabatie.felix.learntodraw

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class DrawText : AppCompatActivity() {

    private lateinit var paintView: PaintView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_text)

        paintView = findViewById(R.id.paintView)
        paintView.stringToDisplay = intent.getStringExtra("stringToDraw")
    }

}
