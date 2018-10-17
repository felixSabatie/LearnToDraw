package com.sabatie.felix.learntodraw

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sabatie.felix.learntodraw.game.Game

class CongratulationsGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        (CurrentGame.gamesList.find { it.name == CurrentGame.game!!.name } as Game).completed = true
        setContentView(R.layout.activity_congratulations_game)

        saveProgress()
    }

    private fun saveProgress() {
        val preferences = this.getSharedPreferences(getString(R.string.preferences_file), Context.MODE_PRIVATE) ?: return
        preferences.edit().putBoolean("${CurrentGame.game!!.name}_completed", true).apply()
    }

    override fun onBackPressed() {
        // Do nothing
    }

    fun onHomescreenClick(v: View) {
        val homeView = Intent(v.context, ChooseGame::class.java)
        homeView.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeView)
    }
}
