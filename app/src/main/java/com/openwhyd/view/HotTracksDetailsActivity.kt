package com.openwhyd.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.openwhyd.R
import com.openwhyd.application.OpenWhydApplication
import com.openwhyd.di.subcomponent.ActivityComponent

class HotTracksDetailsActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {

    companion object  {
        const val EXTRA_URL = "extra_url"
    }

    internal lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        //perform injection
        component = (application as OpenWhydApplication).component.activityComponent().create()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.hot_tracks_details_container)
        setTitle(getString(R.string.hot_track_details))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get intent whether to hide youtube fragment or not
        setupYoutubePlayer()
    }

    private fun setupYoutubePlayer() {
        val youtubeFragment = supportFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragment
        if (youtubeFragment == null) return

        var apiKey: String?

        packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA).apply {
            apiKey = metaData.getString("com.google.android.youtube.API_KEY")
        }

        if (apiKey == null) return
        youtubeFragment.initialize(apiKey, this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        player?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
        //set up video - pass video
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.e("TAG", "Failed")
    }


}