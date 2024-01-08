package com.mikhailovskii.inappreview

import com.mikhailovskii.inappreview.params.DefaultInAppReviewInitParams

interface InAppReviewDelegate {
    suspend fun requestReview()
}

expect fun getDefaultReviewManager(params: DefaultInAppReviewInitParams): InAppReviewDelegate