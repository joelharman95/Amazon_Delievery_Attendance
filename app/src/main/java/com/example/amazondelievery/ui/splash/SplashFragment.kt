package com.example.amazondelievery.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import com.example.amazondelievery.data.viewmodel.AuthenticationViewModel
import com.example.amazondelievery.di.toast
import com.example.amazondelievery.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val vmSplash by viewModel<AuthenticationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (vmSplash.getToken() != "") {

            vmSplash.checkLogin(onSuccess = { resLogin ->
                activity?.toast(resLogin.message.toString())
                if (resLogin.content == "3" && resLogin.isError == false)  //  3 -> Supervisor
                    (activity as MainActivity).isSupervisor(true)   //  For bottom navigation visibility
                else if (resLogin.content == "4" && resLogin.isError == false)  //  4 -> Supervisor
                    (activity as MainActivity).isSupervisor(false)
                vmSplash.employeeProfileStatus(onSuccess = { profileStatus ->
                    if (profileStatus.content?.profileUpdatedStatus!!) {
                        if (profileStatus.content.approvedStatus!!) {
                            when (findNavController().currentDestination?.id) {
                                R.id.splashFragment -> findNavController().navigate(R.id.action_splash_to_dashboardFrag)
                            }
                        } else {
                            when (findNavController().currentDestination?.id) {
                                R.id.splashFragment -> findNavController().navigate(R.id.action_splash_to_relaxFrag)
                            }
                        }
                    } else {
                        when (findNavController().currentDestination?.id) {
                            R.id.splashFragment -> findNavController().navigate(R.id.action_splash_to_personalDetails)
                        }
                    }
                }, onError = {
                    activity?.toast(it)
                })
            }, onError = {
                //  status code == 401 or any
                activity?.toast(it)
                toLoginFrag()
            })
        } else {
            toLoginFrag()
        }
    }

    private fun toLoginFrag() {
        when (findNavController().currentDestination?.id) {
            R.id.splashFragment -> {
                findNavController().navigate(R.id.action_splash_to_login)
            }
        }
    }

}
