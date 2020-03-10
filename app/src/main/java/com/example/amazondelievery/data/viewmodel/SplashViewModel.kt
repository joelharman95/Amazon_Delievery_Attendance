package com.example.amazondelievery.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    fun getLoginRepo(onSuccess: OnSuccess<Boolean>, onError: OnError<String>) {
        viewModelScope.launch {
            delay(200)
            onSuccess(true)
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