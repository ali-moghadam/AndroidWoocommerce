package com.alirnp.androidwoocommerceapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.databinding.FragmentLoginBinding
import com.alirnp.androidwoocommerceapp.viewModel.UserViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)

        binding.materialButton.setOnClickListener {
            activity?.runOnUiThread {
                login(
                    binding.textInputLayoutUsername.editText?.text.toString(),
                    binding.textInputLayoutPassword.editText?.text.toString()
                )
            }
        }

    }

    private fun login(username: String, password: String) {
        val disposable = userViewModel.login(username, password)
            .subscribe(
                { Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show() },
                { error -> Toast.makeText(context, "${error.message}", Toast.LENGTH_SHORT).show() }
            )

        /* userViewModel.login(username, password).enqueue(object : Callback<UserResponse> {
             override fun onResponse(
                 call: Call<UserResponse>,
                 response: Response<UserResponse>
             ) {
                 if (response.isSuccessful) {
                     val user = response.body()?.user

                     userViewModel.user.postValue(user)

                     savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                     findNavController().popBackStack()
                 } else {
                     showErrorMessage(Throwable(response.errorBody()?.string()))

                    // showErrorMessage(Throwable(""))
                     // TODO: 5/28/2021 you should implement that error response from server
                 }
             }

             override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                 showErrorMessage(t)
             }

         })*/


    }

    fun showErrorMessage(t: Throwable) {
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