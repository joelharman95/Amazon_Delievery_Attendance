package com.example.amazondelievery.ui.credential.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.amazondelievery.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnLogin.setOnClickListener {
            //  if (isFieldValid()) {
            findNavController().navigate(R.id.action_login_to_details)
            //  }
        }
    }

    private fun isFieldValid(): Boolean {
        if (TextUtils.isEmpty(etEmail.text.toString())) {
            etEmail.error = "Email Address Required"
            return false
        } else if (TextUtils.isEmpty(etPassword.text.toString())) {
            etPassword.error = "Password Required"
            return false
        }
        return true
    }

}
