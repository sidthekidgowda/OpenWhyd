package com.openwhyd.datasource

import com.openwhyd.service.HotTrackService
import com.openwhyd.util.JsonUtil
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
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
        `when`(
            hotTracksService.getHotTracks(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
            )
        ).thenReturn(Single.just(JsonUtil.firstNetworkCall()))

        hotTracksDataSource.getHotTracks("rock")
            .test()
            .assertValue(JsonUtil.firstNetworkCall())
            .dispose()
    }

    @Test
    fun `test getHotTracks returns network error`() {
        `when`(
            hotTracksService.getHotTracks(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
            )
        ).thenReturn(Single.error(Throwable("error")))

        hotTracksDataSource.getHotTracks("hiphop")
            .test()
            .assertFailure(Throwable::class.java)
            .dispose()
    }

    @Test
    fun `test getHotTracks uses cache for subsequent run`() {

    }

    @Test
    fun `test Cache exist for different genres`() {

    }

    @Test
    fun `test getHotTracks when load more button is clicked makes second network call`() {

    }

    @Test
    fun `test getTrackDetails returns from cache`() {

    }
}