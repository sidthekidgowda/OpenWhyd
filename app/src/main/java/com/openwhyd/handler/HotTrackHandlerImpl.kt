package com.openwhyd.handler

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.openwhyd.view.HotTracksActivity
import com.openwhyd.view.HotTracksDetailsActivity

class HotTrackHandlerImpl : HotTrackHandler {

    override fun onGenreClicked(view: View, genre: String) {
        val activity = view.context as? AppCompatActivity

        activity?.apply {
            val intent = Intent(activity, HotTracksActivity::class.java).apply {
                putExtra(HotTracksActivity.EXTRA_GENRE, genre)
            }
            startActivity(intent)
        }
    }

    override fun onTrackClicked(view: View,
                                youtubePath:String,
                                genre: String,
                                activityTitle: String,
                                position: Int) {

        val activity = view.context as? AppCompatActivity

        activity?.apply {
            val intent = Intent(activity, HotTracksDetailsActivity::class.java).apply {
                putExtra(HotTracksDetailsActivity.EXTRA_URL, youtubePath)
                putExtra(HotTracksDetailsActivity.EXTRA_GENRE, genre)
                putExtra(HotTracksDetailsActivity.EXTRA_TITLE, activityTitle)
                putExtra(HotTracksDetailsActivity.EXTRA_SELCTED_POSITION, position)}
            startActivity(intent)
        }
    }
}