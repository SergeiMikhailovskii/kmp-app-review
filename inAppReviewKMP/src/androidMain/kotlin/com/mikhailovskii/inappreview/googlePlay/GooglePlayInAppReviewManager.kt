package com.mikhailovskii.inappreview.googlePlay

import android.content.Intent
import android.net.Uri
import com.google.android.play.core.review.ReviewException
import com.google.android.play.core.review.ReviewManagerFactory
import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.ReviewCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class GooglePlayInAppReviewManager(private val params: GooglePlayInAppReviewInitParams) : InAppReviewDelegate {

    override fun init() {}

    override suspend fun requestInAppReview(): Flow<ReviewCode> = flow {
        val activity = params.activity
        val manager = ReviewManagerFactory.create(activity)
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

    override suspend fun requestInMarketReview() = flow {
        val activity = params.activity
        val packageName = activity.packageName

        val marketAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")).apply {
            flags += Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        }

        val marketInBrowserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
        )

        runCatching {
            activity.startActivity(marketAppIntent)
        }.getOrElse {
            activity.startActivity(marketInBrowserIntent)
        }
        emit(ReviewCode.NO_ERROR)
    }
}