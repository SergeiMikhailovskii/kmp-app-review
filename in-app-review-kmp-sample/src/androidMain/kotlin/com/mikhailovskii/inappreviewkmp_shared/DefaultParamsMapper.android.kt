package com.mikhailovskii.inappreviewkmp_shared

import com.mikhailovskii.inappreview.params.DefaultInAppReviewInitParams

actual fun getDefaultParams() = DefaultInAppReviewInitParams(requireNotNull(AndroidServiceLocator.activity))