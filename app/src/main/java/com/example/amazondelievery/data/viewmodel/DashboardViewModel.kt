package com.example.amazondelievery.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.api.response.ResponseEmployeeDetails
import com.example.amazondelievery.data.api.response.ResponseLogin
import com.example.amazondelievery.data.preference.IPreferenceManager
import com.example.amazondelievery.data.repository.IDashboardRepository
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardRepository: IDashboardRepository,
    private val pref: IPreferenceManager
) : ViewModel() {

    fun getEmployeeDetail(
        onSuccess: OnSuccess<ResponseEmployeeDetails>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            dashboardRepository.getEmployeeDetail(onSuccess, onError)
        }
    }

}