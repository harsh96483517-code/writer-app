package com.writer.app.model

/** Formatting applied to the main body of a document. */
data class DocumentFormatting(
    val fontSizePt: Float = 12f,
    val lineSpacing: Float = 1.4f,
    val bold: Boolean = false,
    val italic: Boolean = false,
    val alignStart: Boolean = true,
    val marginLeftMm: Int = 25,
    val marginRightMm: Int = 20,
    val marginTopMm: Int = 20,
    val marginBottomMm: Int = 20
)
