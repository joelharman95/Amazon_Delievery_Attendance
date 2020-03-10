package com.example.amazondelievery.di

import android.content.Context
import android.content.SharedPreferences
import com.example.amazondelievery.data.api.http.HttpClientManager
import com.example.amazondelievery.data.api.http.createApi
import com.example.amazondelievery.data.api.service.AuthenticationApi
import com.example.amazondelievery.data.preference.IPreferenceManager
import com.example.amazondelievery.data.repository.ILoginRepository
import com.example.amazondelievery.data.viewmodel.LoginViewModel
import com.example.amazondelievery.data.viewmodel.SplashViewModel
import com.example.amazondelievery.di.utility.Pref.AMAZON_SHARED_PREFERENCES
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val NETWORKING_MODULE = module {
    single { HttpClientManager.newInstance(getPreference(androidContext())) }
    single { get<HttpClientManager>().createApi<AuthenticationApi>() }
}

val REPOSITORY_MODULE = module {
    single { ILoginRepository.getInstance(get()) }
}

val VIEW_MODEL_MODULE = module {
    viewModel { LoginViewModel(get(), IPreferenceManager.getPrefInstance(androidContext())) }
    viewModel { SplashViewModel() }
}

val DB_MODULE = module {
    // single { CommunityDatabase.create(androidContext()) }
}

fun getPreference(androidContext: Context): SharedPreferences {
    return androidContext.getSharedPreferences(
        AMAZON_SHARED_PREFERENCES,
        Context.MODE_PRIVATE
    )
}





