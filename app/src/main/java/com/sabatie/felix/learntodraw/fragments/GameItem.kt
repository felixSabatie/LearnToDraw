package com.sabatie.felix.learntodraw.fragments

import android.app.Fragment
import com.sabatie.felix.learntodraw.game.Game

class GameItem: Fragment() {
    private var listener: OnGameItemClick? = null
    lateinit var game: Game

    interface OnGameItemClick {
        fun onGameItemClick(game: Game)
    }
}