package com.mikhailovskii.inappreview.rustore

import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.ReviewCode
import com.mikhailovskii.inappreview.extensions.openMarket
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.rustore.sdk.core.exception.RuStoreException
import ru.rustore.sdk.review.RuStoreReviewManagerFactory

class RuStoreInAppReviewManager(private val params: RuStoreInAppReviewInitParams) : InAppReviewDelegate {

    override fun requestInAppReview() = flow {
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
        val context = params.context
        val packageName = context.packageName

        context.openMarket(
            deeplink = "rustore://apps.rustore.ru/app/$packageName",
            url = "https://apps.rustore.ru/app/$packageName"
        )
        emit(ReviewCode.NO_ERROR)
    }
}