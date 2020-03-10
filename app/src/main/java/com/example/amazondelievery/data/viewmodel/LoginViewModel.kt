package com.example.amazondelievery.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazondelievery.data.api.request.ReqAllDetails
import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.api.response.ResponseLogin
import com.example.amazondelievery.data.preference.IPreferenceManager
import com.example.amazondelievery.data.repository.ILoginRepository
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: ILoginRepository,
    private val pref: IPreferenceManager
) : ViewModel() {

    fun saveToken(token: String) = pref.saveToken(token)

    fun login(
        requestLogin: ReqLogin,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            loginRepository.login(requestLogin, onSuccess, onError)
        }
    }

    fun postPersonalDetails(
        reqAllDetails: ReqAllDetails,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            loginRepository.postPersonalDetails(reqAllDetails, onSuccess, onError)
        }
    }
}