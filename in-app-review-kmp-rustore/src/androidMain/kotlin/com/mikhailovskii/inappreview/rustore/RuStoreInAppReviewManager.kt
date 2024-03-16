package com.mikhailovskii.inappreview.rustore

import android.content.Intent
import android.net.Uri
import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.ReviewCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.rustore.sdk.core.exception.RuStoreException
import ru.rustore.sdk.review.RuStoreReviewManagerFactory

class RuStoreInAppReviewManager(private val params: RuStoreInAppReviewInitParams) : InAppReviewDelegate {

    override fun requestInAppReview(): Flow<ReviewCode> = flow {
        val activity = params.context
        val manager = RuStoreReviewManagerFactory.create(activity)
        val reviewInfo = manager.requestReviewFlow().await()
        manager.launchReviewFlow(reviewInfo).await()
        emit(ReviewCode.NO_ERROR)
    }.catch { e ->
        if (e is RuStoreException) {
            val exceptionMapper = RuStoreExceptionMapper()
            emit(exceptionMapper(e))
        } else {
            throw e
        }
    }

    override fun requestInMarketReview() = flow {
        val activity = params.context
        val packageName = activity.packageName

        val marketAppIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("rustore://apps.rustore.ru/app/$packageName")).apply {
                flags += Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            }

        val marketInBrowserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://apps.rustore.ru/app/$packageName")
        )

        runCatching {
            activity.startActivity(marketAppIntent)
        }.getOrElse {
            activity.startActivity(marketInBrowserIntent)
        }
        emit(ReviewCode.NO_ERROR)
    }
}