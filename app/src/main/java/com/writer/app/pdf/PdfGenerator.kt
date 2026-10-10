package com.writer.app.pdf

import android.graphics.pdf.PdfDocument
import com.writer.app.model.Document
import java.io.OutputStream
import kotlin.coroutines.resume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Renders a [Document] into a real, multi-page A4 PDF with selectable text.
 * Runs off the main thread. Never screenshot-based.
 */
class PdfGenerator(private val engine: DocumentLayoutEngine = DocumentLayoutEngine()) {

    sealed interface Result {
        data class Success(val pageCount: Int) : Result
        data class Failure(val message: String) : Result
    }

    suspend fun generate(document: Document, output: OutputStream): Result =
        withContext(Dispatchers.Default) {
            val pdf = PdfDocument()
            try {
                if (document.body.isBlank() && document.subject.isBlank()) {
                    return@withContext Result.Failure("दस्तावेज़ खाली है। कृपया सामग्री जोड़ें।")
                }

                val laidOut = engine.layout(document)
                val left = DocGeometry.left(document)
                val top = DocGeometry.top(document)
                val contentHeight = DocGeometry.contentHeight(document)
                val pageWidth = DocGeometry.pageWidth
                val pageHeight = DocGeometry.pageHeight

                for (page in 0 until laidOut.pageCount) {
                    val pageInfo = PdfDocument.PageInfo.Builder(
                        pageWidth.toInt(),
                        pageHeight.toInt(),
                        page + 1
                    ).create()
                    val pdfPage = pdf.startPage(pageInfo)
                    val canvas = pdfPage.canvas

                    // Clip to the printable area and slide the single text flow up
                    // by one content-height per page.
                    canvas.save()
                    canvas.clipRect(left, top, pageWidth - DocGeometry.right(document), pageHeight - DocGeometry.bottom(document))
                    canvas.translate(left, top - page * contentHeight)
                    laidOut.layout.draw(canvas)
                    canvas.restore()

                    pdf.finishPage(pdfPage)
                }

                pdf.writeTo(output)
                Result.Success(laidOut.pageCount)
            } catch (t: Throwable) {
                Result.Failure(t.message ?: "PDF नहीं बन सकी।")
            } finally {
                pdf.close()
            }
        }
}
