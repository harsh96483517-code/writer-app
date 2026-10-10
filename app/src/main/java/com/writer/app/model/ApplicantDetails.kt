package com.writer.app.model

/** Details of the person writing the letter. */
data class ApplicantDetails(
    val name: String = "",
    val guardianName: String = "",
    val address: String = "",
    val village: String = "",
    val postOffice: String = "",
    val block: String = "",
    val district: String = "",
    val state: String = "",
    val pinCode: String = "",
    val mobile: String = ""
) {
    fun isEmpty(): Boolean = name.isBlank() && address.isBlank()
}
