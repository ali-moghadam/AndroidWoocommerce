package com.alirnp.androidwoocommerceapp.repository.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.roomDB.converter.ImageConverter
import com.alirnp.androidwoocommerceapp.repository.roomDB.converter.ProductConverter
import com.alirnp.androidwoocommerceapp.repository.roomDB.dao.CategoryDao
import com.alirnp.androidwoocommerceapp.repository.roomDB.dao.ProductDao

@Database(entities = [Product::class ,Category::class], version = 13)
@TypeConverters(ProductConverter::class , ImageConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "woocommerce.db")
                .fallbackToDestructiveMigration()
                .build()
    }

}
