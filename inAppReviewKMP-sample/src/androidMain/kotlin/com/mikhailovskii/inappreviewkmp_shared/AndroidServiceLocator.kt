package com.mikhailovskii.inappreviewkmp_shared

import android.annotation.SuppressLint
import android.app.Activity

@SuppressLint("StaticFieldLeak")
object AndroidServiceLocator {
    var activity: Activity? = null
}