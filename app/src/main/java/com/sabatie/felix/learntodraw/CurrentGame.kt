package com.sabatie.felix.learntodraw

import com.sabatie.felix.learntodraw.game.Game

object CurrentGame {
    var game: Game? = null
    lateinit var gamesList: List<Game>
}