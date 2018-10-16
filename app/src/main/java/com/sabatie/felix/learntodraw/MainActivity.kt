package com.sabatie.felix.learntodraw

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {
    // send to bob.menelas@gmail.com

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }

    fun startGame(v: View) {
        CurrentGame.initGame()
        val questionView = Intent(v.context, QuestionActivity::class.java)
        questionView.putExtra("question", CurrentGame.game.currentQuestion())
        startActivity(questionView)
    }
}
