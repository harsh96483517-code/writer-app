package com.writer.app.common

/** A4 dimensions and defaults (points = 1/72 inch). */
object DocSpec {
    const val A4_WIDTH_PT = 595.28f
    const val A4_HEIGHT_PT = 841.89f
    const val MM_TO_PT = 2.8346f

    fun mmToPt(mm: Int): Float = mm * MM_TO_PT
}
