package com.openwhyd.handler

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.openwhyd.view.HotTracksActivity
import com.openwhyd.view.HotTracksDetailsFragment

class HotTrackHandlerImpl : HotTrackHandler {

    override fun onGenreClicked(view: View, genre: String) {
        if (view.context !is AppCompatActivity) return

        val intent = Intent(view.context as AppCompatActivity, HotTracksActivity::class.java)
        intent.putExtra(HotTracksActivity.EXTRA_GENRE, genre)
        (view.context as AppCompatActivity).startActivity(intent)

    }

    override fun onTrackClicked(view: View,
                                fragmentId:Int,
                                genre: String,
                                position: Int) {

        if (view.context !is AppCompatActivity) return
        val activity = view.context as AppCompatActivity
        val currentFragment = activity.supportFragmentManager.findFragmentById(fragmentId)

        if (currentFragment !is Fragment) return

        activity.supportFragmentManager.beginTransaction()
            .replace(currentFragment.id, HotTracksDetailsFragment.createInstance(genre, position))
            .addToBackStack(null)
            .commit()

    }
}