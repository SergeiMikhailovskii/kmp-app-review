package com.mikhailovskii.inappreview

import platform.UIKit.UIDevice
import kotlin.math.pow

internal fun systemVersionMoreOrEqualThan(version: String): Boolean {
    val systemVersion = UIDevice.currentDevice.systemVersion
    return version.versionToNumber() >= systemVersion.versionToNumber()
}

private fun String.versionToNumber() = split(".")
    .map { it.toInt() }
    .take(3)
    .run {
        val appendedList = toMutableList()
        repeat(3 - size) { appendedList.add(0) }
        println(appendedList)
        appendedList.foldIndexed(0) { index, acc, current ->
            acc + current * 1000.0.pow(2 - index).toInt()
        }
    }