package com.mikhailovskii.inappreview.rustore

import com.mikhailovskii.inappreview.ReviewCode
import ru.rustore.sdk.core.exception.RuStoreApplicationBannedException
import ru.rustore.sdk.core.exception.RuStoreException
import ru.rustore.sdk.core.exception.RuStoreNotInstalledException
import ru.rustore.sdk.core.exception.RuStoreOutdatedException
import ru.rustore.sdk.core.exception.RuStoreUserBannedException
import ru.rustore.sdk.review.errors.RuStoreInvalidReviewInfo
import ru.rustore.sdk.review.errors.RuStoreRequestLimitReached
import ru.rustore.sdk.review.errors.RuStoreReviewExists

internal class RuStoreExceptionMapper {
    operator fun invoke(throwable: RuStoreException) = when (throwable) {
        is RuStoreNotInstalledException -> ReviewCode.STORE_NOT_FOUND
        is RuStoreOutdatedException -> ReviewCode.STORE_OUTDATED
        is RuStoreUserBannedException -> ReviewCode.USER_BANNED
        is RuStoreApplicationBannedException -> ReviewCode.APP_BANNED
        is RuStoreRequestLimitReached -> ReviewCode.REQUEST_LIMIT_REACHED
        is RuStoreReviewExists -> ReviewCode.REVIEW_EXISTS
        is RuStoreInvalidReviewInfo -> ReviewCode.INVALID_REVIEW_INFO
        else -> throw throwable
    }
}