package com.openwhyd.viewModel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.openwhyd.datasource.HotTracksDataSource
import com.openwhyd.model.HotTrackRes
import com.openwhyd.util.JsonUtil
import com.openwhyd.util.RxSchedulerRule
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HotTracksViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val rxRule = RxSchedulerRule()

    @Mock
    private lateinit var hotTracksDataSource: HotTracksDataSource
    private lateinit var hotTrackViewModel: HotTracksViewModelImpl

    @Before
    fun setup() {
        hotTrackViewModel = HotTracksViewModelImpl(hotTracksDataSource)
    }

    @Test
    fun `test getHotTracks returns valid response`() {

        `when`(hotTracksDataSource.getHotTracks(anyString(), anyInt()))
            .thenReturn(Single.just(JsonUtil.firstNetworkCall()))

        hotTrackViewModel.getHotTracks("pop")
        assertEquals(JsonUtil.firstNetworkCall(), hotTrackViewModel.getHotTracksLiveData().value)
    }

    @Test
    fun `test getHotTracks returns an error`() {
        `when`(hotTracksDataSource.getHotTracks(anyString(), anyInt()))
            .thenReturn(Single.error(Throwable("error")))

        hotTrackViewModel.getHotTracks("rock")
        assertEquals(View.VISIBLE, hotTrackViewModel.errorImgVisibility().value)
    }

    @Test
    fun `test getMoreHotTracks returns valid response`() {
        val firstCall = JsonUtil.firstNetworkCall()
        val secondCall = JsonUtil.secondNetworkCall()
        val concatList = firstCall.tracks + secondCall.tracks
        val hotTrackRes = HotTrackRes(secondCall.hasMore, secondCall.genre, concatList)

        `when`(hotTracksDataSource.getHotTracks(anyString(), anyInt()))
            .thenReturn(Single.just(hotTrackRes))

        hotTrackViewModel.getMoreHotTracks("hiphop", 6)
        assertEquals(hotTrackRes, hotTrackViewModel.getHotTracksLiveData().value)
    }

    @Test
    fun `test getMoreHotTracks returns error`() {
        `when`(hotTracksDataSource.getHotTracks(anyString(), anyInt()))
            .thenReturn(Single.error(Throwable("error")))

        hotTrackViewModel.getMoreHotTracks("pop", 6)
        assertTrue(hotTrackViewModel.getMoreHotDetailsErrorLiveData().value!!)
    }

    @Test
    fun `test getHotTrackDetails returns valid response`() {
        val firstCall = JsonUtil.firstNetworkCall()
        val trackDetails = firstCall.genre to firstCall.tracks.get(2)

        `when`(hotTracksDataSource.getTrackDetails(anyString(), anyInt()))
            .thenReturn(Single.just(trackDetails))

        hotTrackViewModel.getDetailsForHotTrack("jazz", 2)
        assertEquals(trackDetails, hotTrackViewModel.getHotTrackDetailsLiveData().value)
    }

    @Test
    fun `test getHotTrackDetails returns error`() {
        `when`(hotTracksDataSource.getTrackDetails(anyString(), anyInt()))
            .thenReturn(Single.error(Throwable("error")))

        hotTrackViewModel.getDetailsForHotTrack("metal", 1)
        assertEquals(View.VISIBLE, hotTrackViewModel.errorImgVisibility().value)
    }
}