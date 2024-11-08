package com.mikhailovskii.inappreview.googlePlay

import android.app.Activity
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.play.core.review.testing.FakeReviewManager
import com.mikhailovskii.inappreview.ReviewCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.runner.RunWith
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class GooglePlayInAppReviewManagerTests {
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setupTestDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun requestInAppReview() = runTest {
        withTestActivity {
            val manager = GooglePlayInAppReviewManagerImpl(
                params = GooglePlayInAppReviewInitParams(it),
                manager = FakeReviewManager(it)
            )

            val events = mutableListOf<ReviewCode>()
            launch {
                manager.requestInAppReview().toList(events)
                assertEquals(listOf(ReviewCode.NO_ERROR), events)
            }

            advanceUntilIdle()
        }
    }

    @Test
    fun requestInMarketReview() = runTest {
        withTestActivity {
            val manager = GooglePlayInAppReviewManagerImpl(
                params = GooglePlayInAppReviewInitParams(it),
                manager = FakeReviewManager(it)
            )

            val events = mutableListOf<ReviewCode>()
            launch {
                manager.requestInMarketReview().toList(events)
                assertEquals(listOf(ReviewCode.NO_ERROR), events)
            }
        }
    }

    private fun withTestActivity(block: (Activity) -> Unit) {
        launchActivity<TestActivity>().use {
            it.onActivity(block)
        }
    }
}