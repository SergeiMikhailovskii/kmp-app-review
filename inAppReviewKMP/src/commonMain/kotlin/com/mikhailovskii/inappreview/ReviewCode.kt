package com.mikhailovskii.inappreview

enum class ReviewCode(val code: Int) {
    NO_ERROR(0),
    STORE_NOT_FOUND(-1),
    INVALID_REQUEST(-2),
    INTERNAL_ERROR(-100),
    APP_NOT_RELEASED(101),
    RATING_SUBMITTED(102),
    COMMENT_SUBMITTED(103),
    SIGN_IN_STATUS_INVALID(104),
    CONDITIONS_NOT_MET(105),
    COMMENTING_DISABLED(106),
    COMMENTING_NOT_SUPPORTED(107),
    CANCEL(108);

    companion object {
        fun fromCode(code: Int) = entries.first { it.code == code }
    }
}
