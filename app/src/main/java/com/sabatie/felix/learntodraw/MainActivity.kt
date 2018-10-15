package com.sabatie.felix.learntodraw

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // send to bob.menelas@gmail.com
    private lateinit var paintView: PaintView
    private lateinit var responseText: TextView
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        responseText = findViewById(R.id.Response)
        nextButton = findViewById(R.id.nextButton)
        nextButton.visibility = View.INVISIBLE

//        paintView = findViewById(R.id.PaintView)
//        val metrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(metrics)
//        paintView.init(metrics)
    }

    fun displayFalse(view: View) {
        responseText.setTextColor(Color.RED)
        responseText.text = "Mauvais réponse, il fallait dire \"Chat\" !"
        displayNextButton()
    }

    fun displayTrue(view: View) {
        responseText.setTextColor(Color.GREEN)
        responseText.text = "Bonne réponse !"
        displayNextButton()
    }

    fun displayNextButton() {
        nextButton.visibility = View.VISIBLE
    }

    fun navigate(view: View) {
        val writeView = Intent(view.context, DrawText::class.java)
        startActivity(writeView)
    }
}
