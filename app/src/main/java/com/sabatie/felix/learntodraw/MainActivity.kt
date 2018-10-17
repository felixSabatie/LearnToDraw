package com.sabatie.felix.learntodraw

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sabatie.felix.learntodraw.game.GameFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }

    fun startGame(v: View) {
        CurrentGame.gamesList = GameFactory.createGames()
        val preferences = this.getSharedPreferences(getString(R.string.preferences_file), Context.MODE_PRIVATE) ?: return
        CurrentGame.gamesList.forEach {
            it.completed = preferences.getBoolean("${it.name}_completed", false)
        }
        val gameChooserView = Intent(v.context, ChooseGame::class.java)
        startActivity(gameChooserView)
    }
}
