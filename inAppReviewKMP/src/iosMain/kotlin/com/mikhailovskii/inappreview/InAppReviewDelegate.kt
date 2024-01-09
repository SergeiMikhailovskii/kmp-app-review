package com.mikhailovskii.inappreview

import com.mikhailovskii.inappreview.appStore.AppStoreInAppReviewManager
import com.mikhailovskii.inappreview.params.DefaultInAppReviewInitParams

actual fun getDefaultReviewManager(params: DefaultInAppReviewInitParams): InAppReviewDelegate =
    AppStoreInAppReviewManager(params)