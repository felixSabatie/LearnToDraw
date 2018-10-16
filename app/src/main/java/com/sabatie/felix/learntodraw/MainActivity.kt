package com.sabatie.felix.learntodraw

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.sabatie.felix.learntodraw.fragments.ResponseButton
import com.sabatie.felix.learntodraw.fragments.ResponseDialog
import com.sabatie.felix.learntodraw.game.Game
import com.sabatie.felix.learntodraw.game.Question
import com.sabatie.felix.learntodraw.game.Response


class MainActivity : AppCompatActivity(), ResponseButton.OnResponseButtonClicked {
    // send to bob.menelas@gmail.com

    private lateinit var responseText: TextView
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
        responses.add(Response(true, "Chat"))

        questions.add(Question("Lequel de ces mots représente ce qui est sur l'image ?", responses))

        game = Game(questions)
    }

    private fun addResponses() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        game.questions.first().responses.forEachIndexed { index, element ->
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
        displayDialog(response.valid)
    }

    private fun displayDialog(success: Boolean) {
        val ft = fragmentManager.beginTransaction()
        val prev = fragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        val responseDialog = ResponseDialog()

        val args = Bundle()
        val image = if(success) R.drawable.success else R.drawable.oops
        val text = if(success) "Bravo ! C'était bien \"Chat !\""
            else "Oops, il fallait dire \"Chat\" !"

        args.putInt("resultImage", image)
        args.putString("resultText", text)
        responseDialog.arguments = args
        responseDialog.show(ft, "dialog")
    }

    fun navigate(view: View) {
        val writeView = Intent(view.context, DrawText::class.java)
        writeView.putExtra("stringToDraw", "Chat")
        startActivity(writeView)
    }
}
