package com.alirnp.androidwoocommerceapp.repository.roomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.alirnp.androidwoocommerceapp.model.Product
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface UserDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Product>

    @Insert
    fun insert(user: Product) : Completable

    @Insert
    fun insertAll(vararg users: Product)

    @Delete
    fun delete(user: Product)
}