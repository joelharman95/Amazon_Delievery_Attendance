package com.example.amazondelievery.ui.launch.fragment.credential

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.viewmodel.LoginViewModel
import com.example.amazondelievery.di.blockUI
import com.example.amazondelievery.di.toast
import com.example.amazondelievery.di.unBlockUI
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    /*private val vmLogin by lazy {
        let {
            ViewModelProviders.of(this, LoginViewModel.Factory(it.context!!))
                .get(LoginViewModel::class.java)
        }
    }*/

    private val vmLogin by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnLogin.setOnClickListener {

            findNavController().navigate(R.id.action_login_to_details)     //  For testing purpose, need to remove it

            if (isFieldValid()) {
                pbLogin.blockUI(this.requireActivity())
                val requestLogin = ReqLogin(etEmail.text.toString(), etPassword.text.toString())

                vmLogin.login(requestLogin, onSuccess = { resLogin ->

                    vmLogin.saveToken(resLogin.content.toString())

                    activity?.toast(resLogin.message.toString())
                    pbLogin.unBlockUI(this.requireActivity())
                    when (findNavController().currentDestination?.id) {
                        R.id.loginFragment -> findNavController().navigate(R.id.action_login_to_details)
                    }
                }, onError = {
                    activity?.toast(it)
                    pbLogin.unBlockUI(this.requireActivity())
                })
            }
        }
    }

    private fun isFieldValid(): Boolean {
        return when {
            TextUtils.isEmpty(etEmail.text.toString()) -> {
                etEmail.error = "Email Address required"
                false
            }
            TextUtils.isEmpty(etPassword.text.toString()) -> {
                etPassword.error = "Password required"
                false
            }
            else -> true
        }
    }

}
