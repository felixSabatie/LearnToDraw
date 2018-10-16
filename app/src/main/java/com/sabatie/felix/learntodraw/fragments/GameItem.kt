package com.sabatie.felix.learntodraw.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.sabatie.felix.learntodraw.R
import com.sabatie.felix.learntodraw.game.Game

class GameItem: Fragment() {
    private var listener: OnGameItemClick? = null
    lateinit var game: Game

    lateinit var gameTitle: TextView
    lateinit var gameImage: ImageView
    lateinit var completedImage: ImageView
    lateinit var gameItemContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflatedView = inflater.inflate(R.layout.fragment_game_item, container, false)

        gameTitle = inflatedView.findViewById(R.id.gameTitle)
        gameImage = inflatedView.findViewById(R.id.gameImage)
        completedImage = inflatedView.findViewById(R.id.completedImage)
        gameItemContainer = inflatedView.findViewById(R.id.gameItemContainer)
        gameTitle.text = game.name
        gameImage.setImageResource(game.icon)
        gameItemContainer.setOnClickListener { listener?.onGameItemClick(game) }
        if(!game.completed)
            completedImage.visibility = View.INVISIBLE

        return inflatedView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnGameItemClick) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnGameItemClick")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnGameItemClick {
        fun onGameItemClick(game: Game)
    }
}