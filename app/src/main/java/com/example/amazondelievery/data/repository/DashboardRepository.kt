package com.example.amazondelievery.data.repository

import com.example.amazondelievery.data.api.request.ReqLogin
import com.example.amazondelievery.data.api.response.ResponseEmployeeDetails
import com.example.amazondelievery.data.api.response.ResponseLogin
import com.example.amazondelievery.data.api.service.HomeApi
import com.example.amazondelievery.di.utility.OnError
import com.example.amazondelievery.di.utility.OnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DashboardRepository(
    private val api: HomeApi
) : IDashboardRepository {

    override suspend fun getEmployeeDetail(
        onSuccess: OnSuccess<ResponseEmployeeDetails>,
        onError: OnError<String>
    ) {
        withContext(Dispatchers.IO) {
            try {
                val response = api.getEmployeeDetail()
                if (response.isSuccessful) {
                    response.body().let {
                        if (!it?.isError!!)
                            withContext(Dispatchers.Main) { onSuccess(it) }
                        else
                            withContext(Dispatchers.Main) { onError(it.message.toString()) }
                    }
                } else
                    withContext(Dispatchers.Main) { onError(response.message().toString()) }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) { onError(e.toString()) }
            }
        }
    }

}


interface IDashboardRepository {

    suspend fun getEmployeeDetail(
        onSuccess: OnSuccess<ResponseEmployeeDetails>,
        onError: OnError<String>
    )

    companion object Factory {
        fun getInstance(api: HomeApi): IDashboardRepository = DashboardRepository(api)
    }

}