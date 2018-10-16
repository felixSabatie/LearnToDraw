package com.sabatie.felix.learntodraw

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sabatie.felix.learntodraw.game.Question

class CongratulationsQuestion : AppCompatActivity() {

    private lateinit var congratulationsTextView: TextView
    private lateinit var questionImage: ImageView
    private lateinit var drawingImage: ImageView
    private lateinit var bottomButton: TextView

    private lateinit var question: Question
    companion object {
        lateinit var drawingBitmap: Bitmap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        question = intent.extras?.getSerializable("question") as Question

        setContentView(R.layout.activity_congratulations_question)

        congratulationsTextView = findViewById(R.id.congratulationsText)
        questionImage = findViewById(R.id.questionImage)
        drawingImage = findViewById(R.id.drawingImage)
        bottomButton = findViewById(R.id.bottomButton)

        val congratulationsText = "Bravo ! Tu sais désormais écrire \"${question.getResponseText()}\""
        congratulationsTextView.text = congratulationsText
        questionImage.setImageResource(question.image)
        drawingImage.setImageBitmap(drawingBitmap)
        bottomButton.text = "Question suivante"
    }

    override fun onBackPressed() {
        // Do nothing
    }

    fun onNextClick(v: View) {
        val question = CurrentGame.game.nextQuestion()
        question?.run {
            val questionView = Intent(v.context, QuestionActivity::class.java)
            questionView.putExtra("question", this)
            startActivity(questionView)
        }
    }
}
