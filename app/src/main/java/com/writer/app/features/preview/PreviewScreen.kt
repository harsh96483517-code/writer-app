package com.writer.app.features.preview

import android.content.Context
import android.content.Intent
import android.print.PrintManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.writer.app.common.DateUtils
import com.writer.app.design.components.GlassButton
import com.writer.app.design.components.GlassLoadingIndicator
import com.writer.app.design.components.GlassToolbar
import com.writer.app.design.components.GlassToast
import com.writer.app.design.theme.WriterTheme
import com.writer.app.di.AppContainer
import com.writer.app.model.Document
import com.writer.app.model.Language
import com.writer.app.pdf.PdfGenerator
import com.writer.app.pdf.PdfPrinter
import kotlinx.coroutines.launch

@Composable
fun PreviewScreen(
    container: AppContainer,
    documentId: String,
    onBack: () -> Unit,
    onEdit: (String) -> Unit
) {
    val glass = WriterTheme.colors
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var doc by remember { mutableStateOf<Document?>(null) }
    var pageCount by remember { mutableIntStateOf(1) }
    var currentPage by remember { mutableIntStateOf(1) }
    var status by remember { mutableStateOf<String?>(null) }
    var busy by remember { mutableStateOf(false) }

    LaunchedEffect(documentId) {
        val loaded = container.documents.get(documentId)
        doc = loaded
        if (loaded != null) {
            pageCount = container.pdfGenerator.let {
                com.writer.app.pdf.DocumentLayoutEngine().layout(loaded).pageCount
            }
        }
    }

    val createPdf = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument("application/pdf")
    ) { uri ->
        val d = doc ?: return@rememberLauncherForActivityResult
        if (uri == null) return@rememberLauncherForActivityResult
        scope.launch {
            busy = true
            val result = context.contentResolver.openOutputStream(uri)?.use { out ->
                container.pdfGenerator.generate(d, out)
            }
            busy = false
            status = when (result) {
                is PdfGenerator.Result.Success -> "PDF बन गई ✓"
                is PdfGenerator.Result.Failure -> result.message
                null -> "PDF नहीं बन सकी।"
            }
        }
    }

    val current = doc
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        GlassToolbar(title = "दस्तावेज़ पूर्वावलोकन", onBack = onBack)

        Spacer(Modifier.height(12.dp))
        Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
            if (current == null) {
                GlassLoadingIndicator("दस्तावेज़ लोड हो रहा है...")
            } else {
                A4Sheet(current, Modifier.fillMaxSize().verticalScroll(rememberScrollState()))
            }
        }

        status?.let {
            Spacer(Modifier.height(8.dp))
            GlassToast(it)
        }

        Spacer(Modifier.height(10.dp))
        Text("पेज $currentPage / $pageCount", color = glass.textMuted,
            style = MaterialTheme.typography.labelSmall, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(Modifier.height(10.dp))
        if (busy) {
            GlassLoadingIndicator("PDF बन रही है...")
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            GlassButton("संपादित करें", { onEdit(documentId) }, Modifier.weight(1f), filled = false)
            GlassButton("PDF बनाएँ", {
                createPdf.launch("Application_${DateUtils.fileStamp()}")
            }, Modifier.weight(1f))
        }
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            GlassButton("प्रिंट", { current?.let { printDocument(context, container, it) } },
                Modifier.weight(1f), filled = false)
            GlassButton("शेयर", { current?.let { shareDocument(context, container, it) { msg -> status = msg } } },
                Modifier.weight(1f), filled = false)
        }
    }
}

@Composable
private fun A4Sheet(document: Document, modifier: Modifier = Modifier) {
    val hindi = document.language == Language.HINDI
    Box(
        modifier
            .fillMaxWidth()
            .aspectRatio(210f / 297f)
            .background(Color.White)
            .padding(20.dp)
    ) {
        Column {
            val r = document.recipient
            Text(if (hindi) "सेवा में," else "To,", color = Color.Black, style = MaterialTheme.typography.bodyMedium)
            listOf(r.designation, r.officeName, r.location, r.district, r.state)
                .filter { it.isNotBlank() }
                .forEach { Text(it, color = Color.Black, style = MaterialTheme.typography.bodyMedium) }
            Spacer(Modifier.height(10.dp))
            if (document.subject.isNotBlank()) {
                Text(
                    "${if (hindi) "विषय" else "Subject"}: ${document.subject}",
                    color = Color.Black, fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(if (hindi) "महोदय / महोदया," else "Sir / Madam,", color = Color.Black, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
            Text(document.body, color = Color.Black, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(12.dp))
            if (document.closing.isNotBlank()) {
                Text(document.closing, color = Color.Black, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(12.dp))
            }
            Text(if (hindi) "भवदीय," else "Yours faithfully,", color = Color.Black, style = MaterialTheme.typography.bodyMedium)
            if (document.applicant.name.isNotBlank()) Text(document.applicant.name, color = Color.Black, style = MaterialTheme.typography.bodyMedium)
            val addr = listOf(document.applicant.address, document.applicant.village, document.applicant.district, document.applicant.state)
                .filter { it.isNotBlank() }
            if (addr.isNotEmpty()) Text(addr.joinToString(", "), color = Color.Black, style = MaterialTheme.typography.bodyMedium)
            if (document.applicant.mobile.isNotBlank())
                Text("${if (hindi) "मोबाइल" else "Mobile"}: ${document.applicant.mobile}", color = Color.Black, style = MaterialTheme.typography.bodyMedium)
            Text("${if (hindi) "दिनांक" else "Date"}: ${DateUtils.dotted()}", color = Color.Black, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

private fun printDocument(context: Context, container: AppContainer, document: Document) {
    val printManager = ContextCompat.getSystemService(context, PrintManager::class.java) ?: return
    val adapter = PdfPrinter(context, document, container.pdfGenerator)
        .adapter(document.title.ifBlank { "Application" })
    printManager.print(document.title.ifBlank { "Application" }, adapter, null)
}

private fun shareDocument(
    context: Context,
    container: AppContainer,
    document: Document,
    onStatus: (String) -> Unit
) {
    val file = container.fileExporter.newOutputFile(document.title.ifBlank { "Application" })
    try {
        file.outputStream().use { out ->
            kotlinx.coroutines.runBlocking { container.pdfGenerator.generate(document, out) }
        }
        val uri = container.fileExporter.uriFor(file)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "PDF शेयर करें"))
    } catch (t: Throwable) {
        onStatus("PDF शेयर नहीं हो सकी।")
    }
}
