package com.openwhyd.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openwhyd.R

const val INTENT_EXTRA_GENRE = "intent_extra_genre"

class HotTracksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hot_tracks_category_container)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.hot_tracks_fragment_container, HotTracksGenreListFragment())
                .commit()
        }
    }

    override fun onBackPressed() {
        //handle navigation
        super.onBackPressed()
    }
}