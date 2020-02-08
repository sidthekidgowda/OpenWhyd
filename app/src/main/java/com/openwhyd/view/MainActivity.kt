package com.openwhyd.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.openwhyd.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hot_tracks_button.setOnClickListener {
            val hotTracksIntent = Intent(this, HotTracksActivity::class.java)
            startActivity(hotTracksIntent)
        }
    }
}
