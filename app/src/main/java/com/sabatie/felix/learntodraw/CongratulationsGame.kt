package com.sabatie.felix.learntodraw

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CongratulationsGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        CurrentGame.game!!.completed = true
        setContentView(R.layout.activity_congratulations_game)
    }

    override fun onBackPressed() {
        // Do nothing
    }

    fun onHomescreenClick(v: View) {
        val homeView = Intent(v.context, ChooseCategory::class.java)
        homeView.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeView)
    }
}
