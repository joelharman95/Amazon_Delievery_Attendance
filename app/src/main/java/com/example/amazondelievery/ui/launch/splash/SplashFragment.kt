package com.example.amazondelievery.ui.launch.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import com.example.amazondelievery.data.viewmodel.SplashViewModel
import com.example.amazondelievery.di.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val vmSplash by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vmSplash.isTokenValid(onSuccess = { profileStatus ->
            if (profileStatus.content?.profileUpdatedStatus!!) {
                if (profileStatus.content.approvedStatus!!) {
                    when (findNavController().currentDestination?.id) {
                        R.id.loginFragment -> findNavController().navigate(R.id.action_splashFragment_to_dashboardFrag)
                    }
                } else {
                    when (findNavController().currentDestination?.id) {
                        R.id.loginFragment -> findNavController().navigate(R.id.action_splashFragment_to_relaxFrag)
                    }
                }
            } else
                toLoginFrag()
        }, onError = {
            if (vmSplash.getToken() != "") {       //  TODO  :: ::  Need to remove this below two statements
                when (findNavController().currentDestination?.id) {
                    R.id.loginFragment -> findNavController().navigate(R.id.action_splashFragment_to_dashboardFrag)
                }
            }
            toLoginFrag()
            activity?.toast(it)
        })
    }

    private fun toLoginFrag() {
        when (findNavController().currentDestination?.id) {
            R.id.splashFragment -> {
                findNavController().navigate(R.id.action_splash_to_login)
            }
        }
    }

}
