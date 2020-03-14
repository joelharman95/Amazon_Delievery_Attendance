package com.example.amazondelievery.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.amazondelievery.di.utility.Pref.AMAZON_SHARED_PREFERENCES
import com.example.amazondelievery.di.utility.Pref.TOKEN
import com.example.amazondelievery.di.utility.Pref.VERIFY_STATUS

class PreferenceManager(private val context: Context) : IPreferenceManager {

    val pref: SharedPreferences = context.getSharedPreferences(AMAZON_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    override fun saveToken(token: String?) = pref.edit().putString(TOKEN, token).apply()

    override fun getToken(): String = pref.getString(TOKEN, "").toString()

    override fun saveVerifyStatus(verifyStatus: Boolean) = pref.edit().putBoolean(VERIFY_STATUS, verifyStatus).apply()

    override fun getVerifyStatus() = pref.getBoolean(VERIFY_STATUS, false)


}