package com.alirnp.androidwoocommerceapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alirnp.androidwoocommerceapp.model.User
import com.alirnp.androidwoocommerceapp.repository.Wordpress

class UserViewModel : ViewModel() {

    var user: MutableLiveData<User?> = MutableLiveData(null)
    var wordpress: Wordpress = Wordpress()

    fun login(username: String, password: String): MutableLiveData<Boolean> {
        return wordpress.userLogin(username, password)
    }
}