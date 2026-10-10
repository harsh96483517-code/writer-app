package com.writer.app.pdf

import android.content.Context
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import com.writer.app.model.Document
import java.io.File
import java.io.FileOutputStream

/**
 * Streams a freshly generated A4 PDF straight to Android's print framework.
 * Printing is reported as successful only after the OS accepts the job.
 */
class PdfPrinter(
    private val context: Context,
    private val document: Document,
    private val generator: PdfGenerator = PdfGenerator()
) {
    fun adapter(jobName: String): PrintDocumentAdapter = object : PrintDocumentAdapter() {

        override fun onLayout(
            oldAttributes: PrintAttributes?,
            newAttributes: PrintAttributes?,
            cancellationSignal: CancellationSignal?,
            callback: LayoutResultCallback,
            extras: Bundle?
        ) {
            if (cancellationSignal?.isCanceled == true) {
                callback.onLayoutCancelled()
                return
            }
            val info = PrintDocumentInfo.Builder(jobName)
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .build()
            callback.onLayoutFinished(info, true)
        }

        override fun onWrite(
            pages: Array<out PageRange>?,
            destination: ParcelFileDescriptor,
            cancellationSignal: CancellationSignal?,
            callback: WriteResultCallback
        ) {
            try {
                val tmp = File(context.cacheDir, "print_${document.id}.pdf")
                FileOutputStream(tmp).use { out ->
                    kotlinx.coroutines.runBlocking { generator.generate(document, out) }
                }
                ParcelFileDescriptor.AutoCloseOutputStream(destination).use { out ->
                    tmp.inputStream().use { it.copyTo(out) }
                }
                tmp.delete()
                callback.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
            } catch (t: Throwable) {
                callback.onWriteFailed(t.message)
            }
        }
    }
}
