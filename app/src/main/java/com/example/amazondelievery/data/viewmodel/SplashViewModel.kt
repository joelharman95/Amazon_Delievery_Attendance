package com.example.amazondelievery.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazondelievery.data.api.response.ResponseProfileStatus
import com.example.amazondelievery.data.preference.IPreferenceManager
import com.example.amazondelievery.data.repository.ISplashRepository
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val splashRepository: ISplashRepository,
    private val pref: IPreferenceManager
) : ViewModel() {

    fun getToken() = pref.getToken()

    fun isTokenValid(onSuccess: OnSuccess<ResponseProfileStatus>, onError: OnError<String>) {
        viewModelScope.launch {
            splashRepository.validateToken(getToken(), onSuccess, onError)
        }
    }

    /*class Factory(context: Context) : ViewModelProvider.NewInstanceFactory() {
        val repository = ILoginRepository.getInstance()
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SplashViewModel(repository) as T
        }
    }*/
}