package com.openwhyd.datasource

import com.openwhyd.service.HotTrackService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HotTracksDataSourceTest {

    /*
      fun getHotTracks(genre: String, skip: Int=0): Single<HotTrackRes>
    fun getTrackDetails(genre: String, position: Int): Single<Pair<String, HotTrack>>
     */

    @Mock
    private lateinit var hotTracksService: HotTrackService
    private lateinit var hotTracksDataSource: HotTracksDataSource


    @Before
    fun setup() {
        hotTracksDataSource = HotTracksDataSourceImpl(hotTracksService)
    }

    @Test
    fun `test getHotTracks service call is made for first run`() {
        
    }

    @Test
    fun `test getHotTracks uses cache for second run`() {

    }

    @Test
    fun `cache exist`() {

    }

}