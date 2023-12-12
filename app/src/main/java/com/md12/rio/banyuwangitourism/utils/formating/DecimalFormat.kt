package com.md12.rio.banyuwangitourism.utils.formating

import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

fun DecimalFormat(number: Number): String {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')

    val numValue = abs(number.toLong())
    val value = if (numValue > 0) floor(log10(numValue.toDouble())).toInt() else 0
    val base = value / 3

    return when {
        value >= 3 && base < suffix.size -> {
            DecimalFormat("#0.0").format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        }
        else -> DecimalFormat("#,##0").format(numValue)
    }
}