package com.example.soop2025.data.local.language

import android.content.Context
import com.example.soop2025.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class LocalLanguageDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) : LanguageDataSource {

    private val cachedColors: Map<String, String> by lazy {
        val inputStream = context.resources.openRawResource(R.raw.language_colors)
        val json = inputStream.bufferedReader().use { it.readText() }
        Json.decodeFromString(json)
    }

    override fun loadLanguageColors(): Map<String, String> = cachedColors
}
