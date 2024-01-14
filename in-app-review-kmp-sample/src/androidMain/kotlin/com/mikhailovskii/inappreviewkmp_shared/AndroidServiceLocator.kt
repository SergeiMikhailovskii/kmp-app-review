package com.mikhailovskii.inappreviewkmp_shared

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity

@SuppressLint("StaticFieldLeak")
object AndroidServiceLocator {
    var activity: ComponentActivity? = null
}