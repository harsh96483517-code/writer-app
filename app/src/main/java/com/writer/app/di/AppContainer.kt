package com.writer.app.di

import android.content.Context
import com.writer.app.data.db.WriterDatabase
import com.writer.app.data.prefs.PreferencesRepository
import com.writer.app.data.repository.DocumentRepository
import com.writer.app.data.repository.TemplateRepository
import com.writer.app.pdf.PdfGenerator
import com.writer.app.storage.FileExporter

/**
 * Minimal manual dependency container. One instance lives on the Application,
 * so there is no need for a DI framework in this app.
 */
class AppContainer(context: Context) {
    private val appContext = context.applicationContext
    private val database = WriterDatabase.get(appContext)

    val documents = DocumentRepository(database.documentDao())
    val templates = TemplateRepository()
    val prefs = PreferencesRepository(appContext)
    val fileExporter = FileExporter(appContext)
    val pdfGenerator = PdfGenerator()
}
