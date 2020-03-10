package com.example.amazondelievery.di

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Context.toast(msg: Int) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun ProgressBar.blockUI(activity: Activity) {
    this.visibility = View.VISIBLE
    activity.window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun ProgressBar.unBlockUI(activity: Activity) {
    this.visibility = View.GONE
    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Context.setCircularView(imageView: ImageView, pic: Int?) {
    Glide
        .with(this)
        .load(pic)
        .circleCrop()
        .into(imageView)
}

fun Activity.initToolbar(tbar: Toolbar, name: String, navController: NavController) {
    (this as AppCompatActivity).setSupportActionBar(tbar)
    this.supportActionBar?.apply {
        title = name
        setDisplayHomeAsUpEnabled(true)
    }
    tbar.setNavigationOnClickListener {
        navController.navigateUp()
    }
}

fun BottomNavigationView.show() {
    this.visibility = View.VISIBLE
}

fun BottomNavigationView.hide() {
    this.visibility = View.GONE
}