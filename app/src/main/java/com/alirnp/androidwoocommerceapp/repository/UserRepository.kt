package com.alirnp.androidwoocommerceapp.repository

import android.app.Application
import com.alirnp.androidwoocommerceapp.model.user.UserResponse
import com.alirnp.androidwoocommerceapp.repository.api.UserApi
import com.alirnp.androidwoocommerceapp.repository.base.WooRepository
import io.reactivex.Observable

class UserRepository(application: Application, baseUrl: String) :
    WooRepository(application, baseUrl) {

    private val userAPI: UserApi = retrofit.create(UserApi::class.java)

    /**
     * login user in wordpress api
     */
    fun userLogin(username: String, password: String): Observable<UserResponse> {
        return userAPI.login(username, password)
    }


}