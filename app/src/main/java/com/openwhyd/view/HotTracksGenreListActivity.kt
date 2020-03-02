package com.openwhyd.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.openwhyd.R
import com.openwhyd.application.OpenWhydApplication
import com.openwhyd.databinding.HotTracksGenreListActivityBinding
import com.openwhyd.di.subcomponent.ActivityComponent

class HotTracksGenreListActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (application as OpenWhydApplication).component.activityComponent().create()
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<HotTracksGenreListActivityBinding>(this, R.layout.hot_tracks_genre_list_activity)

        navController = findNavController(R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
