package com.writer.app.navigation

object Routes {
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val TEMPLATES = "templates"
    const val FILES = "files"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"

    const val TEMPLATE_DETAIL = "template/{templateId}"
    fun templateDetail(id: String) = "template/$id"

    // docId may be "new"; templateId may be "none"
    const val EDITOR = "editor/{docId}/{templateId}"
    fun editor(docId: String = "new", templateId: String = "none") = "editor/$docId/$templateId"

    const val PREVIEW = "preview/{docId}"
    fun preview(docId: String) = "preview/$docId"
}
