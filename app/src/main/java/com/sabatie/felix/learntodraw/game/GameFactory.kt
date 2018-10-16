package com.sabatie.felix.learntodraw.game

import com.sabatie.felix.learntodraw.R

class GameFactory {
    companion object {
        fun createGames(): List<Game> {
            return arrayListOf<Game>(createAnimalsGame(), createCharactersGame())
        }

        private fun createAnimalsGame(): Game {
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

            return Game(questions, "Animaux", R.drawable.cat)
        }

        private fun createCharactersGame(): Game {
            val responses = ArrayList<Response>()
            val questions = ArrayList<Question>()

            responses.add(Response(true, "Princesse"))
            responses.add(Response(false, "Princèce"))
            responses.add(Response(false, "Prinssesse"))

            questions.add(Question("Lequel de ces mots représente ce qui est sur l'image ?",
                    responses, R.drawable.princess))

            val responses2 = ArrayList<Response>()
            responses2.add(Response(false, "Robo"))
            responses2.add(Response(true, "Robot"))
            responses2.add(Response(false, "Raubot"))

            questions.add(Question("Lequel de ces mots représente ce qui est sur l'image ?",
                    responses2, R.drawable.robot))

            return Game(questions, "Personnages", R.drawable.robot)
        }
    }
}