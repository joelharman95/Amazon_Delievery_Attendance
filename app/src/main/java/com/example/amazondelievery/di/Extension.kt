package com.example.amazondelievery.di

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import com.bumptech.glide.Glide

typealias OnSuccess<R> = (R) -> Unit
typealias OnError<R> = (R) -> Unit

fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Context.toast(msg: Int) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun ProgressBar.blockUI() {
    this.visibility = View.VISIBLE
}

fun ProgressBar.unBlockUI() {
    this.visibility = View.GONE
}

fun Activity.setCircularView(imageView: ImageView, pic: Int?) {
    Glide
        .with(this)
        .load(pic)
        .circleCrop()
        .into(imageView)
}

private fun Activity.initToolbar(tbar: Toolbar, name: String, navController: NavController) {
    (this as AppCompatActivity).setSupportActionBar(tbar)
    (this as AppCompatActivity).supportActionBar?.apply {
        title = name
        setDisplayHomeAsUpEnabled(true)
    }
    tbar.setNavigationOnClickListener {
        navController.navigateUp()
    }
}