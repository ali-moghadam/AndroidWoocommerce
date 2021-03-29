package com.alirnp.androidwoocommerceapp.repository.roomDB.converter

import androidx.room.TypeConverter
import com.alirnp.androidwoocommerceapp.model.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ImageListConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<Image> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Image>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Image>): String {
        return gson.toJson(someObjects)
    }


    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}