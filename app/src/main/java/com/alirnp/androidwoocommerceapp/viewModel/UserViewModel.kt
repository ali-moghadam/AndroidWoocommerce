package com.alirnp.androidwoocommerceapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alirnp.androidwoocommerceapp.model.user.User
import com.alirnp.androidwoocommerceapp.model.user.UserResponse
import com.alirnp.androidwoocommerceapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {

    var user: MutableLiveData<User?> = MutableLiveData(null)
    @Inject lateinit var userRepository : UserRepository

    fun login(username: String, password: String): Observable<UserResponse> {
        return userRepository.userLogin(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}