package com.writer.app.features.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.writer.app.design.components.*
import com.writer.app.design.theme.WriterTheme
import com.writer.app.di.AppContainer
import com.writer.app.di.WriterVmFactory
import com.writer.app.model.Language

@Composable
fun EditorScreen(
    container: AppContainer,
    onBack: () -> Unit,
    onPreview: (String) -> Unit
) {
    val glass = WriterTheme.colors
    val vm: EditorViewModel = viewModel(
        factory = WriterVmFactory(container) { c, h -> EditorViewModel(c, h) }
    )
    val doc by vm.document.collectAsState()
    val savedTick by vm.savedTick.collectAsState()
    val prefs by container.prefs.preferences.collectAsState(initial = com.writer.app.model.UserPreferences())
    LaunchedEffect(prefs.autosaveEnabled) { vm.autosaveEnabled = prefs.autosaveEnabled }

    var tab by remember { mutableIntStateOf(0) }
    val tabs = listOf("प्राप्तकर्ता", "आवेदक", "विषय", "मुख्य पत्र")

    val pinError = doc.applicant.pinCode.isNotBlank() && !doc.applicant.pinCode.matches(Regex("\\d{6}"))
    val mobileError = doc.applicant.mobile.isNotBlank() && !doc.applicant.mobile.matches(Regex("\\d{10}"))

    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 16.dp)) {
        GlassToolbar(
            title = "नया आवेदन पत्र",
            onBack = onBack,
            actions = {
                Text(
                    "सेव करें",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.clickable { vm.saveNow() }.padding(8.dp)
                )
            }
        )

        Spacer(Modifier.height(12.dp))
        GlassTextField(
            value = doc.title,
            onValueChange = vm::updateTitle,
            label = "दस्तावेज़ का शीर्षक",
            placeholder = "जैसे: राशन कार्ड सुधार आवेदन"
        )

        Spacer(Modifier.height(12.dp))
        GlassSegmentedControl(
            options = tabs,
            selectedIndex = tab,
            onSelect = { tab = it }
        )

        Spacer(Modifier.height(12.dp))
        Column(
            Modifier.weight(1f).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            when (tab) {
                0 -> RecipientSection(vm)
                1 -> ApplicantSection(vm, pinError, mobileError)
                2 -> SubjectSection(vm)
                else -> BodySection(vm)
            }
            if (savedTick > 0) {
                Text("✓ ड्राफ्ट सेव हो गया", color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall)
            }
        }

        Spacer(Modifier.height(10.dp))
        EditorToolbar(
            onPreview = {
                vm.saveNow()
                onPreview(doc.id)
            }
        )
    }
}

@Composable
private fun RecipientSection(vm: EditorViewModel) {
    val doc by vm.document.collectAsState()
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        GlassTextField(doc.recipient.designation, { vm.updateRecipient(doc.recipient.copy(designation = it)) },
            label = "प्राप्तकर्ता का पदनाम", placeholder = "जिला अधिकारी महोदय", required = true)
        GlassTextField(doc.recipient.officeName, { vm.updateRecipient(doc.recipient.copy(officeName = it)) },
            label = "कार्यालय का नाम", placeholder = "जिला कलेक्टर कार्यालय")
        GlassTextField(doc.recipient.location, { vm.updateRecipient(doc.recipient.copy(location = it)) },
            label = "स्थान", placeholder = "शहर / गाँव")
        GlassTextField(doc.recipient.district, { vm.updateRecipient(doc.recipient.copy(district = it)) },
            label = "जिला", placeholder = "जिले का नाम")
        GlassTextField(doc.recipient.state, { vm.updateRecipient(doc.recipient.copy(state = it)) },
            label = "राज्य", placeholder = "उत्तर प्रदेश")
    }
}

@Composable
private fun ApplicantSection(vm: EditorViewModel, pinError: Boolean, mobileError: Boolean) {
    val doc by vm.document.collectAsState()
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        GlassTextField(doc.applicant.name, { vm.updateApplicant(doc.applicant.copy(name = it)) },
            label = "आवेदक का नाम", placeholder = "आपका नाम", required = true)
        GlassTextField(doc.applicant.guardianName, { vm.updateApplicant(doc.applicant.copy(guardianName = it)) },
            label = "पिता / माता का नाम")
        GlassTextField(doc.applicant.address, { vm.updateApplicant(doc.applicant.copy(address = it)) },
            label = "पूरा पता", placeholder = "मकान / गाँव", minLines = 2, singleLine = false)
        GlassTextField(doc.applicant.village, { vm.updateApplicant(doc.applicant.copy(village = it)) }, label = "गाँव")
        GlassTextField(doc.applicant.postOffice, { vm.updateApplicant(doc.applicant.copy(postOffice = it)) }, label = "डाकघर")
        GlassTextField(doc.applicant.block, { vm.updateApplicant(doc.applicant.copy(block = it)) }, label = "ब्लॉक")
        GlassTextField(doc.applicant.district, { vm.updateApplicant(doc.applicant.copy(district = it)) }, label = "जिला")
        GlassTextField(doc.applicant.state, { vm.updateApplicant(doc.applicant.copy(state = it)) }, label = "राज्य")
        GlassTextField(
            doc.applicant.pinCode, { vm.updateApplicant(doc.applicant.copy(pinCode = it)) },
            label = "पिन कोड", keyboardType = KeyboardType.Number, isError = pinError,
            supportingText = if (pinError) "पिन कोड 6 अंकों का होना चाहिए" else null
        )
        GlassTextField(
            doc.applicant.mobile, { vm.updateApplicant(doc.applicant.copy(mobile = it)) },
            label = "मोबाइल नंबर", keyboardType = KeyboardType.Phone, isError = mobileError,
            supportingText = if (mobileError) "मोबाइल नंबर 10 अंकों का होना चाहिए" else null
        )
    }
}

@Composable
private fun SubjectSection(vm: EditorViewModel) {
    val doc by vm.document.collectAsState()
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        GlassTextField(doc.subject, vm::updateSubject, label = "विषय", placeholder = "आवेदन का विषय लिखें", required = true)
        GlassSegmentedControl(
            options = listOf("Hindi", "English"),
            selectedIndex = if (doc.language == Language.HINDI) 0 else 1,
            onSelect = { vm.updateLanguage(if (it == 0) Language.HINDI else Language.ENGLISH) }
        )
    }
}

@Composable
private fun BodySection(vm: EditorViewModel) {
    val glass = WriterTheme.colors
    val doc by vm.document.collectAsState()
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        GlassTextField(
            doc.body, vm::updateBody, label = "मुख्य पत्र",
            placeholder = "यहाँ अपना आवेदन पत्र लिखें...", minLines = 10, singleLine = false
        )
        Text("${doc.body.length} अक्षर", color = glass.textMuted, style = MaterialTheme.typography.labelSmall)
        GlassTextField(doc.closing, vm::updateClosing, label = "समापन", minLines = 2, singleLine = false,
            placeholder = "अतः आपसे विनम्र निवेदन है कि...")
    }
}

@Composable
private fun EditorToolbar(onPreview: () -> Unit) {
    val glass = WriterTheme.colors
    Row(
        Modifier.fillMaxWidth()
            .glassSurface(RoundedCornerShape(20.dp), elevated = true)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        listOf("B", "I", "U", "≡", "Aa").forEach { label ->
            Text(
                label,
                color = glass.textPrimary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }
        GlassButton(text = "पूर्वावलोकन", onClick = onPreview)
    }
}
