package com.alirnp.androidwoocommerceapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import retrofit2.Converter
import java.io.Serializable
@Entity
class Category : Serializable {
    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var slug: String? = null
    var parent: Int = 0
    lateinit var description: String
    lateinit var display: String
    var image: Image? = null
    var menu_order: Int = 0
    var count: Int = 0
}
