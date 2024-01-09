package com.mikhailovskii.inappreviewkmp_shared

import com.mikhailovskii.inappreview.getDefaultReviewManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class ReviewComponent {
    private val defaultReviewManager by lazy {
        getDefaultReviewManager(getDefaultParams())
    }

    fun requestInAppReview() {
        GlobalScope.launch {
            defaultReviewManager.requestInAppReview()
        }
    }

    fun requestInMarketReview() {
        GlobalScope.launch {
            defaultReviewManager.requestInMarketReview()
        }
    }
}