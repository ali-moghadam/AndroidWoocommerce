package com.alirnp.androidwoocommerceapp.repository.roomDB.converter

import androidx.room.TypeConverter
import com.alirnp.androidwoocommerceapp.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ProductConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToImageList(data: String?): List<Image> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Image>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToDownloadList(data: String?): List<Download> {
        if (data == null)
            return Collections.emptyList()

        val listType = object : TypeToken<List<Download>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToProductAttributeList(data: String?): List<ProductAttribute> {
        if (data == null)
            return Collections.emptyList()

        val listType = object : TypeToken<List<ProductAttribute>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToDefaultAttributeList(data: String?): List<DefaultAttribute> {
        if (data == null)
            return Collections.emptyList()

        val listType = object : TypeToken<List<DefaultAttribute>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToCategoryList(data: String?): List<Category> {
        if (data == null)
            return Collections.emptyList()

        val listType = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToTagList(data: String?): List<Tag> {
        if (data == null)
            return Collections.emptyList()

        val listType = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToIntegerList(data: String?): List<Int> {
        if (data == null)
            return Collections.emptyList()

        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<*>): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date {
        return value?.let {
            Date(it)
        } ?: run {
            Date(0)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long {
        return date?.let {
            date.time
        } ?: run {
            0
        }
    }

}