package com.openwhyd.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.openwhyd.R
import com.openwhyd.application.OpenWhydApplication
import com.openwhyd.di.subcomponent.ActivityComponent
import org.apache.commons.lang3.StringUtils


class HotTracksActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_GENRE = "extra_genre"
    }

    lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        component = (application as OpenWhydApplication).component.activityComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.hot_tracks_container)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title:String = intent.extras?.getString(EXTRA_GENRE) ?: StringUtils.EMPTY

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.hot_tracks_fragment_container, HotTracksFragment.createInstance(title))
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}