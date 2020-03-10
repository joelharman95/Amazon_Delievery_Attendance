package com.example.amazondelievery.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.amazondelievery.di.utility.Pref.AMAZON_SHARED_PREFERENCES
import com.example.amazondelievery.di.utility.Pref.TOKEN

class PreferenceManager(private val context: Context) : IPreferenceManager {

    val pref: SharedPreferences =
        context.getSharedPreferences(AMAZON_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    override fun saveToken(token: String?) = pref.edit().putString(TOKEN, token).apply()

    override fun getToken(): String = pref.getString(TOKEN, "").toString()
}