package com.mikhailovskii.inappreviewkmp_sample

import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.appStore.AppStoreInAppReviewInitParams
import com.mikhailovskii.inappreview.appStore.AppStoreInAppReviewManager

actual fun getReviewManager(): InAppReviewDelegate = AppStoreInAppReviewManager(AppStoreInAppReviewInitParams("12345"))