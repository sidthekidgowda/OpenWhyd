package com.openwhyd.handler

import android.view.View

interface HotTrackHandler {
    fun onGenreClicked(view: View,
                       genre: String)

    fun onTrackClicked(view: View,
                       youtubePath: String,
                       genre: String,
                       activityTitle: String,
                       position: Int)
}