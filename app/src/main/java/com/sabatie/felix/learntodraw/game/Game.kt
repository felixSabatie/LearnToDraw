package com.sabatie.felix.learntodraw.game

import java.io.Serializable

class Game(private val questions: List<Question>, val name: String, val icon: Int) : Serializable {
    private var currentIndex = 0
    var completed = false

    fun currentQuestion(): Question? {
        return if(questions.count() > currentIndex) questions[currentIndex] else null
    }

    fun nextQuestion(): Question? {
        currentIndex++
        return currentQuestion()
    }

    fun hasNext(): Boolean {
        return currentIndex + 1 < questions.count()
    }
}
