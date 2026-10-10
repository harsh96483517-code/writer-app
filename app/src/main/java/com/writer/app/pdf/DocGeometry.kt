package com.writer.app.pdf

import com.writer.app.common.DocSpec
import com.writer.app.model.Document

/** Page geometry derived from document formatting. */
object DocGeometry {
    val pageWidth = DocSpec.A4_WIDTH_PT
    val pageHeight = DocSpec.A4_HEIGHT_PT

    fun left(d: Document) = DocSpec.mmToPt(d.formatting.marginLeftMm)
    fun right(d: Document) = DocSpec.mmToPt(d.formatting.marginRightMm)
    fun top(d: Document) = DocSpec.mmToPt(d.formatting.marginTopMm)
    fun bottom(d: Document) = DocSpec.mmToPt(d.formatting.marginBottomMm)

    fun contentWidth(d: Document) = pageWidth - left(d) - right(d)
    fun contentHeight(d: Document) = pageHeight - top(d) - bottom(d)
}
