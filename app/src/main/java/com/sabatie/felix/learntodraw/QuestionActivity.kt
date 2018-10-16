package com.sabatie.felix.learntodraw

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.sabatie.felix.learntodraw.fragments.ResponseButton
import com.sabatie.felix.learntodraw.fragments.ResponseDialog
import com.sabatie.felix.learntodraw.game.Game
import com.sabatie.felix.learntodraw.game.Question
import com.sabatie.felix.learntodraw.game.Response

class QuestionActivity : AppCompatActivity(), ResponseButton.OnResponseButtonClicked, ResponseDialog.OnNextButtonClicked {

    private lateinit var questionText: TextView
    private lateinit var questionImage: ImageView
    private lateinit var responsesContainer: LinearLayout

    private lateinit var question: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        question = intent.extras?.getSerializable("question") as Question

        responsesContainer = findViewById(R.id.responsesContainer)
        questionText = findViewById(R.id.questionText)
        questionImage = findViewById(R.id.questionImage)
        questionText.text = question.title
        questionImage.setImageResource(question.image)

        addResponses()
    }

    private fun addResponses() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        question.responses.forEachIndexed { index, element ->
            val fragment = ResponseButton()
            val args = Bundle()

            val background = when (index % 3) {
                0 -> ResourcesCompat.getColor(resources, R.color.backgroundColor1, null)
                1 -> ResourcesCompat.getColor(resources, R.color.backgroundColor2, null)
                else -> ResourcesCompat.getColor(resources, R.color.backgroundColor3, null)
            }

            args.putInt("background", background)
            fragment.arguments = args

            fragment.response = element
            fragmentTransaction.add(responsesContainer.id, fragment, element.text)
        }
        fragmentTransaction.commit()
    }

    override fun onResponseClicked(response: Response) {
        val answer = question.responses.find { it.valid } as Response
            displayDialog(response.valid, answer.text)
    }

    private fun displayDialog(success: Boolean, answer: String) {
        val ft = fragmentManager.beginTransaction()
        val prev = fragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        val responseDialog = ResponseDialog()

        val args = Bundle()
        val image = if(success) R.drawable.success else R.drawable.oops
        val text = if(success) "Bravo ! C'était bien \"$answer !\""
        else "Oops, il fallait dire \"$answer\" !"

        args.putInt("resultImage", image)
        args.putString("resultText", text)
        responseDialog.arguments = args
        responseDialog.show(ft, "dialog")
    }

    override fun onNextClick() {
        navigate(findViewById(android.R.id.content))
    }

    private fun navigate(view: View) {
        val writeView = Intent(view.context, DrawText::class.java)
        writeView.putExtra("stringToDraw", "Chat")
        startActivity(writeView)
    }
}
