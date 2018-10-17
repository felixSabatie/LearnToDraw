package com.sabatie.felix.learntodraw

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.sabatie.felix.learntodraw.fragments.GameItem
import com.sabatie.felix.learntodraw.game.Game

class ChooseGame : AppCompatActivity(), GameItem.OnGameItemClick {
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
            it.resetGame()
            val fragment = GameItem()
            fragment.game = it
            fragmentTransaction.add(gameItemsContainer.id, fragment, it.name)
        }
        fragmentTransaction.commit()
    }

    fun onDeleteClick(v: View) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Est-tu sûr(e) de vouloir effacer ta progression ?")
                .setTitle("Effacer la progression")

        builder.setPositiveButton("Oui") { _, _ -> resetProgression() }
        builder.setNegativeButton("Annuler") { _, _ -> resetProgression() }
        builder.show()
    }

    private fun resetProgression() {
        val preferences = this.getSharedPreferences(getString(R.string.preferences_file), Context.MODE_PRIVATE) ?: return
        CurrentGame.gamesList.forEach {
            it.completed = false
            preferences.edit().putBoolean("${it.name}_completed", false).apply()
        }
        finish()
        startActivity(Intent(this, ChooseGame::class.java))
        overridePendingTransition(0, 0)
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
