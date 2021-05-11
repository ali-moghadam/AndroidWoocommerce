package com.alirnp.androidwoocommerceapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.model.User
import com.alirnp.androidwoocommerceapp.viewModel.UserViewModel
import java.util.*
import kotlin.concurrent.timerTask

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)


        Timer().schedule(timerTask {
            run {
                activity?.runOnUiThread {
                    login("username", "PASS")
                    userViewModel.user.postValue(User("username"))
                }
            }
        }, 5000)

    }

    private fun login(username: String, password: String) {
        userViewModel.login(username, password).observe(viewLifecycleOwner, Observer { result ->
            if (result == true) {
                savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                findNavController().popBackStack()
            } else {
                showErrorMessage()
            }
        })
    }

    fun showErrorMessage() {
        // Display a snackbar error message
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment LoginFragment.
         */
        @JvmStatic
        fun newInstance() = LoginFragment()

        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }
}