package com.mikhailovskii.inappreview

import com.mikhailovskii.inappreview.googlePlay.GooglePlayInAppReviewManager
import com.mikhailovskii.inappreview.params.DefaultInAppReviewInitParams

actual fun getDefaultReviewManager(params: DefaultInAppReviewInitParams): InAppReviewDelegate =
    GooglePlayInAppReviewManager(params)