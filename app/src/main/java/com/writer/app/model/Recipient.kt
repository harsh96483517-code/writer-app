package com.writer.app.model

/** Details of the person / office a letter is addressed to. */
data class Recipient(
    val designation: String = "",
    val officeName: String = "",
    val location: String = "",
    val district: String = "",
    val state: String = ""
) {
    fun isEmpty(): Boolean =
        designation.isBlank() && officeName.isBlank() && location.isBlank() &&
            district.isBlank() && state.isBlank()
}
