package com.mikhailovskii.inappreviewkmp_sample

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity

@SuppressLint("StaticFieldLeak")
object AndroidServiceLocator {
    var activity: ComponentActivity? = null
}