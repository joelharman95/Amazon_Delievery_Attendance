package com.example.amazondelievery.di

import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.example.amazondelievery.R

fun TextView.applySpanPo(stringPrefix: String, stringSuffix: String, color: Int) {
    text = buildSpannedString {
        inSpans {
            ForegroundColorSpan(getCompatColor(R.color.black))
        }
        inSpans {
            append(stringPrefix)
        }.inSpans(
            ForegroundColorSpan(getCompatColor(color))
        ) {
            bold { append(stringSuffix) }
        }
    }
}

fun TextView.applySpanPo(
    stringPrefix: String,
    string: String,
    stringSuffix: String,
    font: Int
) {
    text = buildSpannedString {
        inSpans(
            ForegroundColorSpan(getCompatColor(R.color.black)),
            context.getCompatTypefaceSpan(font)
        ) {
            append(stringPrefix)
        }.inSpans(
            append(string),
            /*context.getCompatTypefaceSpan(R.font.poppins_regular),*/
            AbsoluteSizeSpan(getCompatSize(R.dimen.size_10))
        ) {

        }.inSpans(
            ForegroundColorSpan(getCompatColor(R.color.black)),
            context.getCompatTypefaceSpan(font)
        ) {
            bold { append(stringSuffix) }
        }
    }
}

fun TextView.applyBoldSpan(stringPrefix: String, stringSuffix: String, font: Int) {
    text = buildSpannedString {
        inSpans(
            ForegroundColorSpan(getCompatColor(R.color.black)),
            context.getCompatTypefaceSpan(font)
        ) {
            append(stringPrefix)
        }.inSpans {
            append(stringSuffix)
        }
    }
}