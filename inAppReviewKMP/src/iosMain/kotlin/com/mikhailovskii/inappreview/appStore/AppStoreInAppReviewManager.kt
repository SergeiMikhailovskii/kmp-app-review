package com.mikhailovskii.inappreview.appStore

import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.systemVersionMoreOrEqualThan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import platform.Foundation.NSURL
import platform.StoreKit.SKStoreReviewController
import platform.UIKit.UIApplication
import platform.UIKit.UISceneActivationStateForegroundActive
import platform.UIKit.UIWindowScene

class AppStoreInAppReviewManager(private val params: AppStoreInAppReviewInitParams) : InAppReviewDelegate {

    override suspend fun requestInAppReview(): Flow<Int> = flow {
        if (systemVersionMoreOrEqualThan("14.0")) {
            val scene = UIApplication.sharedApplication.connectedScenes.map { it as UIWindowScene }
                .first { it.activationState == UISceneActivationStateForegroundActive }
            SKStoreReviewController.requestReviewInScene(scene)
        } else {
            SKStoreReviewController.requestReview()
        }
        emit(0)
    }

    override suspend fun requestInMarketReview() {
        val url = NSURL(string = "https://apps.apple.com/app/${params}?action=write-review")
        UIApplication.sharedApplication.openURL(url)
    }
}