package com.openwhyd.viewModel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.openwhyd.datasource.HotTracksDataSource
import com.openwhyd.util.JsonUtil
import com.openwhyd.util.RxSchedulerRule
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
    val rxRule = RxSchedulerRule()
    
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


    fun loadingSpinnerVisibility(): LiveData<Int>
    fun loadMoreContainerVisibility(): LiveData<Int>
    fun recyclerViewVisibility(): LiveData<Int>
    fun errorImgVisibility(): LiveData<Int>
    fun errorTextVisibility(): LiveData<Int>
    fun resetLoadContainer(): LiveData<Boolean>
    fun getMoreHotDetailsErrorLiveData(): LiveData<Boolean>
}
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
        `when`(hotTracksDataSource.getHotTracks(anyString(), anyInt()))
            .thenReturn(Single.error(Throwable("error")))

        hotTrackViewModel.getHotTracks("rock")
        assertEquals(View.VISIBLE, hotTrackViewModel.errorImgVisibility().value)
    }
}