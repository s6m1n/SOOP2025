package com.example.soop2025.presentation.ui

fun formatMetricSuffix(number: Int): String {
    return when {
        number >= 1_000_000_000 -> "%.1fB".format(number / 1_000_000_000.0)
        number >= 1_000_000 -> "%.1fM".format(number / 1_000_000.0)
        number >= 1_000 -> "%.1fk".format(number / 1_000.0)
        else -> number.toString()
    }
}
