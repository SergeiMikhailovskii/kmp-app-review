package com.mikhailovskii.inappreview

interface InAppReviewManager {
    suspend fun requestReview()
}

expect class InAppReviewManagerImpl(params: InAppReviewInitParams) : InAppReviewManager