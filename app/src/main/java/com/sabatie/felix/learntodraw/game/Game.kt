package com.sabatie.felix.learntodraw.game

import java.io.Serializable

class Game(val questions: List<Question>) : Serializable {
    private var currentIndex = 0

    fun currentQuestion(): Question? {
        return if(questions.count() > currentIndex) questions[currentIndex] else null
    }

    fun nextQuestion(): Question? {
        currentIndex++
        return currentQuestion()
    }
}
