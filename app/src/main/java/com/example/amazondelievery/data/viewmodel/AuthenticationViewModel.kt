package com.example.amazondelievery.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazondelievery.data.api.request.ReqAllDetails
import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.api.response.ResponseLogin
import com.example.amazondelievery.data.api.response.ResponseProfileStatus
import com.example.amazondelievery.data.preference.IPreferenceManager
import com.example.amazondelievery.data.repository.IAuthenticationRepository
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val loginRepository: IAuthenticationRepository,
    private val pref: IPreferenceManager
) : ViewModel() {

    fun saveToken(token: String) = pref.saveToken(token)
    fun saveVerifyStatus(verifyStatus: Boolean) = pref.saveVerifyStatus(verifyStatus)
    fun getVerifyStatus() = pref.getVerifyStatus()

    fun getToken() = pref.getToken()

    fun checkLogin(onSuccess: OnSuccess<ResponseLogin>, onError: OnError<String>) {
        viewModelScope.launch {
            loginRepository.checkLogin(onSuccess, onError)
        }
    }

    fun login(
        requestLogin: ReqLogin,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            loginRepository.login(requestLogin, onSuccess, onError)
        }
    }

    fun employeeProfileStatus(
        onSuccess: OnSuccess<ResponseProfileStatus>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            loginRepository.employeeProfileStatus(onSuccess, onError)
        }
    }

    fun postPersonalDetails(
        reqAllDetails: ReqAllDetails,
        files: HashMap<String, String>,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            loginRepository.postPersonalDetails(reqAllDetails, files, onSuccess, onError)
        }
    }

}