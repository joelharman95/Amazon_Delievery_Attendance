package com.example.amazondelievery.di

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.example.amazondelievery.di.utility.ImageConstants.CAMERA
import com.example.amazondelievery.di.utility.ImageConstants.GALLERY
import com.example.amazondelievery.di.utility.ImageConstants.IMAGE_DIRECTORY
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

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

fun Context.setCircularView(imageView: ImageView, pic: String?) {
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

fun Fragment.choosePhotoFromGallery() {
    startActivityForResult(
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
        GALLERY
    )
}

fun Fragment.takePhotoFromCamera() {
    startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA)
}

fun Fragment.showDialogToPick() {
    val pickDialog = AlertDialog.Builder(this.context)
    pickDialog.setTitle("Choose an action")
    val pictureSelection = arrayOf("Select image from gallery", "Take photo on camera")
    pickDialog.setItems(pictureSelection) { dialog, which ->
        when (which) {
            0 -> choosePhotoFromGallery()
            1 -> takePhotoFromCamera()
        }
    }
    pickDialog.show()
}

fun Context.savePic(bitmap: Bitmap, msg: String): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    //  val directory = File(Environment.getExternalStorageState().toString() + IMAGE_DIRECTORY)  // getExternalStorageDirectory()
    val directory = File(this.filesDir, IMAGE_DIRECTORY)
    if (!directory.exists()) directory.mkdirs()
    try {
        val file = File(directory, ((Calendar.getInstance().timeInMillis).toString() + ".jpg"))
        file.createNewFile()
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(byteArrayOutputStream.toByteArray())
        MediaScannerConnection.scanFile(this, arrayOf(file.path), arrayOf("image/jpeg"), null)
        fileOutputStream.close()
        this.toast("$msg  picture has been selected")
        return file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

fun EditText.showDate() {
    val cal = Calendar.getInstance()
    val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        this.setText("$year-${twoDigitConversion(month + 1)}-${twoDigitConversion(dayOfMonth)}")
    }
    DatePickerDialog(
        context,
        dateListener,
        cal.get(Calendar.YEAR),
        cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH)
    ).apply {
        datePicker.minDate = cal.timeInMillis
        show()
    }
}

fun twoDigitConversion(num: Int): String {
    return if (num < 10) "0$num" else "$num"
}