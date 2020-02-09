package com.openwhyd.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.openwhyd.datasource.HotTracksDataSource
import com.openwhyd.util.JsonUtil
import com.openwhyd.util.RxImmediateSchedulerRule
import io.reactivex.Single
import junit.framework.Assert.assertEquals
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
    val rxRule = RxImmediateSchedulerRule()
    @Mock
    private lateinit var hotTracksDataSource: HotTracksDataSource
    private lateinit var hotTrackViewModel: HotTracksViewModelImpl

    @Before
    fun setup() {
        hotTrackViewModel = HotTracksViewModelImpl(hotTracksDataSource)
    }
    /*
      fun getHotTracks(genre: String)
    fun getHotTracksLiveData(): LiveData<HotTrackRes>
    fun getMoreHotTracks(genre: String, position: Int)
    fun getDetailsForHotTrack(genre: String, position: Int)
    fun getHotTrackDetailsLiveData(): LiveData<Pair<String, HotTrack>>
     */

    @Test
    fun `test getHotTracks returns valid response`() {

        `when`(hotTracksDataSource.getHotTracks(anyString(), anyInt()))
            .thenReturn(Single.just(JsonUtil.firstNetworkCall()))

        hotTrackViewModel.getHotTracks("pop")
        assertEquals(JsonUtil.firstNetworkCall(), hotTrackViewModel.getHotTracksLiveData().value)
    }

    @Test
    fun `test getHotTracks returns an error`() {

    }
}