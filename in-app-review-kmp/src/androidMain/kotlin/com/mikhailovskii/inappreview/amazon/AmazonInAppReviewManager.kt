package com.mikhailovskii.inappreview.amazon

import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.ReviewCode
import com.mikhailovskii.inappreview.extensions.openMarket
import kotlinx.coroutines.flow.flow

class AmazonInAppReviewManager(
    private val params: AmazonInAppReviewInitParams
) : InAppReviewDelegate {

    override fun requestInAppReview() = flow {
        val context = params.context
        context.openMarket(
            deeplink = "amzn://apps/android?p=${context.packageName}",
            url = "https://www.amazon.com/gp/mas/dl/android?p=${context.packageName}"
        )
        emit(ReviewCode.NO_ERROR)
    }

    override fun requestInMarketReview() = requestInAppReview()
}