package com.mikhailovskii.inappreview

enum class ReviewCode(val code: Int) {
    NO_ERROR(0),
    STORE_NOT_FOUND(-1),
    INVALID_REQUEST(-2),
    INTERNAL_ERROR(-100);

    companion object {
        fun fromCode(code: Int) = entries.first { it.code == code }
    }
}
