package com.mikhailovskii.inappreview

import com.mikhailovskii.inappreview.params.DefaultInAppReviewInitParams
import kotlinx.coroutines.flow.Flow

interface InAppReviewDelegate {
    fun init() {}
    fun requestInAppReview(): Flow<ReviewCode>
    fun requestInMarketReview(): Flow<ReviewCode>
}

expect fun getDefaultReviewManager(params: DefaultInAppReviewInitParams): InAppReviewDelegate