package com.example.amazondelievery.ui.credential

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.viewmodel.AuthenticationViewModel
import com.example.amazondelievery.di.blockUI
import com.example.amazondelievery.di.toast
import com.example.amazondelievery.di.unBlockUI
import com.example.amazondelievery.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    /*private val vmLogin by lazy {
        let {
            ViewModelProviders.of(this, LoginViewModel.Factory(it.context!!))
                .get(LoginViewModel::class.java)
        }
    }*/

    private val vmLogin by viewModel<AuthenticationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnLogin.setOnClickListener {
            if (isFieldValid()) {
                pbLogin.blockUI(activity as AppCompatActivity)
                val requestLogin = ReqLogin(etEmail.text.toString(), etPassword.text.toString())

                vmLogin.login(requestLogin, onSuccess = { resLogin ->

                    vmLogin.saveToken(resLogin.content.toString())
                    activity?.toast(resLogin.message.toString())

                    vmLogin.employeeProfileStatus(onSuccess = { profileStatus ->
                        vmLogin.checkLogin(onSuccess = { resLogin ->
                            activity?.toast(resLogin.message.toString())
                            if (resLogin.content == "3" && resLogin.isError == false)  //  3 -> Supervisor
                                (activity as MainActivity).isSupervisor(true)   //  For bottom navigation visibility
                            else if (resLogin.content == "4" && resLogin.isError == false)  //  4 -> Delivery Associate
                                (activity as MainActivity).isSupervisor(false)
                            pbLogin.unBlockUI(this.requireActivity())
                            if (profileStatus.content?.profileUpdatedStatus!!) {
                                if (profileStatus.content.approvedStatus!!) {
                                    when (findNavController().currentDestination?.id) {
                                        R.id.loginFragment -> findNavController().navigate(R.id.action_login_to_dashboard_frag)
                                    }
                                } else {
                                    when (findNavController().currentDestination?.id) {
                                        R.id.loginFragment -> findNavController().navigate(R.id.action_login_to_relax)
                                    }
                                }
                            } else {
                                when (findNavController().currentDestination?.id) {
                                    R.id.loginFragment -> findNavController().navigate(R.id.action_login_to_details)
                                }
                            }
                        }, onError = {
                            unBlock(it)
                        })
                    }, onError = {
                        unBlock(it)
                    })
                }, onError = {
                    unBlock(it)
                })
            }
        }
    }

    private fun unBlock(msg: String) {
        activity?.toast(msg)
        pbLogin.unBlockUI(this.requireActivity())
    }

    private fun isFieldValid(): Boolean {
        return when {
            TextUtils.isEmpty(etEmail.text.toString()) -> {
                etEmail.error = "Phone number required"
                false
            }
            !Patterns.PHONE.matcher(etEmail.text.toString()).matches() -> {
                etEmail.error = "Valid phone number required"
                false
            }
            etEmail.text.toString().length < 10 -> {
                etEmail.error = "Valid phone number required"
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