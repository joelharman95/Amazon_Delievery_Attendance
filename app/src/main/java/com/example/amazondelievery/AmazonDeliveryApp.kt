package com.example.amazondelievery

import android.app.Application
import com.example.amazondelievery.di.NETWORKING_MODULE
import com.example.amazondelievery.di.REPOSITORY_MODULE
import com.example.amazondelievery.di.VIEW_MODEL_MODULE
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AmazonDeliveryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AmazonDeliveryApp)
            loadKoinModules(REQUIRED__MODULE)
        }
    }

    companion object {
        val REQUIRED__MODULE = listOf(
            NETWORKING_MODULE,
            VIEW_MODEL_MODULE,
            REPOSITORY_MODULE
        )
    }
}