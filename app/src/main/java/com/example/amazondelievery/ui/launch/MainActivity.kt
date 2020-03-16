package com.example.amazondelievery.ui.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.amazondelievery.R
import com.example.amazondelievery.di.hide
import com.example.amazondelievery.di.show
import com.example.amazondelievery.ui.launch.fragment.home.dashboard.BackPressEvent
import kotlinx.android.synthetic.main.activity_credential.*

class MainActivity : AppCompatActivity() {

    var backPressEvent: BackPressEvent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credential)

        //  setSupportActionBar(tbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.credentialNavHost) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottomNavHome, navController)   //  Navigation bar
        //  NavigationUI.setupActionBarWithNavController(this, navController)  //  Toolbar

        setNavElementVisibility(navController)
    }

    /*override fun onSupportNavigateUp(): Boolean {
        //  return Navigation.findNavController(this, R.id.credentialNavHost).navigateUp()
        return findNavController(R.id.credentialNavHost).navigateUp()
    }*/

    private fun setNavElementVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.dashboardFrag -> bottomNavHome.show()
                R.id.employeeFrag -> bottomNavHome.show()
                R.id.dayDeliveryFrag -> bottomNavHome.show()
                R.id.taskFrag -> bottomNavHome.show()
                R.id.leaveRequestFrag -> bottomNavHome.show()
                else -> bottomNavHome.hide()
            }
        }
    }

    fun setListener(mBackPressEvent: BackPressEvent) {
        backPressEvent = mBackPressEvent
    }

    override fun onBackPressed() {
        if (findNavController(R.id.credentialNavHost).currentDestination?.id != null) {
            when (findNavController(R.id.credentialNavHost).currentDestination?.id) {
                R.id.dashboardFrag -> {
                    if (backPressEvent?.backTriggered()!!) {
                        finish()
                    }
                }
                R.id.loginFragment, R.id.taskFrag, R.id.relaxFrag, R.id.employeeFrag, R.id.dayDeliveryFrag, R.id.leaveRequestFrag -> {
                    finish()
                }
                else -> findNavController(R.id.credentialNavHost).navigateUp()
            }
        } else super.onBackPressed()
    }

}
