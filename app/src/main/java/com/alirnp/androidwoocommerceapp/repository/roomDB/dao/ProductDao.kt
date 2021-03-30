package com.alirnp.androidwoocommerceapp.repository.roomDB.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alirnp.androidwoocommerceapp.model.Product
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Product): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<Product>)

    @Delete
    fun delete(user: Product): Completable
}