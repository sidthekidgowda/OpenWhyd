package com.openwhyd.handler

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.openwhyd.view.HotTracksActivity
import com.openwhyd.view.INTENT_EXTRA_GENRE

class HotTrackGenreHandlerImpl : HotTrackGenreHandler {

    override fun onGenreClicked(view: View, genre: String) {
        if (view.context !is AppCompatActivity) return

        val intent = Intent(view.context as AppCompatActivity, HotTracksActivity::class.java)
        intent.putExtra(INTENT_EXTRA_GENRE, genre)
        (view.context as AppCompatActivity).startActivity(intent)

    }
}