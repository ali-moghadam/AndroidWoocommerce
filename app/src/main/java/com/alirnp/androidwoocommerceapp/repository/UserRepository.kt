package com.alirnp.androidwoocommerceapp.repository

import com.alirnp.androidwoocommerceapp.model.user.UserResponse
import com.alirnp.androidwoocommerceapp.repository.api.UserApi
import io.reactivex.Observable
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserApi) {

    /**
     * login user in wordpress api
     */
    fun userLogin(username: String, password: String): Observable<UserResponse> {
        return userAPI.login(username, password)
    }


}