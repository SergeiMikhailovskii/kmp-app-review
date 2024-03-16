package com.mikhailovskii.inappreviewkmp_sample

import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.rustore.RuStoreInAppReviewInitParams
import com.mikhailovskii.inappreview.rustore.RuStoreInAppReviewManager

actual fun getReviewManager(): InAppReviewDelegate = RuStoreInAppReviewManager(
    RuStoreInAppReviewInitParams(requireNotNull(AndroidServiceLocator.activity))
)