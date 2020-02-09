package com.openwhyd.handler

import android.view.View

interface HotTrackHandler {
    fun onGenreClicked(view: View, genre: String)
    fun onTrackClicked(view: View, fragmentId: Int, genre: String, position: Int)
}