package com.mikhailovskii.inappreview

import com.mikhailovskii.inappreview.params.DefaultInAppReviewInitParams
import kotlinx.coroutines.flow.Flow

interface InAppReviewDelegate {
    suspend fun requestInAppReview(): Flow<ReviewCode>
    suspend fun requestInMarketReview()
}

expect fun getDefaultReviewManager(params: DefaultInAppReviewInitParams): InAppReviewDelegate