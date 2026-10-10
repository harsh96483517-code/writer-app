package com.writer.app.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    private val fileFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private val displayFormat = SimpleDateFormat("dd MMM yyyy", Locale("hi", "IN"))
    private val dottedFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    fun fileStamp(time: Long = System.currentTimeMillis()) = fileFormat.format(Date(time))
    fun display(time: Long) = displayFormat.format(Date(time))
    fun dotted(time: Long = System.currentTimeMillis()) = dottedFormat.format(Date(time))

    /** Safe filename: strips characters Android/SAF may reject. */
    fun sanitizeFileName(name: String): String {
        val cleaned = name.trim().replace(Regex("[\\\\/:*?\"<>|]"), "_")
        return if (cleaned.isBlank()) "Application" else cleaned.take(60)
    }
}
