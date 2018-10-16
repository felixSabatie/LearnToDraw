package com.sabatie.felix.learntodraw

import com.sabatie.felix.learntodraw.game.Game
import com.sabatie.felix.learntodraw.game.Question
import com.sabatie.felix.learntodraw.game.Response

object CurrentGame {
    lateinit var game: Game

    fun initGame() {
        val responses = ArrayList<Response>()
        val questions = ArrayList<Question>()

        responses.add(Response(false, "Shat"))
        responses.add(Response(false, "Cha"))
        responses.add(Response(true, "Chat"))

        questions.add(Question("Lequel de ces mots représente ce qui est sur l'image ?",
                responses, R.drawable.cat))

        val responses2 = ArrayList<Response>()
        responses2.add(Response(false, "Kochon"))
        responses2.add(Response(true, "Cochon"))
        responses2.add(Response(false, "Cauchon"))

        questions.add(Question("Lequel de ces mots représente ce qui est sur l'image ?",
                responses2, R.drawable.pig))

        game = Game(questions)
    }
}