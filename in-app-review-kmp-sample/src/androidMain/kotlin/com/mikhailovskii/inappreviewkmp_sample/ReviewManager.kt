package com.mikhailovskii.inappreviewkmp_sample

import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.galaxyStore.GalaxyStoreInAppReviewInitParams
import com.mikhailovskii.inappreview.galaxyStore.GalaxyStoreInAppReviewManager

actual fun getReviewManager(): InAppReviewDelegate = GalaxyStoreInAppReviewManager(
    GalaxyStoreInAppReviewInitParams(requireNotNull(AndroidServiceLocator.activity))
)