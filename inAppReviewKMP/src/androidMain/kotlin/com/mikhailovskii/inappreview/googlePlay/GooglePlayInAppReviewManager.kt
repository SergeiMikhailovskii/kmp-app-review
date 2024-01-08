package com.mikhailovskii.inappreview.googlePlay

import com.google.android.play.core.review.ReviewManagerFactory
import com.mikhailovskii.inappreview.InAppReviewDelegate
import kotlinx.coroutines.tasks.await

class GooglePlayInAppReviewManager(private val params: GooglePlayInAppReviewInitParams) : InAppReviewDelegate {

    override suspend fun requestReview() {
        val manager = ReviewManagerFactory.create(params.activity)
        val reviewInfo = manager.requestReviewFlow().await()
        manager.launchReviewFlow(params.activity, reviewInfo).await()
    }
}