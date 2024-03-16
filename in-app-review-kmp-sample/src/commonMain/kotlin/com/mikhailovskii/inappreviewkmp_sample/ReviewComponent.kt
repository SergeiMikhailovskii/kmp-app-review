package com.mikhailovskii.inappreviewkmp_sample

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class ReviewComponent {
    private val defaultReviewManager by lazy {
        getReviewManager()
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
        try {
            defaultReviewManager.requestInMarketReview().catch { it.printStackTrace() }
        } catch (it: Exception) {
            it.printStackTrace()
        }
    }
}