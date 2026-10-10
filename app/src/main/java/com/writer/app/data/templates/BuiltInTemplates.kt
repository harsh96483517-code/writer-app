package com.writer.app.data.templates

import com.writer.app.model.Language
import com.writer.app.model.Template
import com.writer.app.model.TemplateCategory

/**
 * Built-in, fully offline template library. Every template ships with a Hindi
 * and an English skeleton. These are editable examples, not official forms.
 */
object BuiltInTemplates {

    private val both = listOf(Language.HINDI, Language.ENGLISH)

    private fun t(
        id: String,
        hi: String,
        en: String,
        descHi: String,
        descEn: String,
        category: TemplateCategory,
        designation: String = "",
        subjectHi: String = "",
        subjectEn: String = "",
        contentHi: String = "",
        contentEn: String = "",
        keywords: List<String> = emptyList()
    ) = Template(
        id = id,
        titleHindi = hi,
        titleEnglish = en,
        descriptionHindi = descHi,
        descriptionEnglish = descEn,
        category = category,
        languageSupport = both,
        defaultSubjectHindi = subjectHi,
        defaultSubjectEnglish = subjectEn,
        defaultRecipientDesignation = designation,
        defaultContentHindi = contentHi,
        defaultContentEnglish = contentEn,
        keywords = keywords
    )

    private const val CLOSE_HI =
        "अतः आपसे विनम्र निवेदन है कि कृपया मेरी समस्या पर विचार करते हुए आवश्यक कार्यवाही करने की कृपा करें।\n\nधन्यवाद।"
    private const val CLOSE_EN =
        "I therefore request you to kindly consider my request and take the necessary action.\n\nThank you."

    val all: List<Template> = listOf(

        /* ---------------- Government applications ---------------- */
        t(
            "gov_dm", "जिला अधिकारी को आवेदन", "Application to District Magistrate",
            "किसी भी समस्या, शिकायत या निवेदन के लिए जिलाधिकारी को आवेदन।",
            "Application to the District Magistrate for any problem, complaint or request.",
            TemplateCategory.GOVERNMENT, "जिला अधिकारी महोदय",
            "समस्या के निवारण हेतु आवेदन पत्र", "Application for redressal of a problem",
            "सेवा में,\nजिला अधिकारी महोदय,\nजनपद — [जिले का नाम]\n\nविषय: [विषय लिखें]\n\nमहोदय,\n\nमैं [आपका नाम], निवासी [पूरा पता], आपका ध्यान निम्नलिखित समस्या की ओर आकर्षित करना चाहता/चाहती हूँ।\n\n[अपनी समस्या विस्तार से लिखें]\n\n$CLOSE_HI",
            "To,\nThe District Magistrate,\nDistrict — [District name]\n\nSubject: [Write subject]\n\nSir/Madam,\n\nI, [Your name], resident of [full address], wish to draw your kind attention to the following problem.\n\n[Describe your problem in detail]\n\n$CLOSE_EN",
            listOf("dm", "district", "जिला", "अधिकारी")
        ),
        t(
            "gov_sdm", "उप जिलाधिकारी को आवेदन", "Application to Sub-Divisional Magistrate",
            "एसडीएम को समस्या या निवेदन हेतु आवेदन।",
            "Application to the SDM for a problem or request.",
            TemplateCategory.GOVERNMENT, "उप जिलाधिकारी महोदय",
            "निवेदन हेतु आवेदन पत्र", "Application for a request",
            "सेवा में,\nउप जिलाधिकारी महोदय,\nतहसील — [तहसील का नाम]\n\nविषय: [विषय लिखें]\n\nमहोदय,\n\n[अपना निवेदन यहाँ लिखें]\n\n$CLOSE_HI",
            "To,\nThe Sub-Divisional Magistrate,\nTehsil — [Tehsil name]\n\nSubject: [Write subject]\n\nSir/Madam,\n\n[Write your request here]\n\n$CLOSE_EN",
            listOf("sdm", "एसडीएम", "उप जिलाधिकारी")
        ),
        t(
            "gov_tehsildar", "तहसीलदार को आवेदन", "Application to Tehsildar",
            "भूमि, नामांतरण या अभिलेख संबंधी निवेदन।",
            "Application regarding land, mutation or records.",
            TemplateCategory.GOVERNMENT, "तहसीलदार महोदय",
            "अभिलेख संबंधी निवेदन", "Application regarding records",
            "सेवा में,\nतहसीलदार महोदय,\nतहसील — [तहसील का नाम]\n\nविषय: [विषय लिखें]\n\nमहोदय,\n\n[भूमि/अभिलेख संबंधी निवेदन यहाँ लिखें]\n\n$CLOSE_HI",
            "To,\nThe Tehsildar,\nTehsil — [Tehsil name]\n\nSubject: [Write subject]\n\nSir/Madam,\n\n[Write your land/records related request here]\n\n$CLOSE_EN",
            listOf("tehsildar", "तहसीलदार", "land", "भूमि")
        ),
        t(
            "gov_police", "थानाध्यक्ष को आवेदन", "Application to Police Station",
            "शिकायत या सूचना हेतु थानाध्यक्ष को आवेदन।",
            "Application to the Station House Officer for a complaint or information.",
            TemplateCategory.GOVERNMENT, "थानाध्यक्ष महोदय",
            "शिकायत दर्ज कराने हेतु आवेदन", "Application to register a complaint",
            "सेवा में,\nथानाध्यक्ष महोदय,\nथाना — [थाने का नाम]\n\nविषय: [विषय लिखें]\n\nमहोदय,\n\n[अपनी शिकायत या सूचना यहाँ लिखें]\n\n$CLOSE_HI",
            "To,\nThe Station House Officer,\nPolice Station — [Name]\n\nSubject: [Write subject]\n\nSir/Madam,\n\n[Write your complaint or information here]\n\n$CLOSE_EN",
            listOf("police", "थाना", "complaint")
        ),
        t(
            "gov_character", "चरित्र प्रमाण पत्र हेतु आवेदन", "Application for Character Certificate",
            "चरित्र प्रमाण पत्र जारी कराने हेतु आवेदन।",
            "Application to obtain a character certificate.",
            TemplateCategory.GOVERNMENT, "थानाध्यक्ष महोदय",
            "चरित्र प्रमाण पत्र हेतु आवेदन", "Application for character certificate",
            "सेवा में,\n[प्राप्तकर्ता का पदनाम],\n[कार्यालय का नाम]\n\nविषय: चरित्र प्रमाण पत्र हेतु आवेदन\n\nमहोदय,\n\nमैं [आपका नाम], पुत्र/पुत्री श्री [पिता का नाम], निवासी [पता], निवेदन करता/करती हूँ कि मुझे [उद्देश्य] के लिए चरित्र प्रमाण पत्र की आवश्यकता है।\n\n$CLOSE_HI",
            "To,\n[Recipient designation],\n[Office name]\n\nSubject: Application for character certificate\n\nSir/Madam,\n\nI, [Your name], son/daughter of [Father's name], resident of [address], request that I require a character certificate for [purpose].\n\n$CLOSE_EN",
            listOf("character", "चरित्र", "certificate")
        ),
        t(
            "gov_residence", "निवास प्रमाण पत्र हेतु आवेदन", "Application for Residence Certificate",
            "निवास प्रमाण पत्र जारी कराने हेतु आवेदन।",
            "Application to obtain a residence certificate.",
            TemplateCategory.GOVERNMENT, "उप जिलाधिकारी महोदय",
            "निवास प्रमाण पत्र हेतु आवेदन", "Application for residence certificate",
            "सेवा में,\nउप जिलाधिकारी महोदय,\nतहसील — [तहसील का नाम]\n\nविषय: निवास प्रमाण पत्र हेतु आवेदन\n\nमहोदय,\n\nमैं [आपका नाम], निवासी [पता], पिछले [वर्ष] वर्षों से यहाँ निवास कर रहा/रही हूँ। मुझे निवास प्रमाण पत्र की आवश्यकता है।\n\n$CLOSE_HI",
            "To,\nThe Sub-Divisional Magistrate,\nTehsil — [Tehsil name]\n\nSubject: Application for residence certificate\n\nSir/Madam,\n\nI, [Your name], resident of [address], have been residing here for the last [years] years. I require a residence certificate.\n\n$CLOSE_EN",
            listOf("residence", "निवास", "domicile")
        ),

        /* ---------------- Education ---------------- */
        t(
            "edu_school_admission", "विद्यालय में प्रवेश हेतु आवेदन", "School Admission Application",
            "विद्यालय में नामांकन हेतु आवेदन।",
            "Application for admission to a school.",
            TemplateCategory.EDUCATION, "प्रधानाचार्य महोदय",
            "प्रवेश हेतु आवेदन", "Application for admission",
            "सेवा में,\nप्रधानाचार्य महोदय,\n[विद्यालय का नाम]\n\nविषय: कक्षा [कक्षा] में प्रवेश हेतु आवेदन\n\nमहोदय,\n\nमैं [आपका नाम], निवेदन करता/करती हूँ कि मेरे बच्चे [बच्चे का नाम] को कक्षा [कक्षा] में प्रवेश दिलाने की कृपा करें।\n\n$CLOSE_HI",
            "To,\nThe Principal,\n[School name]\n\nSubject: Application for admission to class [class]\n\nSir/Madam,\n\nI, [Your name], request that my child [child's name] may kindly be admitted to class [class].\n\n$CLOSE_EN",
            listOf("school", "admission", "प्रवेश", "विद्यालय")
        ),
        t(
            "edu_scholarship", "छात्रवृत्ति हेतु आवेदन", "Scholarship Application",
            "छात्रवृत्ति प्राप्त करने हेतु आवेदन।",
            "Application to avail a scholarship.",
            TemplateCategory.EDUCATION, "प्रधानाचार्य महोदय",
            "छात्रवृत्ति हेतु आवेदन", "Application for scholarship",
            "सेवा में,\nप्रधानाचार्य महोदय,\n[संस्थान का नाम]\n\nविषय: छात्रवृत्ति हेतु आवेदन\n\nमहोदय,\n\nमैं [आपका नाम], कक्षा [कक्षा] का/की छात्र/छात्रा हूँ। मेरे परिवार की आर्थिक स्थिति कमजोर है, अतः छात्रवृत्ति हेतु आवेदन कर रहा/रही हूँ।\n\n$CLOSE_HI",
            "To,\nThe Principal,\n[Institute name]\n\nSubject: Application for scholarship\n\nSir/Madam,\n\nI, [Your name], am a student of class [class]. My family's financial condition is weak, so I am applying for a scholarship.\n\n$CLOSE_EN",
            listOf("scholarship", "छात्रवृत्ति", "student")
        ),
        t(
            "edu_leave", "अवकाश हेतु आवेदन", "Leave Application",
            "विद्यालय या महाविद्यालय में अवकाश हेतु आवेदन।",
            "Application for leave at school or college.",
            TemplateCategory.EDUCATION, "प्रधानाचार्य महोदय",
            "अवकाश हेतु आवेदन", "Application for leave",
            "सेवा में,\nप्रधानाचार्य महोदय,\n[संस्थान का नाम]\n\nविषय: अवकाश हेतु आवेदन\n\nमहोदय,\n\nमैं [आपका नाम], कक्षा [कक्षा] का/की छात्र/छात्रा हूँ। [कारण] के कारण मुझे [दिनांक] से [दिनांक] तक अवकाश की आवश्यकता है।\n\n$CLOSE_HI",
            "To,\nThe Principal,\n[Institute name]\n\nSubject: Application for leave\n\nSir/Madam,\n\nI, [Your name], am a student of class [class]. Due to [reason] I need leave from [date] to [date].\n\n$CLOSE_EN",
            listOf("leave", "अवकाश", "chhutti")
        ),
        t(
            "edu_bonafide", "बोनाफाइड प्रमाण पत्र हेतु आवेदन", "Bonafide Certificate Request",
            "बोनाफाइड प्रमाण पत्र हेतु आवेदन।",
            "Application for a bonafide certificate.",
            TemplateCategory.EDUCATION, "प्रधानाचार्य महोदय",
            "बोनाफाइड प्रमाण पत्र हेतु आवेदन", "Application for bonafide certificate",
            "सेवा में,\nप्रधानाचार्य महोदय,\n[संस्थान का नाम]\n\nविषय: बोनाफाइड प्रमाण पत्र हेतु आवेदन\n\nमहोदय,\n\nमैं [आपका नाम], कक्षा [कक्षा] का/की छात्र/छात्रा हूँ। मुझे [उद्देश्य] के लिए बोनाफाइड प्रमाण पत्र की आवश्यकता है।\n\n$CLOSE_HI",
            "To,\nThe Principal,\n[Institute name]\n\nSubject: Application for bonafide certificate\n\nSir/Madam,\n\nI, [Your name], am a student of class [class]. I require a bonafide certificate for [purpose].\n\n$CLOSE_EN",
            listOf("bonafide", "प्रमाण")
        ),
        t(
            "edu_tc", "स्थानांतरण प्रमाण पत्र हेतु आवेदन", "Transfer Certificate Request",
            "टीसी जारी कराने हेतु आवेदन।",
            "Application to obtain a transfer certificate.",
            TemplateCategory.EDUCATION, "प्रधानाचार्य महोदय",
            "स्थानांतरण प्रमाण पत्र हेतु आवेदन", "Application for transfer certificate",
            "सेवा में,\nप्रधानाचार्य महोदय,\n[संस्थान का नाम]\n\nविषय: स्थानांतरण प्रमाण पत्र हेतु आवेदन\n\nमहोदय,\n\nमैं [आपका नाम], कक्षा [कक्षा] का/की छात्र/छात्रा हूँ। [कारण] के कारण मुझे स्थानांतरण प्रमाण पत्र की आवश्यकता है।\n\n$CLOSE_HI",
            "To,\nThe Principal,\n[Institute name]\n\nSubject: Application for transfer certificate\n\nSir/Madam,\n\nI, [Your name], am a student of class [class]. Due to [reason] I require a transfer certificate.\n\n$CLOSE_EN",
            listOf("tc", "transfer", "स्थानांतरण")
        ),

        /* ---------------- Schemes & documents ---------------- */
        t(
            "sch_ration_correction", "राशन कार्ड सुधार हेतु आवेदन", "Ration Card Correction Request",
            "राशन कार्ड में सुधार हेतु आवेदन।",
            "Application to correct details on a ration card.",
            TemplateCategory.SCHEMES, "जिला पूर्ति अधिकारी महोदय",
            "राशन कार्ड में सुधार हेतु आवेदन", "Application for ration card correction",
            "सेवा में,\nजिला पूर्ति अधिकारी महोदय,\nजनपद — [जिले का नाम]\n\nविषय: राशन कार्ड में सुधार हेतु आवेदन\n\nमहोदय,\n\nमेरे राशन कार्ड संख्या [नंबर] में निम्नलिखित त्रुटि है — [त्रुटि विवरण]। कृपया इसे ठीक करने की कृपा करें।\n\n$CLOSE_HI",
            "To,\nThe District Supply Officer,\nDistrict — [District name]\n\nSubject: Application for ration card correction\n\nSir/Madam,\n\nMy ration card number [number] contains the following error — [error details]. Kindly correct it.\n\n$CLOSE_EN",
            listOf("ration", "राशन", "correction")
        ),
        t(
            "sch_ration_remove", "राशन कार्ड से नाम हटाने हेतु आवेदन", "Ration Card Member Removal Request",
            "राशन कार्ड से नाम हटवाने हेतु आवेदन।",
            "Application to remove a member from a ration card.",
            TemplateCategory.SCHEMES, "जिला पूर्ति अधिकारी महोदय",
            "राशन कार्ड से नाम हटाने हेतु आवेदन", "Application to remove a name from ration card",
            "सेवा में,\nजिला पूर्ति अधिकारी महोदय,\nजनपद — [जिले का नाम]\n\nविषय: राशन कार्ड से नाम हटाने हेतु आवेदन\n\nमहोदय,\n\nमेरे राशन कार्ड संख्या [नंबर] से श्री/श्रीमती [नाम] का नाम हटवाना है क्योंकि [कारण]।\n\n$CLOSE_HI",
            "To,\nThe District Supply Officer,\nDistrict — [District name]\n\nSubject: Application to remove a name from ration card\n\nSir/Madam,\n\nI wish to remove the name of [name] from my ration card number [number] because [reason].\n\n$CLOSE_EN",
            listOf("ration", "राशन", "remove")
        ),
        t(
            "sch_income", "आय प्रमाण पत्र हेतु आवेदन", "Income Certificate Request",
            "आय प्रमाण पत्र हेतु आवेदन।",
            "Application for an income certificate.",
            TemplateCategory.SCHEMES, "उप जिलाधिकारी महोदय",
            "आय प्रमाण पत्र हेतु आवेदन", "Application for income certificate",
            "सेवा में,\nउप जिलाधिकारी महोदय,\nतहसील — [तहसील का नाम]\n\nविषय: आय प्रमाण पत्र हेतु आवेदन\n\nमहोदय,\n\nमुझे [उद्देश्य] के लिए आय प्रमाण पत्र की आवश्यकता है। मेरी वार्षिक आय लगभग ₹[राशि] है।\n\n$CLOSE_HI",
            "To,\nThe Sub-Divisional Magistrate,\nTehsil — [Tehsil name]\n\nSubject: Application for income certificate\n\nSir/Madam,\n\nI require an income certificate for [purpose]. My annual income is approximately Rs. [amount].\n\n$CLOSE_EN",
            listOf("income", "आय", "certificate")
        ),
        t(
            "sch_caste", "जाति प्रमाण पत्र हेतु आवेदन", "Caste Certificate Request",
            "जाति प्रमाण पत्र हेतु आवेदन।",
            "Application for a caste certificate.",
            TemplateCategory.SCHEMES, "उप जिलाधिकारी महोदय",
            "जाति प्रमाण पत्र हेतु आवेदन", "Application for caste certificate",
            "सेवा में,\nउप जिलाधिकारी महोदय,\nतहसील — [तहसील का नाम]\n\nविषय: जाति प्रमाण पत्र हेतु आवेदन\n\nमहोदय,\n\nमुझे [उद्देश्य] के लिए जाति प्रमाण पत्र की आवश्यकता है।\n\n$CLOSE_HI",
            "To,\nThe Sub-Divisional Magistrate,\nTehsil — [Tehsil name]\n\nSubject: Application for caste certificate\n\nSir/Madam,\n\nI require a caste certificate for [purpose].\n\n$CLOSE_EN",
            listOf("caste", "जाति")
        ),
        t(
            "sch_pmkisan", "पीएम-किसान संबंधी आवेदन", "PM-Kisan Related Request",
            "पीएम-किसान योजना संबंधी निवेदन।",
            "Application regarding the PM-Kisan scheme.",
            TemplateCategory.SCHEMES, "कृषि अधिकारी महोदय",
            "पीएम-किसान संबंधी निवेदन", "Request regarding PM-Kisan",
            "सेवा में,\nकृषि अधिकारी महोदय,\nजनपद — [जिले का नाम]\n\nविषय: पीएम-किसान संबंधी निवेदन\n\nमहोदय,\n\nमैं [आपका नाम], किसान हूँ। पीएम-किसान योजना के अंतर्गत [समस्या/निवेदन] के संबंध में निवेदन कर रहा हूँ।\n\n$CLOSE_HI",
            "To,\nThe Agriculture Officer,\nDistrict — [District name]\n\nSubject: Request regarding PM-Kisan\n\nSir/Madam,\n\nI, [Your name], am a farmer. I am writing regarding [problem/request] under the PM-Kisan scheme.\n\n$CLOSE_EN",
            listOf("pm-kisan", "kisan", "किसान")
        ),

        /* ---------------- Complaints ---------------- */
        t(
            "cmp_electricity", "बिजली संबंधी शिकायत", "Electricity Complaint",
            "बिजली आपूर्ति या बिल संबंधी शिकायत।",
            "Complaint regarding electricity supply or billing.",
            TemplateCategory.COMPLAINTS, "अधिशासी अभियंता महोदय",
            "बिजली संबंधी शिकायत", "Electricity complaint",
            "सेवा में,\nअधिशासी अभियंता महोदय,\nविद्युत विभाग — [क्षेत्र]\n\nविषय: बिजली संबंधी शिकायत\n\nमहोदय,\n\nमैं [आपका नाम], उपभोक्ता संख्या [नंबर], निवासी [पता]। [समस्या विवरण] के संबंध में शिकायत दर्ज करना चाहता/चाहती हूँ।\n\n$CLOSE_HI",
            "To,\nThe Executive Engineer,\nElectricity Department — [Area]\n\nSubject: Electricity complaint\n\nSir/Madam,\n\nI, [Your name], consumer number [number], resident of [address], wish to lodge a complaint regarding [problem details].\n\n$CLOSE_EN",
            listOf("electricity", "बिजली", "bijli")
        ),
        t(
            "cmp_water", "जल आपूर्ति संबंधी शिकायत", "Water Supply Complaint",
            "जल आपूर्ति संबंधी शिकायत।",
            "Complaint regarding water supply.",
            TemplateCategory.COMPLAINTS, "अधिशासी अभियंता महोदय",
            "जल आपूर्ति संबंधी शिकायत", "Water supply complaint",
            "सेवा में,\nअधिशासी अभियंता महोदय,\nजल निगम — [क्षेत्र]\n\nविषय: जल आपूर्ति संबंधी शिकायत\n\nमहोदय,\n\nमेरे क्षेत्र में [दिनों] से जल आपूर्ति बाधित है। [समस्या विवरण]।\n\n$CLOSE_HI",
            "To,\nThe Executive Engineer,\nWater Board — [Area]\n\nSubject: Water supply complaint\n\nSir/Madam,\n\nWater supply in my area has been disrupted for [days]. [Problem details].\n\n$CLOSE_EN",
            listOf("water", "जल", "पानी")
        ),
        t(
            "cmp_road", "सड़क मरम्मत हेतु शिकायत", "Road Repair Complaint",
            "सड़क की स्थिति सुधारने हेतु शिकायत।",
            "Complaint to repair a road.",
            TemplateCategory.COMPLAINTS, "अधिशासी अभियंता महोदय",
            "सड़क मरम्मत हेतु शिकायत", "Road repair complaint",
            "सेवा में,\nअधिशासी अभियंता महोदय,\nलोक निर्माण विभाग\n\nविषय: सड़क मरम्मत हेतु शिकायत\n\nमहोदय,\n\n[स्थान] की सड़क अत्यंत जर्जर है, जिससे आवागमन में कठिनाई हो रही है। कृपया मरम्मत कराने की कृपा करें।\n\n$CLOSE_HI",
            "To,\nThe Executive Engineer,\nPublic Works Department\n\nSubject: Road repair complaint\n\nSir/Madam,\n\nThe road at [location] is in a very bad condition, causing difficulty in commuting. Kindly arrange for its repair.\n\n$CLOSE_EN",
            listOf("road", "सड़क", "repair")
        ),
        t(
            "cmp_garbage", "कचरा उठान हेतु शिकायत", "Garbage Collection Complaint",
            "कचरा उठान न होने संबंधी शिकायत।",
            "Complaint regarding non-collection of garbage.",
            TemplateCategory.COMPLAINTS, "नगर आयुक्त महोदय",
            "कचरा उठान हेतु शिकायत", "Garbage collection complaint",
            "सेवा में,\nनगर आयुक्त महोदय,\nनगर पालिका — [नगर]\n\nविषय: कचरा उठान हेतु शिकायत\n\nमहोदय,\n\nमेरे मोहल्ले में [दिनों] से कचरा नहीं उठाया गया है, जिससे स्वास्थ्य संबंधी समस्या हो रही है।\n\n$CLOSE_HI",
            "To,\nThe Municipal Commissioner,\nMunicipality — [City]\n\nSubject: Garbage collection complaint\n\nSir/Madam,\n\nGarbage has not been collected in my locality for [days], causing health issues.\n\n$CLOSE_EN",
            listOf("garbage", "कचरा", "safai")
        ),
        t(
            "cmp_consumer", "उपभोक्ता शिकायत", "Consumer Complaint",
            "उपभोक्ता अदालत में शिकायत हेतु आवेदन।",
            "Application for a complaint to the consumer forum.",
            TemplateCategory.COMPLAINTS, "अध्यक्ष महोदय",
            "उपभोक्ता शिकायत", "Consumer complaint",
            "सेवा में,\nअध्यक्ष महोदय,\nजिला उपभोक्ता आयोग\n\nविषय: उपभोक्ता शिकायत\n\nमहोदय,\n\nमैंने [दुकान/कंपनी] से [वस्तु/सेवा] खरीदी, परंतु [समस्या]। कृपया उचित कार्रवाई करने की कृपा करें।\n\n$CLOSE_HI",
            "To,\nThe President,\nDistrict Consumer Commission\n\nSubject: Consumer complaint\n\nSir/Madam,\n\nI purchased [goods/service] from [shop/company], but [problem]. Kindly take appropriate action.\n\n$CLOSE_EN",
            listOf("consumer", "उपभोक्ता", "complaint")
        ),

        /* ---------------- Banking ---------------- */
        t(
            "bank_address", "पता परिवर्तन हेतु आवेदन", "Address Update Request",
            "बैंक खाते में पता बदलवाने हेतु आवेदन।",
            "Application to update the address on a bank account.",
            TemplateCategory.BANKING, "शाखा प्रबंधक महोदय",
            "पता परिवर्तन हेतु आवेदन", "Application for address update",
            "सेवा में,\nशाखा प्रबंधक महोदय,\n[बैंक का नाम] — [शाखा]\n\nविषय: पता परिवर्तन हेतु आवेदन\n\nमहोदय,\n\nमेरा बचत खाता संख्या [नंबर] है। कृपया मेरे पते को [नया पता] में अद्यतन करने की कृपा करें।\n\n$CLOSE_HI",
            "To,\nThe Branch Manager,\n[Bank name] — [Branch]\n\nSubject: Application for address update\n\nSir/Madam,\n\nMy savings account number is [number]. Kindly update my address to [new address].\n\n$CLOSE_EN",
            listOf("bank", "address", "पता")
        ),
        t(
            "bank_atm", "एटीएम कार्ड हेतु आवेदन", "ATM Card Request",
            "नया एटीएम कार्ड प्राप्त करने हेतु आवेदन।",
            "Application to obtain a new ATM card.",
            TemplateCategory.BANKING, "शाखा प्रबंधक महोदय",
            "एटीएम कार्ड हेतु आवेदन", "Application for ATM card",
            "सेवा में,\nशाखा प्रबंधक महोदय,\n[बैंक का नाम] — [शाखा]\n\nविषय: एटीएम कार्ड हेतु आवेदन\n\nमहोदय,\n\nमेरा खाता संख्या [नंबर] है। कृपया मुझे नया एटीएम कार्ड जारी करने की कृपा करें।\n\n$CLOSE_HI",
            "To,\nThe Branch Manager,\n[Bank name] — [Branch]\n\nSubject: Application for ATM card\n\nSir/Madam,\n\nMy account number is [number]. Kindly issue me a new ATM card.\n\n$CLOSE_EN",
            listOf("atm", "card", "bank")
        ),
        t(
            "bank_statement", "खाता विवरण हेतु आवेदन", "Account Statement Request",
            "बैंक खाते का विवरण प्राप्त करने हेतु आवेदन।",
            "Application to obtain a bank account statement.",
            TemplateCategory.BANKING, "शाखा प्रबंधक महोदय",
            "खाता विवरण हेतु आवेदन", "Application for account statement",
            "सेवा में,\nशाखा प्रबंधक महोदय,\n[बैंक का नाम] — [शाखा]\n\nविषय: खाता विवरण हेतु आवेदन\n\nमहोदय,\n\nकृपया मेरे खाता संख्या [नंबर] का विवरण [अवधि] के लिए उपलब्ध कराने की कृपा करें।\n\n$CLOSE_HI",
            "To,\nThe Branch Manager,\n[Bank name] — [Branch]\n\nSubject: Application for account statement\n\nSir/Madam,\n\nKindly provide me the statement of my account number [number] for the period [period].\n\n$CLOSE_EN",
            listOf("statement", "bank", "विवरण")
        ),

        /* ---------------- Employment ---------------- */
        t(
            "job_application", "नौकरी हेतु आवेदन", "Job Application",
            "किसी पद हेतु नौकरी का आवेदन।",
            "Application for a job position.",
            TemplateCategory.EMPLOYMENT, "प्रबंधक महोदय",
            "पद [पद का नाम] हेतु आवेदन", "Application for the post of [post]",
            "सेवा में,\nप्रबंधक महोदय,\n[कंपनी का नाम]\n\nविषय: पद [पद का नाम] हेतु आवेदन\n\nमहोदय,\n\nमैं [आपका नाम], आपके संस्थान में पद [पद का नाम] हेतु आवेदन करना चाहता/चाहती हूँ। मेरी शैक्षणिक योग्यता [योग्यता] है।\n\n$CLOSE_HI",
            "To,\nThe Manager,\n[Company name]\n\nSubject: Application for the post of [post]\n\nSir/Madam,\n\nI, [Your name], wish to apply for the post of [post] at your organisation. My educational qualification is [qualification].\n\n$CLOSE_EN",
            listOf("job", "नौकरी", "employment")
        ),
        t(
            "job_resignation", "त्यागपत्र", "Resignation Letter",
            "नौकरी से इस्तीफा देने हेतु पत्र।",
            "Letter to resign from a job.",
            TemplateCategory.EMPLOYMENT, "प्रबंधक महोदय",
            "त्यागपत्र", "Resignation letter",
            "सेवा में,\nप्रबंधक महोदय,\n[कंपनी का नाम]\n\nविषय: त्यागपत्र\n\nमहोदय,\n\nमैं [आपका नाम], पद [पद], निवेदन करता/करती हूँ कि मैं [कारण] के कारण अपने पद से त्यागपत्र दे रहा/रही हूँ।\n\n$CLOSE_HI",
            "To,\nThe Manager,\n[Company name]\n\nSubject: Resignation letter\n\nSir/Madam,\n\nI, [Your name], [post], hereby resign from my position due to [reason].\n\n$CLOSE_EN",
            listOf("resignation", "इस्तीफा", "त्यागपत्र")
        ),
        t(
            "job_experience", "अनुभव प्रमाण पत्र हेतु आवेदन", "Experience Certificate Request",
            "अनुभव प्रमाण पत्र हेतु आवेदन।",
            "Application for an experience certificate.",
            TemplateCategory.EMPLOYMENT, "प्रबंधक महोदय",
            "अनुभव प्रमाण पत्र हेतु आवेदन", "Application for experience certificate",
            "सेवा में,\nप्रबंधक महोदय,\n[कंपनी का नाम]\n\nविषय: अनुभव प्रमाण पत्र हेतु आवेदन\n\nमहोदय,\n\nमैं [आपका नाम], [अवधि] तक आपके संस्थान में कार्यरत था/थी। कृपया मुझे अनुभव प्रमाण पत्र जारी करने की कृपा करें।\n\n$CLOSE_HI",
            "To,\nThe Manager,\n[Company name]\n\nSubject: Application for experience certificate\n\nSir/Madam,\n\nI, [Your name], worked at your organisation until [period]. Kindly issue me an experience certificate.\n\n$CLOSE_EN",
            listOf("experience", "अनुभव")
        ),

        /* ---------------- Personal ---------------- */
        t(
            "per_formal", "सामान्य औपचारिक पत्र", "General Formal Letter",
            "किसी भी औपचारिक उद्देश्य हेतु सामान्य पत्र।",
            "A general formal letter for any purpose.",
            TemplateCategory.PERSONAL, "",
            "औपचारिक पत्र", "Formal letter",
            "सेवा में,\n[प्राप्तकर्ता का पदनाम],\n[कार्यालय का नाम]\n\nविषय: [विषय लिखें]\n\nमहोदय/महोदया,\n\n[पत्र की मुख्य सामग्री यहाँ लिखें]\n\n$CLOSE_HI",
            "To,\n[Recipient designation],\n[Office name]\n\nSubject: [Write subject]\n\nSir/Madam,\n\n[Write the main content here]\n\n$CLOSE_EN",
            listOf("formal", "औपचारिक")
        ),
        t(
            "per_thankyou", "धन्यवाद पत्र", "Thank-you Letter",
            "कृतज्ञता व्यक्त करने हेतु पत्र।",
            "A letter to express gratitude.",
            TemplateCategory.PERSONAL, "",
            "धन्यवाद पत्र", "Thank-you letter",
            "सेवा में,\n[प्राप्तकर्ता का नाम],\n[पता]\n\nविषय: धन्यवाद\n\nमहोदय/महोदया,\n\n[सहायता विवरण] के लिए मैं आपका हृदय से धन्यवाद करता/करती हूँ।\n\n$CLOSE_HI",
            "To,\n[Recipient name],\n[Address]\n\nSubject: Thank you\n\nSir/Madam,\n\nI sincerely thank you for [help details].\n\n$CLOSE_EN",
            listOf("thanks", "धन्यवाद")
        ),
        t(
            "per_permission", "अनुमति हेतु पत्र", "Permission Request Letter",
            "किसी कार्य हेतु अनुमति लेने हेतु पत्र।",
            "A letter to seek permission for something.",
            TemplateCategory.PERSONAL, "",
            "अनुमति हेतु पत्र", "Permission request letter",
            "सेवा में,\n[प्राप्तकर्ता का नाम],\n[पता]\n\nविषय: अनुमति हेतु पत्र\n\nमहोदय/महोदया,\n\nमैं [आपका नाम], [कार्य] के लिए आपसे अनुमति चाहता/चाहती हूँ।\n\n$CLOSE_HI",
            "To,\n[Recipient name],\n[Address]\n\nSubject: Permission request\n\nSir/Madam,\n\nI, [Your name], seek your permission for [task].\n\n$CLOSE_EN",
            listOf("permission", "अनुमति")
        )
    )
}
