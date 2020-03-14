package com.example.amazondelievery.data.preference

import android.content.Context

interface IPreferenceManager {

    fun saveToken(token: String?)
    fun getToken(): String
    fun saveVerifyStatus(verifyStatus: Boolean)
    fun getVerifyStatus(): Boolean

    companion object Factory {
        fun getPrefInstance(context: Context): IPreferenceManager = PreferenceManager(context)
    }
}