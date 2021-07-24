package com.alirnp.androidwoocommerceapp.repository.roomDB.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.model.Product
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Category): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Category>)

    @Delete
    fun delete(product: Category): Completable

    @Query("DELETE FROM category")
    fun deleteAll()
}