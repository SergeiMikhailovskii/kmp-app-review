package com.mikhailovskii.inappreviewkmp_sample

import com.mikhailovskii.inappreview.params.DefaultInAppReviewInitParams

actual fun getDefaultParams() = DefaultInAppReviewInitParams(requireNotNull(AndroidServiceLocator.activity))