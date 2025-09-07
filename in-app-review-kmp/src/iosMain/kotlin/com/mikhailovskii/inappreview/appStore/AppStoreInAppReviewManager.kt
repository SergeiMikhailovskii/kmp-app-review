package com.mikhailovskii.inappreview.appStore

import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.ReviewCode
import com.mikhailovskii.inappreview.systemVersionMoreOrEqualThan
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import platform.Foundation.NSURL.Companion.URLWithString
import platform.StoreKit.SKStoreReviewController
import platform.UIKit.UIApplication
import platform.UIKit.UISceneActivationStateForegroundActive
import platform.UIKit.UIWindowScene

class AppStoreInAppReviewManager(private val params: AppStoreInAppReviewInitParams) :
    InAppReviewDelegate {

    override fun requestInAppReview(): Flow<ReviewCode> = flow {
        if (systemVersionMoreOrEqualThan("14.0")) {
            val scene = UIApplication.sharedApplication.connectedScenes.map { it as UIWindowScene }
                .first { it.activationState == UISceneActivationStateForegroundActive }
            SKStoreReviewController.requestReviewInScene(scene)
        } else {
            SKStoreReviewController.requestReview()
        }
        emit(ReviewCode.NO_ERROR)
    }

    override fun requestInMarketReview() = callbackFlow {
        UIApplication.sharedApplication.openURL(
            url = URLWithString("https://apps.apple.com/app/${params.appStoreId}?action=write-review")!!,
            options = emptyMap<Any?, Any>(),
            completionHandler = { success ->
                if (success) {
                    trySendBlocking(ReviewCode.NO_ERROR)
                } else {
                    trySendBlocking(ReviewCode.INTERNAL_ERROR)
                }
            }
        )
    }
}