package com.mikhailovskii.inappreview

import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.tasks.await

actual class InAppReviewManagerImpl actual constructor(private val params: InAppReviewInitParams) : InAppReviewManager {

    override suspend fun requestReview() {
        val manager = ReviewManagerFactory.create(params.activity)
        val reviewInfo = manager.requestReviewFlow().await()
        manager.launchReviewFlow(params.activity, reviewInfo).await()
    }
}