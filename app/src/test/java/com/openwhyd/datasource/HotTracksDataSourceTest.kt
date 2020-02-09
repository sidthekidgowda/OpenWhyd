package com.openwhyd.datasource

import com.openwhyd.model.HotTrackRes
import com.openwhyd.service.HotTrackService
import com.openwhyd.util.JsonUtil
import com.openwhyd.util.RxSchedulerRule
import io.reactivex.Single
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HotTracksDataSourceTest {

    @get:Rule
    val rxRule = RxSchedulerRule()

    @Mock
    private lateinit var hotTracksService: HotTrackService
    private lateinit var hotTracksDataSource: HotTracksDataSourceImpl

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
    fun `test getHotTracks uses cache for the same genre for subsequent runs`() {
        `when`(
            hotTracksService.getHotTracks(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
            )
        ).thenReturn(Single.just(JsonUtil.firstNetworkCall()))
        hotTracksDataSource.getHotTracks("rock").blockingGet()

        assertTrue(hotTracksDataSource.hotTrackMapCache.containsKey("rock"))
        assertFalse(hotTracksDataSource.hotTrackMapCache.containsKey("pop"))

        assertEquals(JsonUtil.firstNetworkCall(), hotTracksDataSource.hotTrackMapCache["rock"])

    }

    @Test
    fun `test Cache exist for different genres`() {
        `when`(
            hotTracksService.getHotTracks(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
            )
        ).thenAnswer { invocation ->
            val genre = invocation.getArgument<String>(0)
            when(genre) {
                "rock" -> Single.just(JsonUtil.firstNetworkCall())
                "pop" -> Single.just(JsonUtil.diffGenreNetworkCall())
                else -> Single.error(Throwable("error"))
            }
        }

        hotTracksDataSource.getHotTracks("rock").blockingGet()
        hotTracksDataSource.getHotTracks("pop").blockingGet()

        assertTrue(hotTracksDataSource.hotTrackMapCache.containsKey("rock"))
        assertTrue(hotTracksDataSource.hotTrackMapCache.containsKey("pop"))

        assertEquals(JsonUtil.firstNetworkCall(), hotTracksDataSource.hotTrackMapCache["rock"])
        assertEquals(JsonUtil.diffGenreNetworkCall(), hotTracksDataSource.hotTrackMapCache["pop"])
    }

    @Test
    fun `test getHotTracks makes second network call when load more button is clicked`() {
        `when`(
            hotTracksService.getHotTracks(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
            )
        ).thenReturn(Single.just(JsonUtil.secondNetworkCall()))

        val firstCall = JsonUtil.firstNetworkCall()
        val secondCall = JsonUtil.secondNetworkCall()
        val concatList = firstCall.tracks + secondCall.tracks
        val expected = HotTrackRes(secondCall.hasMore, secondCall.genre, concatList)
        //put first value in map
        hotTracksDataSource.hotTrackMapCache.put("rock", firstCall)

        hotTracksDataSource.getHotTracks("rock", 6)
            .test()
            .assertValue(expected)
            .dispose()
    }

    @Test
    fun `test getTrackDetails returns from cache`() {
        val firstGenre = JsonUtil.firstNetworkCall()
        val secondGenre = JsonUtil.diffGenreNetworkCall()

        val secondPair = secondGenre.genre to secondGenre.tracks[2]

        hotTracksDataSource.hotTrackMapCache.put("rock", firstGenre)
        hotTracksDataSource.hotTrackMapCache.put("pop", secondGenre)

        hotTracksDataSource.getTrackDetails("pop", 2)
            .test()
            .assertValue(secondPair)
            .dispose()
    }

    @Test
    fun `test getTrackDetails returns Error from cache`() {

        hotTracksDataSource.hotTrackMapCache.put("rock", JsonUtil.firstNetworkCall())
        hotTracksDataSource.getTrackDetails("pop", 2)
            .test()
            .assertFailure(Throwable::class.java)
            .dispose()
    }
}