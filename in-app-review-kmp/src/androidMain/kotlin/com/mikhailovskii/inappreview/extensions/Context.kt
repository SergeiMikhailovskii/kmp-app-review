package com.mikhailovskii.inappreview.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openMarket(deeplink: String, url: String) {

    val marketAppIntent = Intent(Intent.ACTION_VIEW, Uri.parse(deeplink)).apply {
        flags += Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK
    }

    val marketInBrowserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    runCatching {
        startActivity(marketAppIntent)
    }.getOrElse {
        startActivity(marketInBrowserIntent)
    }
}