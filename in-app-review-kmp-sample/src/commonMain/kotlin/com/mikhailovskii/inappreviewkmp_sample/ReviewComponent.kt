package com.mikhailovskii.inappreviewkmp_sample

import com.mikhailovskii.inappreview.getDefaultReviewManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class ReviewComponent {
    private val defaultReviewManager by lazy {
        getDefaultReviewManager(getDefaultParams())
    }

    fun init() {
        defaultReviewManager.init()
    }

    fun requestInAppReview() {
        GlobalScope.launch {
            defaultReviewManager.requestInAppReview().collect {
                println("Result code=$it")
            }
        }
    }

    fun requestInMarketReview() {
        GlobalScope.launch {
            defaultReviewManager.requestInMarketReview()
        }
    }
}