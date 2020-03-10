package com.example.amazondelievery.data.preference

import android.content.Context

interface IPreferenceManager {

    fun saveToken(token: String?)
    fun getToken(): String

    companion object Factory {
        fun getPrefInstance(context: Context): IPreferenceManager = PreferenceManager(context)
    }
}