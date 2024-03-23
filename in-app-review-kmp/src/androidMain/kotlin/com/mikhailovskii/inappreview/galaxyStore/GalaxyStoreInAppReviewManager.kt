package com.mikhailovskii.inappreview.galaxyStore

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.ReviewCode
import com.mikhailovskii.inappreview.extensions.openMarket
import kotlinx.coroutines.flow.flow

class GalaxyStoreInAppReviewManager(
    private val params: GalaxyStoreInAppReviewInitParams
) : InAppReviewDelegate {

    private var deepLinkUri: String? = null
    private var errorCode: Int = 0

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun init() {
        super.init()
        val context = params.context
        val filter = IntentFilter("com.sec.android.app.samsungapps.RESPONSE_INAPP_REVIEW_AUTHORITY")
        val authorityReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val hasAuthority = intent.getBooleanExtra("hasAuthority", false)
                errorCode = intent.getIntExtra("errorCode", 0)
                if (hasAuthority) {
                    deepLinkUri = intent.getStringExtra("deeplinkUri")
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(authorityReceiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            context.registerReceiver(authorityReceiver, filter)
        }
        val intent = Intent("com.sec.android.app.samsungapps.REQUEST_INAPP_REVIEW_AUTHORITY").apply {
            `package` = "com.sec.android.app.samsungapps"
            putExtra("callerPackage", context.packageName)
        }
        context.sendBroadcast(intent)
    }

    override fun requestInAppReview() = flow {
        if (errorCode != 0) {
            emit(ReviewCode.fromCode(errorCode))
        } else {
            deepLinkUri?.let {
                val intent = Intent().apply {
                    data = Uri.parse(it)
                    flags += Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_INCLUDE_STOPPED_PACKAGES
                }
                params.context.startActivity(intent)
                emit(ReviewCode.NO_ERROR)
            } ?: run {
                emit(ReviewCode.INTERNAL_ERROR)
            }
        }
    }

    override fun requestInMarketReview() = flow {
        val context = params.context
        val packageName = context.packageName
        context.openMarket(
            deeplink = "samsungapps://AppRating/$packageName",
            url = "https://apps.samsung.com/appquery/AppRating.as?appId=$packageName"
        )
        emit(ReviewCode.NO_ERROR)
    }
}