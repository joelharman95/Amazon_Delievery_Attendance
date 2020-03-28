package com.example.amazondelievery.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.api.request.RequestLeave
import com.example.amazondelievery.data.api.response.*
import com.example.amazondelievery.data.preference.IPreferenceManager
import com.example.amazondelievery.data.repository.IDashboardRepository
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardRepository: IDashboardRepository,
    private val pref: IPreferenceManager
) : ViewModel() {

    fun clearToken() = pref.clearToken()

    fun getEmployeeDetail(
        onSuccess: OnSuccess<ResponseEmployeeDetails>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            dashboardRepository.getEmployeeDetail(onSuccess, onError)
        }
    }

    fun sendLeaveRequest(
        requestLeave: RequestLeave,
        onSuccess: OnSuccess<ResponseLogin>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            dashboardRepository.sendLeaveRequest(requestLeave, onSuccess, onError)
        }
    }

    fun getLeaveList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseLeaveList>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            dashboardRepository.getLeaveList(size, page, onSuccess, onError)
        }
    }

    fun getSupervisorLeaveList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseLeaveList>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            dashboardRepository.getSupervisorLeaveList(size, page, onSuccess, onError)
        }
    }

    fun postLeaveManagement(
        status: Int,
        lmId: Int,
        onSuccess: OnSuccess<ResponseLeaveApproval>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            dashboardRepository.postLeaveManagement(status, lmId, onSuccess, onError)
        }
    }

    fun getDelAssociateteList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseDelAssociatete>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            dashboardRepository.getDelAssociateteList(size, page, onSuccess, onError)
        }
    }

    fun getLatestLeaveList(
        size: Int,
        page: Int,
        onSuccess: OnSuccess<ResponseLatestLeaveList>,
        onError: OnError<String>
    ) {
        viewModelScope.launch {
            dashboardRepository.getLatestLeaveList(size, page, onSuccess, onError)
        }
    }

}