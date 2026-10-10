package com.writer.app.storage

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.writer.app.common.DateUtils
import java.io.File

/**
 * Writes generated PDFs into the app's private exports folder and exposes them
 * through a FileProvider so they can be shared without extra permissions.
 */
class FileExporter(private val context: Context) {

    private val exportDir: File
        get() = File(context.filesDir, "exports").apply { if (!exists()) mkdirs() }

    fun newOutputFile(baseName: String): File {
        val safe = DateUtils.sanitizeFileName(baseName)
        var file = File(exportDir, "$safe.pdf")
        var i = 1
        while (file.exists()) {            // never silently overwrite
            file = File(exportDir, "${safe}_$i.pdf")
            i++
        }
        return file
    }

    fun uriFor(file: File): Uri =
        FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)

    fun listExportedPdfs(): List<File> =
        exportDir.listFiles { f -> f.extension == "pdf" }?.sortedByDescending { it.lastModified() } ?: emptyList()
}
