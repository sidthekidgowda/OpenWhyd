package com.openwhyd.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openwhyd.R

class HotTracksGenreListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hot_tracks_genre_container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.hot_tracks_genre_container, HotTracksGenreListFragment())
                .commit()
        }
    }
}
