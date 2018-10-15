package com.sabatie.felix.learntodraw

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.sabatie.felix.learntodraw.game.Game
import com.sabatie.felix.learntodraw.game.Question
import com.sabatie.felix.learntodraw.game.Response

class MainActivity : AppCompatActivity(), ResponseButton.OnResponseButtonClicked {
    // send to bob.menelas@gmail.com

    private lateinit var responseText: TextView
    private lateinit var nextButton: Button
    private lateinit var responsesContainer: LinearLayout

    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        responsesContainer = findViewById(R.id.responsesContainer)

        initGame()
        addResponses()
    }

    private fun initGame() {
        val responses = ArrayList<Response>()
        val questions = ArrayList<Question>()

        responses.add(Response(false, "Shat"))
        responses.add(Response(false, "Cha"))
        responses.add(Response(false, "Chat"))

        questions.add(Question("Lequel de ces mots représente ce qui est sur l'image ?", responses))

        game = Game(questions)
    }

    private fun addResponses() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        game.questions.first().responses.forEach{
            val fragment = ResponseButton()
            val args = Bundle()
            args.putInt("background", ResourcesCompat.getColor(resources, R.color.backgroundColor1, null))
            fragment.arguments = args

            fragment.response = it
            fragmentTransaction.add(responsesContainer.id, fragment, it.text)
        }
        fragmentTransaction.commit()
    }

    override fun onResponseClicked(response: Response) {
        println("${response.text} clicked")
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

    private fun displayNextButton() {
        nextButton.visibility = View.VISIBLE
    }

    fun navigate(view: View) {
        val writeView = Intent(view.context, DrawText::class.java)
        writeView.putExtra("stringToDraw", "Chat")
        startActivity(writeView)
    }
}
