package com.example.amazondelievery.di

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.location.Location
import android.media.MediaScannerConnection
import android.provider.MediaStore
import android.text.TextPaint
import android.text.style.TypefaceSpan
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.FontRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.example.amazondelievery.R
import com.example.amazondelievery.di.utility.ImageConstants.CAMERA
import com.example.amazondelievery.di.utility.ImageConstants.GALLERY
import com.example.amazondelievery.di.utility.ImageConstants.IMAGE_DIRECTORY
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

fun Context.getCompatTypefaceSpan(@FontRes fontRes: Int): TypefaceSpan =
    getCompatFont(fontRes).let {
        object : TypefaceSpan(String()) {

            override fun updateDrawState(ds: TextPaint) {
                applyCustomTypeFace(ds, it)
            }

            override fun updateMeasureState(paint: TextPaint) {
                applyCustomTypeFace(paint, it)
            }

            fun applyCustomTypeFace(paint: Paint, tf: Typeface) {
                val fake = paint.typeface?.style ?: 0 and tf.style.inv()
                if (fake and Typeface.BOLD != 0) {
                    paint.isFakeBoldText = true
                }
                if (fake and Typeface.NORMAL != 0) {
                    paint.textSkewX = -0.25f
                }
                paint.typeface = tf
            }

        }
    }

fun Context.getCompatFont(@FontRes fontRes: Int): Typeface =
    ResourcesCompat.getFont(this, fontRes) ?: Typeface.DEFAULT

fun View.getCompatSize(@DimenRes dimenRes: Int): Int =
    resources.getDimension(dimenRes).toInt()

fun View.getCompatColor(@ColorRes colorRes: Int): Int =
    ContextCompat.getColor(this.context, colorRes)

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
        .placeholder(R.drawable.icon_user)
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
    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
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

fun TextView.showDate(text: String) {
    val cal = Calendar.getInstance()
    val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        (this as TextView).text =
            "$text - $year-${twoDigitConversion(month + 1)}-${twoDigitConversion(dayOfMonth)}"
        this.tag = "$year-${twoDigitConversion(month + 1)}-${twoDigitConversion(dayOfMonth)}"
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

fun TextView.showDate(text: String, date: String?, fromToDate: Boolean) {
    val cal = Calendar.getInstance()
    val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        (this as TextView).text =
            "$text - $year-${twoDigitConversion(month + 1)}-${twoDigitConversion(dayOfMonth)}"
        this.tag = "$year-${twoDigitConversion(month + 1)}-${twoDigitConversion(dayOfMonth)}"
    }
    DatePickerDialog(
        context,
        dateListener,
        cal.get(Calendar.YEAR),
        cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH)
    ).apply {
        if (fromToDate) {
            datePicker.convertToMilliMin(date)
        } else {
            datePicker.minDate = System.currentTimeMillis()
            datePicker.convertToMilliMax(date)
        }
        show()
    }
}

fun DatePicker.convertToMilliMin(date: String?) {
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val newDate = sdf.parse(date.toString())
        if (newDate != null)
            this.minDate = newDate.time
        else
            this.minDate = System.currentTimeMillis()
    } catch (e: Exception) {
        e.printStackTrace()
        this.minDate = System.currentTimeMillis()
    }
}

fun DatePicker.convertToMilliMax(date: String?) {
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val newDate = sdf.parse(date.toString())
        if (newDate != null)
            this.maxDate = newDate.time
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun TextView.showTime(text: String) {
    val cal = Calendar.getInstance()
    val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        (this as TextView).text =
            "$text - ${twoDigitConversion(hourOfDay)}:${twoDigitConversion(minute)}"
        this.tag = "${twoDigitConversion(hourOfDay)}:${twoDigitConversion(minute)}"
    }
    TimePickerDialog(
        context,
        timeListener,
        cal.get(Calendar.HOUR_OF_DAY),
        cal.get(Calendar.MINUTE),
        true
    ).show()
}

fun String.getTimeInMilli(): Long {
    return try {
        val sdf = SimpleDateFormat("HH:MM", Locale.getDefault())
        sdf.parse(this).time
    } catch (e : Exception) {
        e.printStackTrace()
        0L
    }
}

fun twoDigitConversion(num: Int): String {
    return if (num < 10) "0$num" else "$num"
}

fun LatLng.toLocation() = Location("").apply {
    latitude = latitude
    longitude = longitude
}

fun LatLng.distanceTo(latLng: LatLng) = toLocation().distanceTo(latLng.toLocation())