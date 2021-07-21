package com.alirnp.androidwoocommerceapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alirnp.androidwoocommerceapp.model.user.User
import com.alirnp.androidwoocommerceapp.model.user.UserResponse
import com.alirnp.androidwoocommerceapp.repository.api.WoocommerceApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserViewModel : ViewModel() {

    var user: MutableLiveData<User?> = MutableLiveData(null)
    private val wordpressRepository = WoocommerceApi.instance.userRepository

    fun login(username: String, password: String): Observable<UserResponse> {
        return wordpressRepository.userLogin(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}