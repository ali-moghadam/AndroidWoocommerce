package com.alirnp.androidwoocommerceapp.repository.api

import com.alirnp.androidwoocommerceapp.model.user.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserApi {

    @Headers("Accept: application/json")
    @GET("login")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
    )
            : Observable<UserResponse>

}