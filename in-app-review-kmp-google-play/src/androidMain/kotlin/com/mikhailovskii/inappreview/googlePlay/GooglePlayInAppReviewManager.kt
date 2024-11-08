package com.mikhailovskii.inappreview.googlePlay

import com.google.android.play.core.review.ReviewException
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.ReviewCode
import com.mikhailovskii.inappreview.extensions.openMarket
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

@Suppress("FunctionName")
fun GooglePlayInAppReviewManager(params: GooglePlayInAppReviewInitParams):
        InAppReviewDelegate = GooglePlayInAppReviewManagerImpl(params, ReviewManagerFactory.create(params.activity))

internal class GooglePlayInAppReviewManagerImpl(
    private val params: GooglePlayInAppReviewInitParams,
    private val manager: ReviewManager
) : InAppReviewDelegate {

    override fun requestInAppReview() = flow {
        val activity = params.activity
        val reviewInfo = manager.requestReviewFlow().await()
        manager.launchReviewFlow(activity, reviewInfo).await()
        emit(ReviewCode.NO_ERROR)
    }.catch { e ->
        if (e is ReviewException) {
            emit(ReviewCode.fromCode(e.errorCode))
        } else {
            throw e
        }
    }

    override fun requestInMarketReview() = flow {
        val activity = params.activity
        val packageName = activity.packageName

        params.activity.openMarket(
            deeplink = "market://details?id=$packageName",
            url = "http://play.google.com/store/apps/details?id=$packageName"
        )
        emit(ReviewCode.NO_ERROR)
    }
}
