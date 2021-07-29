package com.alirnp.androidwoocommerceapp.di

import android.content.Context
import androidx.room.Room
import com.alirnp.androidwoocommerceapp.repository.roomDB.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            AppDatabase::class.java, "woocommerce.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}