package com.mikhailovskii.inappreview

import com.mikhailovskii.inappreview.params.DefaultInAppReviewInitParams

interface InAppReviewDelegate {
    suspend fun requestInAppReview()
    suspend fun requestInMarketReview()
}

expect fun getDefaultReviewManager(params: DefaultInAppReviewInitParams): InAppReviewDelegate