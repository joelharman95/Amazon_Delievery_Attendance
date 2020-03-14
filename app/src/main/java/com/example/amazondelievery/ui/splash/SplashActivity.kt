package com.example.amazondelievery.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amazondelievery.R
import com.example.amazondelievery.data.viewmodel.SplashViewModel
import com.example.amazondelievery.di.toast
import com.example.amazondelievery.ui.launch.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val vmSplash by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        vmSplash.isTokenValid(onSuccess = {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, onError = {
            toast(it)
        })
    }

}
