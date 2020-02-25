package com.example.amazondelievery.ui.credential

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.amazondelievery.R

class CredentialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credential)

    }

    override fun onBackPressed() {
        if (findNavController(R.id.nav_host_fragment).currentDestination?.id != null) {
            findNavController(R.id.nav_host_fragment).navigateUp()
        } else {
            super.onBackPressed()
        }

    }

}
