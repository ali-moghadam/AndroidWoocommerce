package com.alirnp.androidwoocommerceapp.repository.roomDB

import android.content.Context
import androidx.room.*
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.roomDB.converter.ImageListConverter

@Database(entities = arrayOf(Product::class), version = 3)
@TypeConverters(ImageListConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

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
