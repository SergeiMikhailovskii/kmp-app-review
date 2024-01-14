package com.mikhailovskii.inappreview.appGallery

import com.mikhailovskii.inappreview.ReviewCode

internal class AppGalleryReviewCodeMapper {
    operator fun invoke(code: Int) = if (code == 0) ReviewCode.INTERNAL_ERROR else ReviewCode.fromCode(code)
}