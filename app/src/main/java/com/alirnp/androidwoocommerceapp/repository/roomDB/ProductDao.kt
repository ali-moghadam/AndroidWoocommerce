package com.alirnp.androidwoocommerceapp.repository.roomDB

import androidx.room.*
import com.alirnp.androidwoocommerceapp.model.Product
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): Single<List<Product>>

    @Query("SELECT * FROM product WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Product) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( users: List<Product>) : Completable

    @Delete
    fun delete(user: Product)
}