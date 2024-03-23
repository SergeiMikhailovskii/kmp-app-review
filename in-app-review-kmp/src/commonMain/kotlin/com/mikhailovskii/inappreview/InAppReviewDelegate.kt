package com.mikhailovskii.inappreview

import kotlinx.coroutines.flow.Flow

interface InAppReviewDelegate {
    fun init() {}
    fun requestInAppReview(): Flow<ReviewCode>
    fun requestInMarketReview(): Flow<ReviewCode>
}
