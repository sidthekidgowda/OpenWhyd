package com.openwhyd.view

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.openwhyd.R
import com.openwhyd.application.OpenWhydApplication
import com.openwhyd.di.subcomponent.ActivityComponent
import org.apache.commons.lang3.StringUtils

class HotTracksDetailsActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {

    companion object  {
        const val EXTRA_URL = "extra_url"
        const val EXTRA_GENRE = "extra_genre"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_SELCTED_POSITION = "extra_selected_position"
    }

    internal lateinit var component: ActivityComponent

    private var youtubePath: String? = null
    private val TAG = HotTracksDetailsActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        //perform injection
        component = (application as OpenWhydApplication).component.activityComponent().create()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.hot_tracks_details_container)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        youtubePath = intent.extras?.getString(EXTRA_URL)
        val genre = intent.extras?.getString(EXTRA_GENRE) ?: StringUtils.EMPTY
        val title = intent.extras?.getString(EXTRA_TITLE) ?: StringUtils.EMPTY
        val position = intent.extras?.getInt(EXTRA_SELCTED_POSITION) ?: 0

        setTitle(title)

        //get intent whether to hide youtube fragment or not
        val youtubeFragment = supportFragmentManager.findFragmentById(R.id.youtube_fragment)
                as YouTubePlayerSupportFragment

        if (youtubePath.isNullOrEmpty() && youtubeFragment is Fragment) {
            supportFragmentManager.beginTransaction().hide(youtubeFragment).commit()
        } else {
            setupYoutubePlayer(youtubeFragment)
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.hot_tracks_details_fragment, HotTracksDetailsFragment.createInstance(genre, position))
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }

    private fun setupYoutubePlayer(youtubeFragment: YouTubePlayerSupportFragment) {
       packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA).run {
            metaData.getString("com.google.android.youtube.API_KEY")
        }.also {
            youtubeFragment.initialize(it, this)
        }
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        player?.apply {
            setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
            Log.d(TAG, "Youtube initialization succeeded")
            //setupYoutubePlayer will be called only if youtubePath is not null
            if (!wasRestored) cueVideo(youtubePath)
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.e(TAG, "Youtube initialization failed")
    }


}