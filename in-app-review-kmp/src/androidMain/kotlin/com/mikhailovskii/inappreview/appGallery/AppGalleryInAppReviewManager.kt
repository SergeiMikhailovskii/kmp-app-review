package com.mikhailovskii.inappreview.appGallery

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.mikhailovskii.inappreview.InAppReviewDelegate
import com.mikhailovskii.inappreview.ReviewCode
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class AppGalleryInAppReviewManager(
    private val params: AppGalleryInAppReviewInitParams
) : InAppReviewDelegate {

    private val resultFlow = MutableSharedFlow<ReviewCode>()
    private var activityResult: ActivityResultLauncher<Intent>? = null

    private val reviewCodeMapper by lazy(::AppGalleryReviewCodeMapper)

    override fun init() {
        activityResult =
            params.activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val resultCode = result.resultCode
                GlobalScope.launch { resultFlow.emit(reviewCodeMapper(resultCode)) }
            }
    }

    override fun requestInAppReview(): Flow<ReviewCode> {
        val intent = Intent("com.huawei.appmarket.intent.action.guidecomment")
            .setPackage("com.huawei.appmarket")
        activityResult?.launch(intent)
        return resultFlow
    }

    override fun requestInMarketReview() = requestInAppReview()
}