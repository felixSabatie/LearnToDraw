package com.sabatie.felix.learntodraw

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ChooseCategory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_choose_category)
    }

    override fun onBackPressed() {
        // Do nothing
    }
}
