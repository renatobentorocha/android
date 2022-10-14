package com.example.jetnote.converters

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date: Date) = date.time

    @TypeConverter
    fun dateFromTimeStamp(timeStamp: Long) = Date(timeStamp)
}