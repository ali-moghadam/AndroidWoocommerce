package com.alirnp.androidwoocommerceapp.repository.api

import androidx.lifecycle.LiveData
import com.alirnp.androidwoocommerceapp.model.Category
import com.alirnp.androidwoocommerceapp.model.Product
import com.alirnp.androidwoocommerceapp.repository.networkBoundResource.ApiResponse
import retrofit2.Call
import retrofit2.http.*

interface CategoryAPI {

    /**
     * get all categories without filter
     */
    @GET("products/categories")
    fun getAllCategories(): LiveData<ApiResponse<List<Category>>>

    @Headers("Content-Type: application/json")
    @POST("products/categories")
    fun create(@Body body: Category): Call<Category>

    @GET("products/categories/{id}")
    fun view(@Path("id") id: Int): Call<Category>

    @Headers("Content-Type: application/json")
    @PUT("products/categories/{id}")
    fun update(@Path("id") id: Int, @Body body: Category): Call<Category>

    @DELETE("products/categories/{id}")
    fun delete(@Path("id") id: Int): Call<Category>

    @DELETE("products/categories/{id}")
    fun delete(@Path("id") id: Int, @Query("force") force: Boolean): Call<Category>

    @POST("products/categories/batch")
    fun batch(@Body body: Category): Call<String>

    @GET("products/categories")
    fun filter(@QueryMap filter: Map<String, String>): Call<List<Category>>

}