package com.alirnp.androidwoocommerceapp.repository.roomDB.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.alirnp.androidwoocommerceapp.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class ImageConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToImage(data: String?): Image? {
        if (data == null) {
            return Image()
        }
        val listType: Type = object : TypeToken<Image?>() {}.type
        return gson.fromJson<Image>(data, listType)
    }

    @TypeConverter
    fun imageToString(image: Image?): String? {
        return gson.toJson(image)
    }

}