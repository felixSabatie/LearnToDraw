package com.sabatie.felix.learntodraw

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sabatie.felix.learntodraw.game.GameFactory


class MainActivity : AppCompatActivity() {
    // send to bob.menelas@gmail.com

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }

    fun startGame(v: View) {
        CurrentGame.gamesList = GameFactory.createGames()
        val gameChooserView = Intent(v.context, ChooseCategory::class.java)
        startActivity(gameChooserView)
    }
}
