package com.alirnp.androidwoocommerceapp.repository.roomDB

import android.content.Context
import androidx.room.*
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.roomDB.converter.ProductConverter
import com.alirnp.androidwoocommerceapp.repository.roomDB.dao.ProductDao

@Database(entities = arrayOf(Product::class), version = 8)
@TypeConverters(ProductConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

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
