package com.mikhailovskii.inappreview.appStore

import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.systemVersionMoreOrEqualThan
import platform.StoreKit.SKStoreReviewController
import platform.UIKit.UIApplication
import platform.UIKit.UISceneActivationStateForegroundActive
import platform.UIKit.UIWindowScene

class AppStoreInAppReviewManager : InAppReviewDelegate {
    override suspend fun requestReview() {
        if (systemVersionMoreOrEqualThan("14.0")) {
            val scene = UIApplication.sharedApplication.connectedScenes.map { it as UIWindowScene }
                .first { it.activationState == UISceneActivationStateForegroundActive }
            SKStoreReviewController.requestReviewInScene(scene)
        } else {
            SKStoreReviewController.requestReview()
        }
    }
}