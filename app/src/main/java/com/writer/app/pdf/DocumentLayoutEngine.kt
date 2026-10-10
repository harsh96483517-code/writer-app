package com.writer.app.pdf

import android.text.Layout
import android.text.SpannableStringBuilder
import android.text.StaticLayout
import android.text.TextPaint
import android.text.style.StyleSpan
import android.graphics.Typeface
import com.writer.app.common.DateUtils
import com.writer.app.model.Document
import com.writer.app.model.Language

/**
 * Turns a [Document] into a laid-out, paginated text flow using Android's own
 * text engine ([StaticLayout]). This keeps Hindi conjuncts and vowel marks
 * correct and produces selectable, searchable text — no screenshots.
 */
class DocumentLayoutEngine {

    data class LaidOutDocument(
        val layout: StaticLayout,
        val pageCount: Int,
        val contentWidth: Float
    )

    fun layout(document: Document): LaidOutDocument {
        val text = buildText(document)
        val paint = buildPaint(document)
        val contentWidth = (DocGeometry.contentWidth(document)).toInt().coerceAtLeast(1)

        val layout = StaticLayout.Builder
            .obtain(text, 0, text.length, paint, contentWidth)
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .setLineSpacing(0f, document.formatting.lineSpacing)
            .setIncludePad(false)
            .build()

        val contentHeight = DocGeometry.contentHeight(document)
        val pageCount = kotlin.math.max(1, kotlin.math.ceil(layout.height / contentHeight).toInt())
        return LaidOutDocument(layout, pageCount, contentWidth.toFloat())
    }

    private fun buildPaint(document: Document): TextPaint {
        val paint = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        // PdfDocument canvases are measured in points (1/72 inch), so a point of
        // text maps directly to one unit of canvas space.
        paint.textSize = document.formatting.fontSizePt
        paint.color = android.graphics.Color.BLACK
        paint.typeface = if (document.language == Language.HINDI) {
            Typeface.create("sans-serif", if (document.formatting.bold) Typeface.BOLD else Typeface.NORMAL)
        } else {
            Typeface.create(Typeface.SERIF, if (document.formatting.bold) Typeface.BOLD else Typeface.NORMAL)
        }
        return paint
    }

    /** Compose the full letter as a single styled text flow. */
    private fun buildText(document: Document): CharSequence {
        val sb = SpannableStringBuilder()
        val hindi = document.language == Language.HINDI

        // Recipient block
        val r = document.recipient
        sb.append(if (hindi) "सेवा में,\n" else "To,\n")
        if (r.designation.isNotBlank()) sb.append(r.designation).append("\n")
        if (r.officeName.isNotBlank()) sb.append(r.officeName).append("\n")
        val place = listOf(r.location, r.district, r.state).filter { it.isNotBlank() }
        if (place.isNotEmpty()) sb.append(place.joinToString(", ")).append("\n")
        sb.append("\n")

        // Subject (bold)
        if (document.subject.isNotBlank()) {
            val start = sb.length
            sb.append(if (hindi) "विषय: " else "Subject: ").append(document.subject).append("\n\n")
            sb.setSpan(StyleSpan(Typeface.BOLD), start, sb.length, 0)
        }

        // Salutation
        sb.append(if (hindi) "महोदय / महोदया,\n\n" else "Sir / Madam,\n\n")

        // Body
        sb.append(document.body.trim()).append("\n\n")

        // Closing
        if (document.closing.isNotBlank()) sb.append(document.closing.trim()).append("\n\n")

        // Signature block
        val a = document.applicant
        sb.append(if (hindi) "भवदीय,\n" else "Yours faithfully,\n")
        if (a.name.isNotBlank()) sb.append(a.name).append("\n")
        val addr = listOf(a.address, a.village, a.postOffice, a.block, a.district, a.state)
            .filter { it.isNotBlank() }
        if (addr.isNotEmpty()) sb.append(addr.joinToString(", ")).append("\n")
        if (a.pinCode.isNotBlank()) sb.append((if (hindi) "पिन: " else "PIN: ")).append(a.pinCode).append("\n")
        if (a.mobile.isNotBlank()) sb.append((if (hindi) "मोबाइल: " else "Mobile: ")).append(a.mobile).append("\n")
        sb.append(if (hindi) "दिनांक: " else "Date: ").append(DateUtils.dotted())

        return sb
    }
}
