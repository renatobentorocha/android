package com.example.jetnote.converters

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {
    @TypeConverter
    fun stringFromUUID(uuid: UUID) = uuid.toString()

    @TypeConverter
    fun uuidFromString(uuid: String) = UUID.fromString(uuid)
}