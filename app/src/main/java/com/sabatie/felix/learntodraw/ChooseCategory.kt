package com.sabatie.felix.learntodraw

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.sabatie.felix.learntodraw.fragments.GameItem
import com.sabatie.felix.learntodraw.game.Game

class ChooseCategory : AppCompatActivity(), GameItem.OnGameItemClick {
    lateinit var gameItemsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_choose_category)

        gameItemsContainer = findViewById(R.id.gameItemsContainer)
        addGameItems()
    }

    private fun addGameItems() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        CurrentGame.gamesList.forEach {
            val fragment = GameItem()
            fragment.game = it
            fragmentTransaction.add(gameItemsContainer.id, fragment, it.name)
        }
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        // Do nothing
    }

    override fun onGameItemClick(game: Game) {
        CurrentGame.game = game
        val questionView = Intent(findViewById<View>(android.R.id.content).context, QuestionActivity::class.java)
        questionView.putExtra("question", game.currentQuestion())
        startActivity(questionView)
    }
}
