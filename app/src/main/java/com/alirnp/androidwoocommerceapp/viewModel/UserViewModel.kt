package com.alirnp.androidwoocommerceapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alirnp.androidwoocommerceapp.model.User
import com.alirnp.androidwoocommerceapp.repository.WordpressRepository

class UserViewModel : ViewModel() {

    var user: MutableLiveData<User?> = MutableLiveData(null)
    private var wordpressRepository: WordpressRepository = WordpressRepository()

    fun login(username: String, password: String): MutableLiveData<Boolean> {
        return wordpressRepository.userLogin(username, password)
    }
}