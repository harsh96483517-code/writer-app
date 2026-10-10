package com.writer.app.data.db

import androidx.room.TypeConverter
import com.writer.app.model.DocumentStatus
import com.writer.app.model.Language

class Converters {
    @TypeConverter
    fun languageToString(value: Language): String = value.name

    @TypeConverter
    fun stringToLanguage(value: String): Language = Language.valueOf(value)

    @TypeConverter
    fun statusToString(value: DocumentStatus): String = value.name

    @TypeConverter
    fun stringToStatus(value: String): DocumentStatus = DocumentStatus.valueOf(value)
}
